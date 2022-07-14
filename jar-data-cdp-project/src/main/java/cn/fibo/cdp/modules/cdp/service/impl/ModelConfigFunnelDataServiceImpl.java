package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.enums.*;
import cn.fibo.cdp.common.utils.*;
import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.cdp.dao.ClickhouseBaseDao;
import cn.fibo.cdp.modules.cdp.dao.ModelDataDao;
import cn.fibo.cdp.modules.cdp.entity.*;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitGroupPropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitSelectPropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitWherePropertyParam;
import cn.fibo.cdp.modules.cdp.service.*;
import cn.fibo.cdp.modules.cdp.service.utils.CalculateUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisURI;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 19:02:21
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Service("modelConfigFunnelDataService")
public class ModelConfigFunnelDataServiceImpl extends ServiceImpl<ModelDataDao, ModelDataEntity> implements ModelConfigDataService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelDataEntity> page = this.page(
                new Query<ModelDataEntity>().getPage(params),
                new QueryWrapper<ModelDataEntity>()
        );
        return new PageUtils(page);
    }


    @Autowired
    private ClickhouseBaseDao clickhouseBaseDao;

    @Autowired
    private ModelDataDao modelDataDao;

    @Autowired
    private ModelWhereService modelWhereService;

    @Autowired
    private ModelGroupService modelGroupService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean submitModelData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        //chartType定死
        submitModelAnalysisParam.setChartType(ChartTypeEnums.chart_funnel.getCode());
        //基本信息
        ModelDataEntity modelDataEntity = new ModelDataEntity();
        BeanUtils.copyProperties(submitModelAnalysisParam, modelDataEntity);
        if (modelDataEntity.getId() != null) {
            //如果是修改，删除分路相关表，在进行插入。
            modelDataDao.deleteEventModelData(modelDataEntity.getId());
            String  redisKey = RedisKeys.CDP_DATA_ANALYSIS_CALC.replace("{type}",submitModelAnalysisParam.getType().toString()).replace("{id}",submitModelAnalysisParam.getId().toString());
            redisUtils.delete(redisKey);
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
        return null;
    }

    @Override
    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public Map<String, Object> getCalculateData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        BigDecimal hundred = new BigDecimal(100);
        Map<String,Object> rtnMap = new HashMap<>();
        String startTime = submitModelAnalysisParam.getStartTime();
        String endTime = submitModelAnalysisParam.getEndTime();
        FunnelDataEntity funnelDataEntity = submitModelAnalysisParam.getFunnelDataEntity();
        Integer windowPeriod = funnelDataEntity.getWindowPeriod();
        //根据第一个步骤事件进行分组。
        String tableAliasName = "tb";
        List<FunnelStepDataEntity> funnelStepDataEntityList = funnelDataEntity.getFunnelStepDataEntityList();

        List<SubmitWherePropertyParam> wherePropertyParams = submitModelAnalysisParam.getWherePropertyParams();
        WhereRelationTypeEnums whereRelationTypeEnums = WhereRelationTypeEnums.getObject(submitModelAnalysisParam.getRelation());
        List<SubmitGroupPropertyParam> groupPropertyParams = submitModelAnalysisParam.getGroupPropertyParams();
        boolean isGroup = !CollectionUtils.isEmpty(groupPropertyParams);
        //非当日漏斗或者需要分组查询，需根据漏斗第一个步骤查询存在的日期与分组
        String timeFormatFun = ClickhouseOptions.DATETIME_FORMAT_FUN.replace("{format}", TimeGranularityEnums.day.getDbFormt());
        //根据漏斗第一个步骤查询存在的日期与分组列表
        List<Map<String, Object>> maps = new ArrayList<>();
        StringBuilder selectBuilder = new StringBuilder();
        if(windowPeriod>0 || isGroup){
           selectBuilder.append(timeFormatFun);
           selectBuilder.append(" as dates");
           StringBuilder groupBuilder = new StringBuilder();
           groupBuilder.append(timeFormatFun);
           //全局筛选
           String whereSQL = CalculateUtils.whereSQLAppend(wherePropertyParams, tableAliasName, startTime, endTime,whereRelationTypeEnums);
           //根据步骤中的第一个进行分组
           if(isGroup){
               CalculateUtils.groupSelectSQLAppend(groupPropertyParams,tableAliasName,selectBuilder,groupBuilder);
           }
           //第一个漏斗步骤的事件作为筛选条件，缩小数据范围
           String funnelWhereSQL = CalculateUtils.getFunnelWhereSQL(funnelStepDataEntityList.get(0), tableAliasName);
           whereSQL+=funnelWhereSQL;
           whereSQL+=CalculateUtils.projectWhereSQL(tableAliasName);
           Map<String,Object> params = new HashMap<>();
           params.put("tableAliasName",tableAliasName);
           params.put("tableName",ClickhouseOptions.BASEDATA_TABLE_NAME);
           params.put("selectSQL",selectBuilder.toString());
           params.put("whereSQL",whereSQL);
           params.put("groupBySQL",groupBuilder.toString());
           params.put("orderBySQL","dates asc");
           maps = clickhouseBaseDao.execStandardSql(params);
       }

        //漏斗步骤顺序条件
        StringBuilder funnelEvent = new StringBuilder();
        StringBuilder funnelTotal = new StringBuilder();
        List<String> arrays = new ArrayList<>();
        for (int i = 0; i < funnelStepDataEntityList.size(); i++) {
            funnelEvent.append("(1=1");
            funnelTotal.append("(1=1");
            FunnelStepDataEntity funnelStepDataEntity = funnelStepDataEntityList.get(i);
            funnelEvent.append(CalculateUtils.getFunnelWhereSQL(funnelStepDataEntity, null));
            funnelTotal.append(CalculateUtils.getFunnelWhereSQL(funnelStepDataEntity,null));
            if(i==0){
                if(windowPeriod>0){
                    //非当日漏斗，需定义日期，后续替换只查某一天的漏斗
                    funnelEvent.append(" and "+timeFormatFun+"='{dates}'");
                }
                funnelTotal.append(" and flume_time>= '"+startTime+"' and flume_time<='"+endTime+"'");
                //第一个步骤需要根据分组值进行查询
                if(isGroup){
                    groupPropertyParams.forEach(item->{
                        funnelEvent.append(" and `"+item.getPropertyEn()+"`='{"+item.getPropertyEn()+"}'");
                    });
                }
            }
            funnelEvent.append(")");
            funnelTotal.append(")");
            if(i<funnelStepDataEntityList.size()-1){
                funnelEvent.append(",");
                funnelTotal.append(",");
            }
            //获取所有漏斗中事件名称，后续查询使用，缩小查询范围，提高查询性能
            arrays.addAll(CalculateUtils.getFunnelStepEventNameWhereSQL(funnelStepDataEntity));
        }
        final String funnelStepWhereSql = funnelEvent.toString();
        //漏斗窗口期，计算秒
        Integer windowPeriodSecond = 24*3600;
        if(windowPeriod>0){
            windowPeriodSecond=windowPeriodSecond*windowPeriod;
        }
        //每天漏斗进行查询
        if(maps.size()>0){
            for (Map<String, Object> map : maps) {
                String newFunnelStepWhereSql = funnelStepWhereSql;
                if(windowPeriod>0){
                    String dates = map.get("dates").toString();
                    newFunnelStepWhereSql = newFunnelStepWhereSql.replace("{dates}",dates);
                }
                if(isGroup){
                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        String value = "";
                        if(!ClickhouseOptions.GROUP_BY_NOT_NULL_DEFAULT_VALUE.equals(map.get(key))){
                            value = map.get(key).toString();
                        }
                        newFunnelStepWhereSql = newFunnelStepWhereSql.replace("{"+key+"}",value);
                    }
                }
                Map<String,Object> funnelParams = new HashMap<>();
                funnelParams.put("window",windowPeriodSecond);
                funnelParams.put("isNow",windowPeriod>0?0:1);
                funnelParams.put("timeField",ClickhouseOptions.DATETIME_FIELD);
                funnelParams.put("conds",newFunnelStepWhereSql);
                String whereFunnelSQL = "";
                //外层筛选添加时间条件，时间范围为筛选的开始至（结束+窗口期）
                String plusWindowPeriodDateStr = null;
                try {
                    Date plusWindowPeriodDate  = DateUtils.addDateDays(DateUtils.parseDateStrictly(endTime, DateUtils.parsePatterns), funnelDataEntity.getWindowPeriod());
                    plusWindowPeriodDateStr = DateUtils.format(plusWindowPeriodDate, DateUtils.DATE_TIME_MILLISECOND);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                whereFunnelSQL = CalculateUtils.whereSQLAppend(wherePropertyParams, tableAliasName, startTime, plusWindowPeriodDateStr,whereRelationTypeEnums);
                whereFunnelSQL += CalculateUtils.projectWhereSQL(tableAliasName);
                if(selectBuilder.length()>0){
                    funnelParams.put("selectSQL",selectBuilder.toString());
                }
                funnelParams.put("arrays",arrays);
                funnelParams.put("whereSQL",whereFunnelSQL);
                List<Map<String, Object>> funnelUserCount = clickhouseBaseDao.getFunnelUserCount(funnelParams);
                if(windowPeriod>0){
                    Map<String,Integer> funnelStepMap = new HashMap<>();
                    for (Map<String, Object> stringObjectMap : funnelUserCount) {
                        Integer levelIndex = Integer.valueOf(stringObjectMap.get("level_index").toString());
                        Object numbers = stringObjectMap.get("numbers");
                        funnelStepMap.put("step"+levelIndex,Integer.valueOf(numbers.toString()));
                    }
                    //缺失步骤，补0
                    for(int i =1;i<=funnelStepDataEntityList.size();i++){
                        String nowKey = "step"+i;
                        if(!funnelStepMap.keySet().contains(nowKey)){
                            funnelStepMap.put(nowKey,0);
                        }
                    }
                    map.putAll(funnelStepMap);
                }else{
                    if(!CollectionUtils.isEmpty(funnelUserCount)){
                        Map<String, List<Map<String, Object>>> dates = funnelUserCount.stream().collect(Collectors.groupingBy(e -> e.get("dates").toString()));
                        Set<String> dateSetKeys = dates.keySet();
                        for (String dateKey : dateSetKeys) {
                            Map<String,Object> dateMap =  new HashMap<>(16);
                            List<Map<String, Object>> maps1 = dates.get(dateKey);
                            rtnMap.put("dates",dateKey);
                            for (Map<String, Object> stringObjectMap : maps1) {
                                Integer levelIndex = Integer.valueOf(stringObjectMap.get("level_index").toString());
                                Object numbers = stringObjectMap.get("numbers");
                                dateMap.put("step"+levelIndex,Integer.valueOf(numbers.toString()));
                            }
                            for(int i =1;i<=funnelStepDataEntityList.size();i++){
                                String stepName = "step"+i;
                                if(!dateMap.keySet().contains(stepName)){
                                    dateMap.put(stepName,0);
                                    if(i>1){
                                        dateMap.put("rate-"+stepName,0);
                                    }
                                }else{
                                    if(i>1){
                                        BigDecimal preDateStep = new BigDecimal(dateMap.get("step"+(i-1)).toString());
                                        BigDecimal nowDateStep = new BigDecimal(dateMap.get(stepName).toString());
                                        if(preDateStep.compareTo(BigDecimal.ZERO)!=0){
                                            //计算上一步的比例
                                            dateMap.put("rate"+stepName,nowDateStep.divide(preDateStep,4,BigDecimal.ROUND_HALF_UP).multiply(hundred));
                                        }else{
                                            dateMap.put("rate"+stepName,0);
                                        }
                                    }
                                }
                            }
                            //计算该日总体转换比例
                            BigDecimal lastDateStep = new BigDecimal(dateMap.get("step"+funnelStepDataEntityList.size()).toString());
                            BigDecimal firstDateStep = new BigDecimal(dateMap.get("step1").toString());
                            dateMap.put("total",lastDateStep.divide(firstDateStep,4,BigDecimal.ROUND_HALF_UP).multiply(hundred));
                            map.putAll(dateMap);
                        }
                    }
                }
            }
        }else{
            Map<String,Object> funnelParams = new HashMap<>();
            funnelParams.put("window",windowPeriodSecond);
            funnelParams.put("timeField",ClickhouseOptions.DATETIME_FIELD);
            funnelParams.put("isNow",windowPeriod>0?0:1);
            funnelParams.put("conds",funnelStepWhereSql);
            String whereFunnelSQL = "";
            whereFunnelSQL = CalculateUtils.whereSQLAppend(wherePropertyParams, tableAliasName, startTime, endTime,whereRelationTypeEnums);
            whereFunnelSQL += CalculateUtils.projectWhereSQL(tableAliasName);
            if(selectBuilder.length()>0){
                funnelParams.put("selectSQL",selectBuilder.toString());
            }
            funnelParams.put("arrays",arrays);
            funnelParams.put("whereSQL",whereFunnelSQL);
            List<Map<String, Object>> funnelUserCount = clickhouseBaseDao.getFunnelUserCount(funnelParams);
            if(!CollectionUtils.isEmpty(funnelUserCount)){
                Map<String, List<Map<String, Object>>> dates = funnelUserCount.stream().collect(Collectors.groupingBy(e -> e.get("dates").toString()));
                Set<String> dateSetKeys = dates.keySet();
                for (String dateKey : dateSetKeys) {
                    Map<String,Object> dateMap =  new HashMap<>(16);
                    List<Map<String, Object>> maps1 = dates.get(dateKey);
                    dateMap.put("dates",dateKey);
                    for (Map<String, Object> stringObjectMap : maps1) {
                        Integer levelIndex = Integer.valueOf(stringObjectMap.get("level_index").toString());
                        Integer numbers = Integer.valueOf(stringObjectMap.get("numbers").toString());
                        dateMap.put("step"+levelIndex,numbers);
                    }
                    for(int i =1;i<=funnelStepDataEntityList.size();i++){
                        String stepName = "step"+i;
                        if(!dateMap.keySet().contains(stepName)){
                            dateMap.put(stepName,0);
                            if(i>1){
                                dateMap.put("rate-"+stepName,0);
                            }
                        }else{
                            if(i>1){
                                BigDecimal preDateStep = new BigDecimal(dateMap.get("step"+(i-1)).toString());
                                BigDecimal nowDateStep = new BigDecimal(dateMap.get(stepName).toString());
                                if(preDateStep.compareTo(BigDecimal.ZERO)!=0){
                                    //计算上一步的比例
                                    dateMap.put("rate"+stepName,nowDateStep.divide(preDateStep,4,BigDecimal.ROUND_HALF_UP).multiply(hundred));
                                }else{
                                    dateMap.put("rate"+stepName,0);
                                }
                            }
                        }
                    }
                    //计算该日总体转换比例
                   BigDecimal lastDateStep = new BigDecimal(dateMap.get("step"+funnelStepDataEntityList.size()).toString());
                    BigDecimal firstDateStep = new BigDecimal(dateMap.get("step1").toString());
                    dateMap.put("total",lastDateStep.divide(firstDateStep,4,BigDecimal.ROUND_HALF_UP).multiply(hundred));
                    maps.add(dateMap);
                }
            }
        }

        //总体漏斗
        Map<String,Object> funnelParams = new HashMap<>();
        funnelParams.put("window",windowPeriodSecond);
        funnelParams.put("timeField",ClickhouseOptions.DATETIME_FIELD);
        funnelParams.put("conds",funnelTotal.toString());
        String whereFunnelSQL = "";
        whereFunnelSQL = CalculateUtils.whereSQLAppend(wherePropertyParams, tableAliasName, startTime, endTime,whereRelationTypeEnums);
        funnelParams.put("arrays",arrays);
        funnelParams.put("whereSQL",whereFunnelSQL);
        List<Map<String, Object>> funnelUserCount = clickhouseBaseDao.getFunnelUserCount(funnelParams);
        Map<String,Integer> funnelStepMap = new HashMap<>();
        //每个步骤，默认给0
        for(int i =1;i<=funnelStepDataEntityList.size();i++){
            String nowKey = "step"+i;
            funnelStepMap.put(nowKey,0);
            if(!CollectionUtils.isEmpty(funnelUserCount)){
                for (Map<String, Object> stringObjectMap : funnelUserCount) {
                    Integer levelIndex = Integer.valueOf(stringObjectMap.get("level_index").toString());
                    if(levelIndex == i){
                        Object numbers = stringObjectMap.get("numbers");
                        funnelStepMap.put(nowKey,Integer.valueOf(numbers.toString()));
                        break;
                    }
                }
            }
        }
        rtnMap.put("total",funnelStepMap);
        try {
            SubmitGroupPropertyParam submitGroupPropertyParam = null;
            if(!CollectionUtils.isEmpty(groupPropertyParams)){
                submitGroupPropertyParam = groupPropertyParams.get(0);
            }
            //补0+排序
            List<String> stringList = dataFullZero(maps, funnelStepDataEntityList,submitGroupPropertyParam, startTime, endTime);
            rtnMap.put("times",stringList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        rtnMap.put("datas",maps);
        return rtnMap;
    }

    @Override
    public void exportExcel(SubmitModelAnalysisParam submitModelAnalysisParam, HttpServletResponse response,String fileName) {

    }


    //补0
    public List<String> dataFullZero(List<Map<String,Object>> lists,List<FunnelStepDataEntity> funnelStepDataEntityList,
                                     SubmitGroupPropertyParam submitGroupPropertyParam,String startDate,String endDate) throws ParseException {
        List<String> dates = DateUtils.getDays(startDate,endDate,true);
        List<String> names = new ArrayList<>();
        if(submitGroupPropertyParam!=null){
            names= lists.stream().map(s->String.valueOf(s.get(submitGroupPropertyParam.getPropertyEn()))).collect(Collectors.toList());
        }
        for (String date : dates) {
            if(names.size()>0){
                for (String name : names) {
                    boolean isExist = false;
                    for (Map<String, Object> list : lists) {
                        if(date.equals(list.get("dates").toString()) && name.equals(list.get(submitGroupPropertyParam.getPropertyEn()))){
                            isExist=true;
                            break;
                        }
                    }
                    if(!isExist){
                        Map<String,Object> addOneMap = new HashMap<>();
                        addOneMap.put("dates",date);
                        addOneMap.put(submitGroupPropertyParam.getPropertyEn(),name);
                        for(int i=1;i<=funnelStepDataEntityList.size();i++){
                            addOneMap.put("step"+i,0);
                        }
                        lists.add(addOneMap);
                    }
                }
            }else{
                boolean isExist = false;
                for (Map<String, Object> list : lists) {
                    if(date.equals(list.get("dates").toString())){
                        isExist=true;
                    }
                }
                if(!isExist){
                    Map<String,Object> addOneMap = new HashMap<>();
                    addOneMap.put("dates",date);
                    for(int i=1;i<=funnelStepDataEntityList.size();i++){
                        addOneMap.put("step"+i,0);
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

}
