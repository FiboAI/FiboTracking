package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.common.constants.EventDataAnalysisConstants;
import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.enums.*;
import cn.fibo.cdp.common.utils.*;
import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.cdp.dao.ClickhouseBaseDao;
import cn.fibo.cdp.modules.cdp.dao.ModelDataDao;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelGroupEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelSelectEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelWhereEntity;
import cn.fibo.cdp.modules.cdp.entity.param.*;
import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
import cn.fibo.cdp.modules.cdp.service.ModelGroupService;
import cn.fibo.cdp.modules.cdp.service.ModelSelectService;
import cn.fibo.cdp.modules.cdp.service.ModelWhereService;
import cn.fibo.cdp.modules.cdp.service.utils.CalculateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 18:58:37
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Service("modelConfigEventDataService")
public class ModelConfigEventDataServiceImpl extends ServiceImpl<ModelDataDao, ModelDataEntity> implements ModelConfigDataService {

    @Autowired
    private ModelWhereService modelWhereService;

    @Autowired
    private ModelDataDao modelDataDao;

    @Autowired
    private ModelSelectService modelSelectService;

    @Autowired
    private ModelGroupService modelGroupService;

    @Autowired
    private ClickhouseBaseDao clickhouseBaseDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelDataEntity> page = this.page(
                new Query<ModelDataEntity>().getPage(params),
                new QueryWrapper<ModelDataEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public boolean submitModelData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        //基本信息
        ModelDataEntity modelDataEntity = new ModelDataEntity();
        BeanUtils.copyProperties(submitModelAnalysisParam, modelDataEntity);
        String startTime = submitModelAnalysisParam.getStartTime();
        String endTime = submitModelAnalysisParam.getEndTime();
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
                String[] startTimeArray = startTime.split(" ");
                if(startTimeArray.length==1){
                    startTime += " 00:00:00.000";
                }
                String[] endTimeArray = endTime.split(" ");
                if(endTimeArray.length==1){
                    endTime += " 23:59:59.999";
                }
                modelDataEntity.setStartTime(startTime);
                modelDataEntity.setEndTime(endTime);
        }
        if (modelDataEntity.getId() != null) {
            //修改模型，清除缓存
         String  redisKey = RedisKeys.CDP_DATA_ANALYSIS_CALC.replace("{type}",submitModelAnalysisParam.getType().toString()).replace("{id}",submitModelAnalysisParam.getId().toString());
            redisUtils.delete(redisKey);
            //如果是修改，删除分路相关表，在进行插入。
            modelDataDao.deleteEventModelData(modelDataEntity.getId());
        }
        //全局筛选 where
        String allWhereUUid;
        List<SubmitWherePropertyParam> wherePropertyParams = submitModelAnalysisParam.getWherePropertyParams();
        if (!CollectionUtils.isEmpty(wherePropertyParams)) {
            allWhereUUid = StringUtils.getUUID();
            List<ModelWhereEntity> modelWhereEntityList = new ArrayList<>();
            modelDataEntity.setWhereUuid(allWhereUUid);
            for (SubmitWherePropertyParam item : wherePropertyParams) {
                ModelWhereEntity modelWhereEntity = new ModelWhereEntity();
                BeanUtils.copyProperties(item, modelWhereEntity);
                modelWhereEntity.setSource(WhereSourceEnums.model_global.getCode());
                modelWhereEntity.setUuid(allWhereUUid);
                modelWhereEntityList.add(modelWhereEntity);
            }
            modelWhereService.saveBatch(modelWhereEntityList);
        }
        this.saveOrUpdate(modelDataEntity);
        //统计属性 select
        if (!CollectionUtils.isEmpty(submitModelAnalysisParam.getSelectPropertyParams())) {
            List<ModelSelectEntity> modelSelectEntities = new ArrayList<>();
            submitModelAnalysisParam.getSelectPropertyParams().forEach(item -> {
                ModelSelectEntity modelSelectEntity = new ModelSelectEntity();
                BeanUtils.copyProperties(item, modelSelectEntity);
                modelSelectEntity.setModelId(modelDataEntity.getId());
                modelSelectEntities.add(modelSelectEntity);
            });
            modelSelectService.saveBatch(modelSelectEntities);
        }

        //根据什么属性查看 group
        if (!CollectionUtils.isEmpty(submitModelAnalysisParam.getGroupPropertyParams())) {
            List<ModelGroupEntity> modelGroupEntities = new ArrayList<>();
            submitModelAnalysisParam.getGroupPropertyParams().forEach(item -> {
                ModelGroupEntity modelGroupEntity = new ModelGroupEntity();
                BeanUtils.copyProperties(item, modelGroupEntity);
                modelGroupEntity.setModelId(modelDataEntity.getId());
                modelGroupEntities.add(modelGroupEntity);
            });
            modelGroupService.saveBatch(modelGroupEntities);
        }
        return true;
    }

    @Override
    public SubmitModelAnalysisParam getModelDetail(Long id) {
        return modelDataDao.getModelEventDetail(id);
    }






    @Override
    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public Map<String, Object> getCalculateData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        /**
         * SQL 生成规则，生成虚拟临时表tb与tb1，其他指标的结果进行left join
         *
         * select ... from  (  tb ) as tb1
         * left join ( ) tb2 ...
         * left join () tb3 ...s
         * ...
         */
        List<SubmitSelectPropertyParam> selectPropertyParams = submitModelAnalysisParam.getSelectPropertyParams();
        List<SubmitGroupPropertyParam> groupPropertyParams = submitModelAnalysisParam.getGroupPropertyParams();
        List<SubmitWherePropertyParam> wherePropertyParams = submitModelAnalysisParam.getWherePropertyParams();
        WhereRelationTypeEnums whereRelationTypeEnums = WhereRelationTypeEnums.getObject(submitModelAnalysisParam.getRelation());
        //时间粒度
        TimeGranularityEnums timeGranularityEnums = TimeGranularityEnums.getByCode(submitModelAnalysisParam.getTimeGranularity());
        //开始时间
        String startTime = submitModelAnalysisParam.getStartTime();
        //结束时间
        String endTime = submitModelAnalysisParam.getEndTime();
        Map<String, String> stringStringMap = CalculateUtils.selectSQLAppend(selectPropertyParams, groupPropertyParams, wherePropertyParams, timeGranularityEnums.getDbFormt(),
                startTime, endTime, EventDataAnalysisConstants.mainTableAliasName,whereRelationTypeEnums);
        StringBuilder tbSelectSQL = new StringBuilder();
        StringBuilder tbGroupSQL = new StringBuilder();
        StringBuilder tbWhereSQL = new StringBuilder();
        String sqlTemplate = EventDataAnalysisConstants.sqlMainTemplate;
        sqlTemplate = sqlTemplate.replace("{format}",timeGranularityEnums.getDbFormt());
        CalculateUtils.groupSelectSQLAppend(groupPropertyParams, EventDataAnalysisConstants.mainTableDataAliasName,tbSelectSQL,tbGroupSQL);
        tbWhereSQL.append(CalculateUtils.whereSQLAppend(wherePropertyParams,EventDataAnalysisConstants.mainTableDataAliasName,startTime,endTime,whereRelationTypeEnums));
        //获取所有需要统计的事件英文名
        String eventInWhereSQL = CalculateUtils.getEventSelectListWhereSQL(selectPropertyParams,EventDataAnalysisConstants.mainTableDataAliasName);
        tbWhereSQL.append(eventInWhereSQL);
        sqlTemplate = sqlTemplate.replace("{whereSQL}",tbWhereSQL);
        sqlTemplate = sqlTemplate.replace("{groupSQL}",tbGroupSQL);
        sqlTemplate = sqlTemplate.replace("{selectSQL}",tbSelectSQL);

        StringBuilder tb1SelectSQL = new StringBuilder();
        StringBuilder tb1GroupSQL = new StringBuilder();
        CalculateUtils.groupSelectSQLAppend(groupPropertyParams, EventDataAnalysisConstants.mainTableAliasName,tb1SelectSQL,tb1GroupSQL);
        Set<String> stringKeys = stringStringMap.keySet();
        String groupKeys = "";
        for (String stringKey : stringKeys) {
           groupKeys+=","+stringKey+" as "+stringKey.split("\\.")[1];
           sqlTemplate+=stringStringMap.get(stringKey);
        }
        tb1SelectSQL.append(groupKeys);
        sqlTemplate = sqlTemplate.replace("{tb1SelectSQL}",tb1SelectSQL);
        //根据时间排序
         sqlTemplate+=" order by "+EventDataAnalysisConstants.mainTableAliasName+".dates asc";
        List<Map<String, Object>> maps = clickhouseBaseDao.execSql(sqlTemplate);
        Map<String,Object> datas = new HashMap<>();
        try {
            //分组英文key转换中文
            if(!CollectionUtils.isEmpty(groupPropertyParams)){
                propertyEnConvertCn(maps, groupPropertyParams);
            }
            //补0+排序
            List<String> stringList = dataFullZero(maps, selectPropertyParams, groupPropertyParams, startTime, endTime, timeGranularityEnums);
            datas.put("times",stringList);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datas.put("datas",maps);
        //计算同环比
        //计算时间周期内的最后一个时间节点的同环比
        //取select中的第一个，只有一个计算量指标才能计算同环比
        SubmitSelectPropertyParam submitSelectPropertyParam = selectPropertyParams.get(0);
        //取最后一个日期的日期与值
        Map<String, Object> stringObjectMap = maps.get(maps.size() - 1);
        String dates = stringObjectMap.get("dates").toString();
        String values = stringObjectMap.get(submitSelectPropertyParam.getName()).toString();
        BigDecimal nowValue = new BigDecimal(values);
        if(submitModelAnalysisParam.isTempIsCalcYearToYearCondions()){
            //获得同期日期
            Map<String,String> yearToyearMap = CalculateUtils.getYearToYearDates(dates,timeGranularityEnums);
            String preChainDate = yearToyearMap.get("preDateChainValue");
            String preYearToYearDate = yearToyearMap.get("preDateYearToYearValue");
            SelectCalcTypeEnums selectCalcTypeEnums = SelectCalcTypeEnums.getByCode(submitSelectPropertyParam.getCalcType());
            BigDecimal preChainValue =calcPreValue(selectCalcTypeEnums,
                    submitSelectPropertyParam,
                    timeGranularityEnums.getDbFormt(),
                    preChainDate,wherePropertyParams,whereRelationTypeEnums);
            BigDecimal preYearToYearValue = calcPreValue(selectCalcTypeEnums,
                    submitSelectPropertyParam,
                    timeGranularityEnums.getDbFormt(),
                    preYearToYearDate,wherePropertyParams,whereRelationTypeEnums);
            BigDecimal preChainRate = BigDecimal.ZERO;
            if(preChainValue.compareTo(BigDecimal.ZERO)!=0){
                preChainRate= (nowValue.subtract(preChainValue)).divide(preChainValue,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            }
            BigDecimal preYearToYearRate = BigDecimal.ZERO;
            if(preYearToYearValue.compareTo(BigDecimal.ZERO)!=0){
                preYearToYearRate= (nowValue.subtract(preYearToYearValue)).divide(preYearToYearValue,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            }
            //
//            datas.put("start_time",startTime);
//            datas.put("end_time",endTime);
            //环比
            Map<String,Object> preChainMap = new HashMap<>();
            preChainMap.put("preChainRate",preChainRate);
            preChainMap.put("preChainDate",preChainDate);
            preChainMap.put("preChainValue",preChainValue);
            datas.put("preChainMap",preChainMap);
            //同比
            Map<String,Object> preYearToYearMap = new HashMap<>();
            preYearToYearMap.put("preYearToYearRate",preYearToYearRate);
            preYearToYearMap.put("preYearToYearDate",preYearToYearDate);
            preYearToYearMap.put("preYearToYearValue",preYearToYearValue);
            datas.put("preYearToYearMap",preYearToYearMap);
        }
        //本期
        Map<String,Object> nowMap = new HashMap<>();
        nowMap.put("nowDate",dates);
        nowMap.put("nowDateValue",nowValue);
        datas.put("nowMap",nowMap);
        return datas;
    }

    @Override
    public void exportExcel(SubmitModelAnalysisParam submitModelAnalysisParam, HttpServletResponse response,String fileName) {

    }


    //分组key值转换为中文
    public void propertyEnConvertCn(List<Map<String,Object>> lists,
                                    List<SubmitGroupPropertyParam> submitGroupPropertyParams){
        Map<String, String> maps = submitGroupPropertyParams.stream().collect(Collectors.toMap(SubmitGroupPropertyParam::getPropertyEn, SubmitGroupPropertyParam::getPropertyCn, (key1, key2) -> key2));
        Set<String> propertyEnKeys = maps.keySet();
        for (Map<String, Object> item : lists) {
            for (String propertyEnKey : propertyEnKeys) {
                if(item.containsKey(propertyEnKey)){
                    item.put(maps.get(propertyEnKey),item.get(propertyEnKey).toString());
                    item.remove(propertyEnKey);
                }
            }
        }
    }



    //数据日期补0
    public List<String> dataFullZero(List<Map<String,Object>> lists,
                             List<SubmitSelectPropertyParam> selectPropertyParams,
                             List<SubmitGroupPropertyParam> submitGroupPropertyParams,String startDate,String endDate,TimeGranularityEnums timeGranularityEnums) throws ParseException {
        List<String> dates = new ArrayList<>();
        switch (timeGranularityEnums){
            case month:
                dates = DateUtils.getMonths(startDate,endDate,true);
                break;
            case day:
                dates = DateUtils.getDays(startDate,endDate,true);
                break;
            case hour:
                dates = DateUtils.getHours(startDate,endDate,true);
                break;
            case minute:
                dates = DateUtils.getMinutes(startDate,endDate,true);
                break;
        }
            if(!CollectionUtils.isEmpty(submitGroupPropertyParams)){
                //遍历数据，记录每一个分组数据对应的日期列表
                Map<String,List<String>> groupMap = new HashMap<>();
                for (Map<String, Object> map : lists) {
                    List<String> groupStrArray = new ArrayList<>();;
                    for (SubmitGroupPropertyParam submitGroupPropertyParam : submitGroupPropertyParams) {
                        groupStrArray.add(map.get(submitGroupPropertyParam.getPropertyCn()).toString());
                    }
                    String groupStr = groupStrArray.stream().collect(Collectors.joining(" - "));
                    List<String> values = groupMap.getOrDefault(groupStr, new ArrayList<>());
                    values.add(map.get("dates").toString());
                    groupMap.put(groupStr,values);
                }
                Set<String> groupKeySet = groupMap.keySet();
                for (String groupKey : groupKeySet) {
                    List<String> stringList = groupMap.get(groupKey);
                    for (String date : dates) {
                        if(!stringList.contains(date)){
                            Map<String,Object> addOneMap = new HashMap<>();
                            addOneMap.put("dates",date);
                            String[] split = groupKey.split(" - ");
                            for (int i = 0; i < split.length; i++) {
                                String key =  submitGroupPropertyParams.get(i).getPropertyCn();
                                String value = split[i];
                                addOneMap.put(key,value);
                            }
                            for (SubmitSelectPropertyParam selectPropertyParam : selectPropertyParams) {
                                addOneMap.put(selectPropertyParam.getName(),0);
                            }
                            lists.add(addOneMap);
                        }
                    }
                }
            }else{
                for (String date : dates) {
                    boolean isExist = false;
                    for (Map<String, Object> list : lists) {
                        if(date.equals(list.get("dates").toString())){
                            isExist=true;
                            break;
                        }
                    }
                    if(!isExist){
                        Map<String,Object> addOneMap = new HashMap<>();
                        addOneMap.put("dates",date);
                        for (SubmitSelectPropertyParam selectPropertyParam : selectPropertyParams) {
                            addOneMap.put(selectPropertyParam.getName(),0);
                        }
                        lists.add(addOneMap);
                    }
            }
        }
        Collections.sort(lists,new Comparator<Map<String, Object>>(){
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2){
                return o1.get("dates").toString().compareTo(o2.get("dates").toString());
            }
        });
        return dates;
    }



    //同环比计算
    private BigDecimal calcPreValue(SelectCalcTypeEnums selectCalcTypeEnums,SubmitSelectPropertyParam submitSelectPropertyParam,
                                    String dbFormat,String value,List<SubmitWherePropertyParam> wherePropertyParams,WhereRelationTypeEnums whereRelationTypeEnums
                                    ){
        Map<String,Object> chainValueParams = new HashMap<>();
        String aggregateFunctionStr = CalculateUtils.getAggregateFunctionStr(selectCalcTypeEnums, submitSelectPropertyParam.getPropertyEn(),submitSelectPropertyParam.getPropertyCn());
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append("where project = '"+ClickhouseOptions.PROJECT+"'");
        sqlWhere.append(" and ");
        sqlWhere.append(ClickhouseOptions.DATETIME_FORMAT_FUN.replace("{format}",dbFormat)+"="+"'"+value+"'");
        sqlWhere.append(CalculateUtils.whereSQLAppend(wherePropertyParams,null,null,null,whereRelationTypeEnums));
        sqlWhere.append(CalculateUtils.virturalSqlStatement(null,submitSelectPropertyParam.getVirturalEventDetailParams(),submitSelectPropertyParam.getEventEn(),null));
        chainValueParams.put("function",aggregateFunctionStr);
        chainValueParams.put("whereSQL",sqlWhere.toString());
        BigDecimal preChainValue = clickhouseBaseDao.getYearToYearValue(chainValueParams);
        return preChainValue;
    }



}
