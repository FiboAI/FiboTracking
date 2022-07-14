package cn.fibo.analysis.tracking.db;

import cn.fibo.analysis.tracking.constants.CacheKeys;
import cn.fibo.analysis.tracking.constants.ConfigServerAddressConstants;
import cn.fibo.analysis.tracking.model.BaseLibEntity;
import cn.fibo.analysis.tracking.model.BaseUserEntity;
import cn.fibo.analysis.tracking.model.DataEntity;
import cn.fibo.analysis.tracking.utils.CacheSingletonUtil;
import cn.fibo.analysis.tracking.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.io.IOException;
import java.sql.*;
import java.util.UUID;

/**
 * @author lisw
 * @program jar-data-analysis
 * @description 数据库操作类
 * @createDate 2022-05-06 20:09:50
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Slf4j
public class ClickhouseImpl  extends RichSinkFunction<DataEntity> {
    private Connection conn;
    //行为记录插入
    private PreparedStatement insertStmt;
    //用户插入
    private PreparedStatement insertUserStatement;
    //根据设备ID查询用户信息
    private PreparedStatement selectUserByFirstIdStatement;
    //根据业务系统的用户ID查询用户信息
    private PreparedStatement selectUserBySecondIdStatement;
    //根据userid更新user
    private PreparedStatement updateUserByUserIdStatement;
    //删除用户
    private PreparedStatement deleteUserStatement;
    //更新业务用户，修复用户多端数据
    private PreparedStatement updateBaseDataStatement;




    @Override
    public void open(Configuration parameters){
        try {
            conn = DriverManager.getConnection(ConfigServerAddressConstants.CLICKHOUSE_SERVER_URL,ConfigServerAddressConstants.CLICKHOUSE_USERNAME,ConfigServerAddressConstants.CLICKHOUSE_PASSWORD);
            String insertUserSQL = "insert into users ("+
                    "user_id,first_id,second_id,$device_id_list,project,server_time)"+
                    "values"+
                    "(?,?,?,?,?,?)";
            insertUserStatement = conn.prepareStatement(insertUserSQL);

            String selectUserByFirstIdSQL = "select * from users where first_id = ? and project = ?";
            selectUserByFirstIdStatement = conn.prepareStatement(selectUserByFirstIdSQL);

            String selectUserBySecondIdSQL = "select * from users where second_id = ?  and project = ?";
            selectUserBySecondIdStatement = conn.prepareStatement(selectUserBySecondIdSQL);

            String updateUserByUserIdSQL = "alter table users update first_id = ?,second_id = ?,$device_id_list=? where user_id = ?";
            updateUserByUserIdStatement = conn.prepareStatement(updateUserByUserIdSQL);


            String deleteUserStatementSQL = "alter table users update status = ? where user_id = ?";
            deleteUserStatement = conn.prepareStatement(deleteUserStatementSQL);

            String updateBaseDataStatementSQL = "alter table events update user_id = ? where user_id = ? and project = ?";
            updateBaseDataStatement = conn.prepareStatement(updateBaseDataStatementSQL);

            StringBuffer insertBaseData = new StringBuffer();
            insertBaseData.append("INSERT INTO events (");
            insertBaseData.append("distinct_id,event,$lib,$lib_version,$screen_height, $screen_width,$latest_referrer,$latest_referrer_host,$latest_traffic_source_type,$latest_search_keyword,");
            insertBaseData.append("$referrer,$referrer_host,$url,$url_path,$title,$is_first_day, $is_first_time,$event_duration,$element_id,$element_content,");
            insertBaseData.append("$element_name, $element_class_name,$element_type,$element_selector,$element_target_url, $time,server_time,channel,channel_type,$page_x,");
            insertBaseData.append( "$page_y,commodity_detail_souce, commodity_id,commodity_name,commodity_tag,first_commodity,second_commodity, original_price,present_price,discount_price,");
            insertBaseData.append("quantity_unit,monetary_unit, store_id,store_name,supplier_id,supplier_name,service_type,get_type, is_success,fail_reason,");
            insertBaseData.append("account,login_method,is_quick_login,commodity_size, commodity_colour,commodity_quantity,viewpoint_height,entrance,order_id, order_amount,");
            insertBaseData.append("order_actual_amount,receiver_name,receiver_province, receiver_city,receiver_area,receiver_address,transportation_costs, discount_name,discount_amount,is_use_discount,");
            insertBaseData.append("if_use_integral, number_of_integral,integral_discount_amount,is_cash_back,cash_back_amount, cash_back_name,commodity_discount_amount,total_price_of_commodity, payment_method,delivery_method," );
            insertBaseData.append("page_type,banner_belong_area,banner_type, banner_name,banner_id,url,banner_rank,project,platform_type,register_method," );
            insertBaseData.append("type,anonymous_id,login_id,$app_id,$model,$manufacturer,$os,$os_version,$is_login_id,$longitude," );
            insertBaseData.append("$latitude,$ip,$timezone_offset,$country,$province,$city,$network_type,$browser,$browser_version,$scene," );
            insertBaseData.append("$latest_utm_source,$latest_utm_medium,$latest_utm_term,$latest_utm_content,$latest_utm_campaign,$latest_scene,$url_query,$brand,$mp_client_app_version,$mp_client_basic_library_version," );
            insertBaseData.append("$geo_coordinate_system,$app_version,$wifi,$carrier,$device_id,$app_name,$screen_orientation,user_id,$identity_login_id,flume_time, " );
            insertBaseData.append("_track_id)");
            insertBaseData.append("VALUES" );
            insertBaseData.append("(" );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?," );
            insertBaseData.append("?,?,?,?,?,?,?,?,?,?,");
            insertBaseData.append("?)");
            insertStmt = conn.prepareStatement(insertBaseData.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //插入用户
    private String insertUser(String firstId,String second_id,String $device_id_list,String project){
        String userId = "";
        userId = UUID.randomUUID().toString();
        try {
            insertUserStatement.setString(1,userId);
            insertUserStatement.setString(2,firstId);
            insertUserStatement.setString(3,second_id);
            insertUserStatement.setString(4,$device_id_list);
            insertUserStatement.setString(5,project);
            insertUserStatement.setString(6,String.valueOf(System.currentTimeMillis()/1000));
            insertUserStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    //根据设备ID查询用户
    private BaseUserEntity selectUserByFirstId(String firstId,String project){
        String key = CacheKeys.getFirstIdByKey(project,firstId);
        Object cacheData = CacheSingletonUtil.getInstance().getCacheData(key);
        if(cacheData!=null){
            log.info("selectUserByFirstId命中缓存{},{},{}",key,firstId,project);
            return (BaseUserEntity) cacheData;
        }
        try {
            selectUserByFirstIdStatement.setString(1,firstId);
            selectUserByFirstIdStatement.setString(2,project);
            ResultSet resultUser = selectUserByFirstIdStatement.executeQuery();
            BaseUserEntity result =null;
            if(resultUser.next()){
                result = new BaseUserEntity(resultUser.getString("user_id"),
                        resultUser.getString("first_id"),
                        resultUser.getString("second_id"),
                        resultUser.getString("$device_id_list"),
                        resultUser.getString("project")
                );
                CacheSingletonUtil.getInstance().addCacheData(key,result);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据业务系统的用户ID查询用户信息
    private BaseUserEntity selectUserBySecondId(String secondId,String project){
        try {
            if(secondId==null){
                return null;
            }
            String key = CacheKeys.getSecondIdByKey(project,secondId);
            Object cacheData = CacheSingletonUtil.getInstance().getCacheData(key);
            if(cacheData!=null){
                log.info("selectUserBySecondId命中缓存{},{},{}",key,secondId,project);
                return (BaseUserEntity) cacheData;
            }
            selectUserBySecondIdStatement.setString(1,secondId);
            selectUserBySecondIdStatement.setString(2,project);
            ResultSet resultUser = selectUserBySecondIdStatement.executeQuery();
            BaseUserEntity result =null;
            if(resultUser!=null && resultUser.next()){
                result = new BaseUserEntity(resultUser.getString("user_id"),
                        resultUser.getString("first_id"),
                        resultUser.getString("second_id"),
                        resultUser.getString("$device_id_list"),
                        resultUser.getString("project")
                );
                CacheSingletonUtil.getInstance().addCacheData(key,result);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据userid更新用户信息
    private boolean updateUserByUserId(BaseUserEntity baseUserEntity){
        try{

           updateUserByUserIdStatement.setString(1,baseUserEntity.getFirst_id());
           updateUserByUserIdStatement.setString(2,baseUserEntity.getSecond_id());
           updateUserByUserIdStatement.setString(3,baseUserEntity.getDevice_id_list());
           updateUserByUserIdStatement.setString(4,baseUserEntity.getUser_id());
            updateUserByUserIdStatement.execute();
            if(StringUtils.isNotBlank(baseUserEntity.getFirst_id())){
                String key = CacheKeys.getFirstIdByKey(baseUserEntity.getProject(),baseUserEntity.getFirst_id());
                CacheSingletonUtil.getInstance().addCacheData(key,baseUserEntity);
                log.info("更新key，updateUserByUserId,{}",key);
            }
            if(StringUtils.isNotBlank(baseUserEntity.getSecond_id())){
                String key = CacheKeys.getSecondIdByKey(baseUserEntity.getUser_id(),baseUserEntity.getSecond_id());
                CacheSingletonUtil.getInstance().addCacheData(key,baseUserEntity);
                log.info("更新key，updateUserByUserId,{}",key);
            }

           return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //用户多端数据操作，数据修复
    private void userTerminalDataRepair(String original_id,String user_id,String project){
        try{
            BaseUserEntity baseUserEntity = selectUserByFirstId(original_id, project);
            if(baseUserEntity!=null && StringUtils.isBlank(baseUserEntity.getSecond_id())){
                updateBaseDataStatement.setString(1,user_id);
                updateBaseDataStatement.setString(2,baseUserEntity.getUser_id());
                updateBaseDataStatement.setString(3,project);
                updateBaseDataStatement.execute();
                deleteUserStatement.setString(1,"1");
                deleteUserStatement.setString(2,baseUserEntity.getUser_id());
                deleteUserStatement.execute();
                String key = CacheKeys.getFirstIdByKey(project,baseUserEntity.getFirst_id());
                CacheSingletonUtil.getInstance().removeCacheData(key);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //处理用户
    private void userCallBack(DataEntity value){
        String project = value.getProject();
        if(StringUtils.isNotBlank(project) && StringUtils.isNotBlank(value.getType())){
            String userId = "";
            String login_id = "";
            //调用登录时，会给此字段，代表登录前的设备ID
            String original_id = value.getOriginal_id();
            //登录前为设备ID，登录后为用户业务系统ID
            String distinctId = value.getDistinct_id();
            //登录处理
            if(StringUtils.equals(value.getType(),"track_signup")){
                login_id = distinctId;
                BaseUserEntity baseUserEntity = selectUserBySecondId(login_id,project);
                if(baseUserEntity!=null){
                    //根据登录ID查到用户，使用此用户的埋点系统ID，且更新此用户对应的设备列表
                    if(!baseUserEntity.getDevice_id_list().contains(original_id)){
                        baseUserEntity.setDevice_id_list(baseUserEntity.getDevice_id_list()+","+original_id);
                        updateUserByUserId(baseUserEntity);
                        //修复数据，神策用户标识，方案3
                        //例如A用户在一台手机设备ID为X，并进行一系列操作，生成Userid，为1，设备IDX，某一天登录了，second_id为A。user表数据 userid:1 distinct_id:X second_id:A
                        //该用户更换了一个新的苹果手机，并进行一系列操作，由于尚未登录，此时神策使用全新的设备 ID Y 来标识此设备，发送的 distinct_id 为 Y，对应分配的神策 ID 为 2，
                        // 若客户端 SDK 调用接口 profile_set ，则将神策 ID 2 、设备 ID Y 存入 users 表的 id, first_id 字段。
                        //该用户在苹果手机上使用账号 A 进行登录，此时神策将设备 ID Y 与登录 ID A 进行关联，关联成功，对应的神策 ID 依然为 1，同时将设备 ID Y 添加到 users 表中神策 ID 1 的 $device_id_list 字段。
                        //该用户登录之后的后续操作，发送的 distinct_id 为 A，所以仍以神策 ID 1 标识
                        //todo 修复方案
                        //修复逻辑，根据当前登录人的original_id(登录前的设备ID)，查找用户，如果userid不等于当前userid说明需要修复
                        //将此前original_id对应userid的数据全部更新至该用户ID上。
                        userTerminalDataRepair(original_id,baseUserEntity.getUser_id(),project);
                    }
                    userId = baseUserEntity.getUser_id();
                }else{
                    //未找到该用户，根据设备ID寻找
                    BaseUserEntity userExistByFirstId = selectUserByFirstId(original_id,project);
                    //根据设备ID找到，用户为空，则将此人与此设备进行绑定，更新操作。
                    if(userExistByFirstId!=null && StringUtils.isBlank(userExistByFirstId.getSecond_id())){
                        userExistByFirstId.setSecond_id(distinctId);
                        userExistByFirstId.setDevice_id_list(original_id);
//                        log.info("开始更新"+value.getEvent()+distinctId);
                        updateUserByUserId(userExistByFirstId);
//                        log.info("更新完成"+value.getEvent()+distinctId);
                        userId = userExistByFirstId.getUser_id();
                    }else{
                        if(userExistByFirstId==null){
                            //正常情况下，不应出现该情况，因测试存在直接
                            userId = insertUser(original_id,distinctId,original_id,project);
                        }else{
                            //根据设备ID找到，但用户ID却不为空说明设备被别的账号绑定，给此人在加一个user记录，使用新的埋点系统ID
                            userId = insertUser(distinctId,distinctId,null,project);
                        }
                    }
                }
            }else{
                //distinctId有可能是登录ID，故而先根据登录ID查询
//                log.info("查询"+value.getEvent()+distinctId);
                BaseUserEntity baseUserEntity = selectUserBySecondId(distinctId,project);
                if(baseUserEntity == null){
                    //根据设备ID查埋点ID，查不到将插入一条新设备在user表中
                    baseUserEntity = selectUserByFirstId(distinctId,project);
                    if(baseUserEntity!=null){
                        userId = baseUserEntity.getUser_id();
                    }else{
                        userId=insertUser(distinctId,"","",project);
//                        log.info("插入"+value.getEvent()+distinctId);
                    }
                }else{
                    userId = baseUserEntity.getUser_id();
                    login_id = baseUserEntity.getSecond_id();
                }
            }
            value.getProperties().setUser_id(userId);
            value.getProperties().set_$is_login_id(login_id);
        }
    }



    @Override
    public void invoke(DataEntity value, SinkFunction.Context context){
        if(!ConfigServerAddressConstants.IS_PROD){
            //非正式环境部署，默认给test项目名称，防止数据重复。
            value.setProject("test");
        }
        //用户ID处理
        userCallBack(value);
        try {
            insertStmt.setString(1,value.getDistinct_id());
            insertStmt.setString(2,value.getEvent());
            if(value.getLib() == null){
                value.setLib(new BaseLibEntity());
            }
            String country = null;
            String province = null;
            String city = null;
            if(StringUtils.isNotBlank(value.getIp())){
                try {
                    String cityInfo = IPUtils.getCityInfo(value.getIp());
//                    log.info("地址"+cityInfo);
                    String[] cityInfoArray = cityInfo.split("\\|");
                    country = cityInfoArray[0];
                    if(StringUtils.equals(country,"0") || StringUtils.equals(country,"|")){
                        country=null;
                    }
                    province = cityInfoArray[2];
                    if(StringUtils.equals(province,"0") || StringUtils.equals(province,"|")){
                        province=null;
                    }
                    city = cityInfoArray[3];
                    if(StringUtils.equals(city,"0") || StringUtils.equals(city,"|")){
                        city=null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            insertStmt.setString(3,value.getLib().get_$lib());
            insertStmt.setString(4,value.getLib().get_$lib_version());
            insertStmt.setString(5,value.getProperties().get_$screen_height());
            insertStmt.setString(6,value.getProperties().get_$screen_width());
            insertStmt.setString(7,value.getProperties().get_$latest_referrer());
            insertStmt.setString(8,value.getProperties().get_$latest_referrer_host());
            insertStmt.setString(9,value.getProperties().get_$latest_traffic_source_type());
            insertStmt.setString(10,value.getProperties().get_$latest_search_keyword());
            insertStmt.setString(11,value.getProperties().get_$referrer());
            insertStmt.setString(12,value.getProperties().get_$referrer_host());
            insertStmt.setString(13,value.getProperties().get_$url());
            insertStmt.setString(14,value.getProperties().get_$url_path());
            insertStmt.setString(15,value.getProperties().get_$title());
            insertStmt.setString(16,value.getProperties().get_$is_first_day());
            insertStmt.setString(17,value.getProperties().get_$is_first_time());
            insertStmt.setString(18,value.getProperties().getEvent_duration());
            insertStmt.setString(19,value.getProperties().get_$element_id());
            insertStmt.setString(20,value.getProperties().get_$element_content());
            insertStmt.setString(21,value.getProperties().get_$element_name());
            insertStmt.setString(22,value.getProperties().get_$element_class_name());
            insertStmt.setString(23,value.getProperties().get_$element_type());
            insertStmt.setString(24,value.getProperties().get_$element_selector());
            insertStmt.setString(25,value.getProperties().get_$element_target_url());
            Long nowTime = System.currentTimeMillis();
            if(value.getTime()==null){
                value.setTime(nowTime);
            }
            insertStmt.setLong(26,value.getTime());
            insertStmt.setLong(27,nowTime);
            insertStmt.setString(28,value.getProperties().getChannel());
            insertStmt.setString(29,value.getProperties().getChannel_type());
            insertStmt.setString(30,value.getProperties().get_$page_x());
            insertStmt.setString(31,value.getProperties().get_$page_y());
            insertStmt.setString(32,value.getProperties().getCommodity_detail_souce());
            insertStmt.setString(33,value.getProperties().getCommodity_id());
            insertStmt.setString(34,value.getProperties().getCommodity_name());
            insertStmt.setString(35,value.getProperties().getCommodity_tag());
            insertStmt.setString(36,value.getProperties().getFirst_commodity());
            insertStmt.setString(37,value.getProperties().getSecond_commodity());
            insertStmt.setString(38,value.getProperties().getOriginal_price());
            insertStmt.setString(39,value.getProperties().getPresent_price());
            insertStmt.setString(40,value.getProperties().getDiscount_price());
            insertStmt.setString(41,value.getProperties().getQuantity_unit());
            insertStmt.setString(42,value.getProperties().getMonetary_unit());
            insertStmt.setString(43,value.getProperties().getStore_id());
            insertStmt.setString(44,value.getProperties().getStore_name());
            insertStmt.setString(45,value.getProperties().getSupplier_id());
            insertStmt.setString(46,value.getProperties().getSupplier_name());
            insertStmt.setString(47,value.getProperties().getService_type());
            insertStmt.setString(48,value.getProperties().getGet_type());
            insertStmt.setString(49,value.getProperties().getIs_success());
            insertStmt.setString(50,value.getProperties().getFail_reason());
            insertStmt.setString(51,value.getProperties().getAccount());
            insertStmt.setString(52,value.getProperties().getLogin_method());
            insertStmt.setString(53,value.getProperties().getIs_quick_login());
            insertStmt.setString(54,value.getProperties().getCommodity_size());
            insertStmt.setString(55,value.getProperties().getCommodity_colour());
            insertStmt.setString(56,value.getProperties().getCommodity_quantity());
            insertStmt.setString(57,value.getProperties().getViewpoint_height());
            insertStmt.setString(58,value.getProperties().getEntrance());
            insertStmt.setString(59,value.getProperties().getOrder_id());
            insertStmt.setString(60,value.getProperties().getOrder_amount());
            insertStmt.setString(61,value.getProperties().getOrder_actual_amount());
            insertStmt.setString(62,value.getProperties().getReceiver_name());
            insertStmt.setString(63,value.getProperties().getReceiver_province());
            insertStmt.setString(64,value.getProperties().getReceiver_city());
            insertStmt.setString(65,value.getProperties().getReceiver_area());
            insertStmt.setString(66,value.getProperties().getReceiver_address());
            insertStmt.setString(67,value.getProperties().getTransportation_costs());
            insertStmt.setString(68,value.getProperties().getDiscount_name());
            insertStmt.setString(69,value.getProperties().getDiscount_amount());
            insertStmt.setString(70,value.getProperties().getIs_use_discount());
            insertStmt.setString(71,value.getProperties().getIf_use_integral());
            insertStmt.setString(72,value.getProperties().getNumber_of_integral());
            insertStmt.setString(73,value.getProperties().getIntegral_discount_amount());
            insertStmt.setString(74,value.getProperties().getIs_cash_back());
            insertStmt.setString(75,value.getProperties().getCash_back_amount());
            insertStmt.setString(76,value.getProperties().getCash_back_name());
            insertStmt.setString(77,value.getProperties().getCommodity_discount_amount());
            insertStmt.setString(78,value.getProperties().getTotal_price_of_commodity());
            insertStmt.setString(79,value.getProperties().getPayment_method());
            insertStmt.setString(80,value.getProperties().getDelivery_method());
            insertStmt.setString(81,value.getProperties().getPage_type());
            insertStmt.setString(82,value.getProperties().getBanner_belong_area());
            insertStmt.setString(83,value.getProperties().getBanner_type());
            insertStmt.setString(84,value.getProperties().getBanner_name());
            insertStmt.setString(85,value.getProperties().getBanner_id());
            insertStmt.setString(86,value.getProperties().getUrl());
            insertStmt.setString(87,value.getProperties().getBanner_rank());
            insertStmt.setString(88,value.getProject());
            insertStmt.setString(89,value.getProperties().getPlatform_type());
            insertStmt.setString(90,value.getProperties().getRegister_method());
            insertStmt.setString(91,value.getType());
            insertStmt.setString(92,value.getAnonymous_id());
            insertStmt.setString(93,value.getLogin_id());
            insertStmt.setString(94,value.getProperties().get_$app_id());
            insertStmt.setString(95,value.getProperties().get_$model());
            insertStmt.setString(96,value.getProperties().get_$manufacturer());
            insertStmt.setString(97,value.getProperties().get_$os());
            insertStmt.setString(98,value.getProperties().get_$os_version());
            insertStmt.setString(99,value.getProperties().get_$is_login_id());
            insertStmt.setString(100,value.getProperties().get_$longitude());
            insertStmt.setString(101,value.getProperties().get_$latitude());
            insertStmt.setString(102,value.getIp());
            insertStmt.setString(103,value.getProperties().get_$timezone_offset());
            insertStmt.setString(104,country);
            insertStmt.setString(105,province);
            insertStmt.setString(106,city);
            insertStmt.setString(107,value.getProperties().get_$network_type());
            insertStmt.setString(108,value.getProperties().get_$browser());
            insertStmt.setString(109,value.getProperties().get_$browser_version());
            insertStmt.setString(110,value.getProperties().get_$scene());
            insertStmt.setString(111,value.getProperties().get_$latest_utm_source());
            insertStmt.setString(112,value.getProperties().get_$latest_utm_medium());
            insertStmt.setString(113,value.getProperties().get_$latest_utm_term());
            insertStmt.setString(114,value.getProperties().get_$latest_utm_content());
            insertStmt.setString(115,value.getProperties().get_$latest_utm_campaign());
            insertStmt.setString(116,value.getProperties().get_$latest_scene());
            insertStmt.setString(117,value.getProperties().get_$url_query());;
            insertStmt.setString(118,value.getProperties().get_$brand());;
            insertStmt.setString(119,value.getProperties().get_$mp_client_app_version());
            insertStmt.setString(120,value.getProperties().get_$mp_client_basic_library_version());
            insertStmt.setString(121,value.getProperties().get_$geo_coordinate_system());
            insertStmt.setString(122,value.getProperties().get_$app_version());
            insertStmt.setString(123,value.getProperties().get_$wifi());
            insertStmt.setString(124,value.getProperties().get_$carrier());
            insertStmt.setString(125,value.getProperties().get_$device_id());
            insertStmt.setString(126,value.getProperties().get_$app_name());
            insertStmt.setString(127,value.getProperties().get_$screen_orientation());
            insertStmt.setString(128,value.getProperties().getUser_id());
            insertStmt.setString(129,value.getIdentities().get_$identity_login_id());
            if(value.getFlume_time()==null){
                value.setFlume_time(value.getTime());
            }
            insertStmt.setLong(130,value.getFlume_time());
            insertStmt.setString(131,value.get_track_id());
            insertStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void close(){
      try{
          if(conn!=null){
              conn.close();
          }
          if(insertStmt!=null){
              insertStmt.close();
          }
          if(insertUserStatement!=null){
              insertUserStatement.close();
          }
          if(selectUserByFirstIdStatement!=null){
              selectUserByFirstIdStatement.close();
          }
          if(selectUserBySecondIdStatement!=null){
              selectUserBySecondIdStatement.close();
          }
          if(updateUserByUserIdStatement!=null){
              updateUserByUserIdStatement.close();
          }
          if(updateBaseDataStatement!=null){
              updateBaseDataStatement.close();
          }
          if(deleteUserStatement!=null){
              deleteUserStatement.close();
          }
      }catch (SQLException e){
          e.printStackTrace();
      }
    }
}
