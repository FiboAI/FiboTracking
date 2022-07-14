package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.common.constants.KeepDataAnalysisConstants;
import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.enums.*;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;
import cn.fibo.cdp.common.utils.RedisUtils;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.cdp.dao.KeepDataDao;
import cn.fibo.cdp.modules.cdp.dao.ModelDataDao;
import cn.fibo.cdp.modules.cdp.dao.clickhouse.keep.ClickhouseKeepDataDao;
import cn.fibo.cdp.modules.cdp.entity.KeepDataEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelGroupEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelWhereEntity;
import cn.fibo.cdp.modules.cdp.entity.dto.CalcKeepDataRtnDto;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitGroupPropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitKeepEventParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitWherePropertyParam;
import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
import cn.fibo.cdp.modules.cdp.service.ModelGroupService;
import cn.fibo.cdp.modules.cdp.service.ModelWhereService;
import cn.fibo.cdp.modules.cdp.service.utils.CalculateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 19:13:07
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Service("modelConfigKeepDataService")
public class ModelConfigKeepDataServiceImpl extends ServiceImpl<ModelDataDao, ModelDataEntity> implements ModelConfigDataService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelDataEntity> page = this.page(
                new Query<ModelDataEntity>().getPage(params),
                new QueryWrapper<ModelDataEntity>()
        );
        return new PageUtils(page);
    }



    @Autowired
    private ClickhouseKeepDataDao clickhouseKeepDataDao;

    @Autowired
    private ModelDataDao modelDataDao;

    @Autowired
    private ModelWhereService modelWhereService;

    @Autowired
    private ModelGroupService modelGroupService;


    @Autowired
    private KeepDataDao keepDataDao;

    @Autowired
    private RedisUtils redisUtils;



    @Override
    public boolean submitModelData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        //chartType定死
        submitModelAnalysisParam.setChartType(ChartTypeEnums.chart_keep.getCode());
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
        //起始行为、后续行为的where条件
        SubmitKeepEventParam keepEventParam = submitModelAnalysisParam.getKeepEventParam();
        KeepDataEntity keepDataEntity = new KeepDataEntity();
        keepDataEntity.setStartEvent(keepEventParam.getFirstEventEn());
        keepDataEntity.setEndEvent(keepEventParam.getLastEventEn());
        keepDataEntity.setModelId(modelDataEntity.getId());
        String firstWhereUuid;
        String lastWhereUuid;
        List<ModelWhereEntity> keepEventWhereList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(keepEventParam.getFirstWherePropertyParamList())){
            firstWhereUuid = StringUtils.getUUID();
            keepEventParam.getFirstWherePropertyParamList().forEach(item ->{
                ModelWhereEntity modelWhereEntity = new ModelWhereEntity();
                BeanUtils.copyProperties(item, modelWhereEntity);
                modelWhereEntity.setUuid(firstWhereUuid);
                modelWhereEntity.setSource(WhereSourceEnums.keep_event.getCode());
                keepEventWhereList.add(modelWhereEntity);
            });
        }
        if(!CollectionUtils.isEmpty(keepEventParam.getLastWherePropertyParamList())){
            lastWhereUuid = StringUtils.getUUID();
            keepEventParam.getLastWherePropertyParamList().forEach(item->{
                ModelWhereEntity modelWhereEntity = new ModelWhereEntity();
                BeanUtils.copyProperties(item, modelWhereEntity);
                modelWhereEntity.setUuid(lastWhereUuid);
                modelWhereEntity.setSource(WhereSourceEnums.keep_event.getCode());
                keepEventWhereList.add(modelWhereEntity);
            });
        }
        if(!CollectionUtils.isEmpty(keepEventWhereList)){
            modelWhereService.saveBatch(keepEventWhereList);
        }
        keepDataDao.insert(keepDataEntity);
       return true;
    }

    @Override
    public SubmitModelAnalysisParam getModelDetail(Long id) {
        return null;
    }

    @Override
    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public Map<String, Object> getCalculateData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        Map<String,Object> rtnMap = new HashMap<>();
        String startTime =submitModelAnalysisParam.getStartTime();
        String endTime = submitModelAnalysisParam.getEndTime();
        SubmitKeepEventParam keepEventParam = submitModelAnalysisParam.getKeepEventParam();
        List<SubmitWherePropertyParam> wherePropertyParams = submitModelAnalysisParam.getWherePropertyParams();
        List<SubmitGroupPropertyParam> groupPropertyParams = submitModelAnalysisParam.getGroupPropertyParams();
        String timeGranularity = submitModelAnalysisParam.getTimeGranularity();
        TimeGranularityEnums timeGranularityEnums = TimeGranularityEnums.valueOf(timeGranularity);
        Integer timeValue = submitModelAnalysisParam.getTimeValue();
        StringBuilder groupSelectBuilder = new StringBuilder();
        StringBuilder groupByBuilder = new StringBuilder();
        CalculateUtils.groupSelectSQLAppend(groupPropertyParams,null,groupSelectBuilder,groupByBuilder);
        String globalWhere =CalculateUtils.whereSQLAppend(wherePropertyParams,null,null,null, WhereRelationTypeEnums.getObject(submitModelAnalysisParam.getRelation()));
       String firstWhereStr = CalculateUtils.virturalSqlStatement(null,keepEventParam.getFirstVirturalEventDetailParams(),keepEventParam.getFirstEventEn(),keepEventParam.getFirstWherePropertyParamList());
        String lastWhereStr = CalculateUtils.virturalSqlStatement(null,keepEventParam.getLastVirturalEventDetailParams(),keepEventParam.getLastEventEn(),keepEventParam.getLastWherePropertyParamList());
       Map<String,Object> sqlParams = new HashMap();
        sqlParams.put("startTime",startTime);
        sqlParams.put("endTime",endTime);
        sqlParams.put("timeGranularity",timeGranularityEnums.getCode());
        int[] sizes = new int[timeValue+1];
        sqlParams.put("timeValue",timeValue);
        sqlParams.put("timeValueArray",sizes);
        sqlParams.put("dbDateFormat",timeGranularityEnums.getDbFormt());
        sqlParams.put("firstWhereSQL",firstWhereStr+globalWhere);
        sqlParams.put("lastWhereSQL",lastWhereStr+globalWhere);
        if(groupByBuilder.length()>0){
            sqlParams.put("groupBySQL",groupByBuilder.toString());
        }
        if(groupSelectBuilder.length()>0){
            sqlParams.put("groupSelectSQL",groupSelectBuilder.toString());
        }
        List<Map<String, Object>> keepDataUserCount = clickhouseKeepDataDao.getKeepDataUserCount(sqlParams);
        List<Map<String,Object>> dataMap = new ArrayList<>();
        //留存表，表头生成
        List<Map<String,Object>> columnMaps = new ArrayList<>();
        //不分组则查总体
        if(CollectionUtils.isEmpty(groupPropertyParams)){
            Map<String,Object> columnGroupItem = new HashMap<>();
            columnGroupItem.put("title",KeepDataAnalysisConstants.NONE_GROUP_TEXT);
            columnGroupItem.put("dataIndex",KeepDataAnalysisConstants.GROUP_STR_KEY);
            columnMaps.add(columnGroupItem);
            Map<String,Object> columnDatesGroupItem = new HashMap<>();
            columnDatesGroupItem.put("title",KeepDataAnalysisConstants.DATE_KEY_TEXT);
            columnDatesGroupItem.put("dataIndex",KeepDataAnalysisConstants.DATE_KEY);
            columnMaps.add(columnDatesGroupItem);
            //访问起始行为的总人数（去重）
            Integer keepDataTotalUserCount = clickhouseKeepDataDao.getKeepDataTotalUserCount(sqlParams);
            //未来每日留存人数（去重）
            List<Map<String, Object>> keepDataRetentionUserCountGroup = clickhouseKeepDataDao.getKeepDataRetentionUserCountGroup(sqlParams);
            Map<String,Object> totalRtnMap = new HashMap<>();
            for (Map<String, Object> stringObjectMap : keepDataRetentionUserCountGroup) {
                totalRtnMap.put(KeepDataAnalysisConstants.MORROW_PREFIX_KEY+stringObjectMap.get(KeepDataAnalysisConstants.DATEDIFF_KEY),
                        stringObjectMap.get(KeepDataAnalysisConstants.DATE_TOTAL_USER_COUNT_KEY));
            }
            totalRtnMap.put(KeepDataAnalysisConstants.DATE_TOTAL_USER_COUNT_KEY,keepDataTotalUserCount);
            totalRtnMap.put(KeepDataAnalysisConstants.GROUP_STR_KEY,KeepDataAnalysisConstants.NONE_GROUP_TEXT);
            Map<String,BigDecimal> totalMap = new HashMap<>();
            Map<String,BigDecimal> totalFisrtMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(keepDataUserCount)){
                //计算留存比例
                calcKeepRate(totalRtnMap,keepDataUserCount,totalMap,totalFisrtMap,submitModelAnalysisParam.getTimeValue());
            }
            totalRtnMap.put(KeepDataAnalysisConstants.DETAILS_KEY,keepDataUserCount);
            dataMap.add(totalRtnMap);
        }else{
            groupPropertyParams.forEach(group->{
                Map<String,Object> columnGroupItem = new HashMap<>();
                columnGroupItem.put("title",group.getPropertyCn());
                columnGroupItem.put("dataIndex",group.getPropertyEn());
                columnMaps.add(columnGroupItem);
            });
            Map<String,Object> columnDatesGroupItem = new HashMap<>();
            columnDatesGroupItem.put("title",KeepDataAnalysisConstants.DATE_KEY_TEXT);
            columnDatesGroupItem.put("dataIndex",KeepDataAnalysisConstants.DATE_KEY);
            columnMaps.add(columnDatesGroupItem);
            //未来每日留存人数（去重）
            List<Map<String, Object>> keepDataRetentionUserCountGroup = clickhouseKeepDataDao.getKeepDataRetentionUserCountGroup(sqlParams);
            sqlParams.put("groupBySQL",groupByBuilder.deleteCharAt(0).toString());
            List<Map<String, Object>> keepDataTotalUserCountByGroup = clickhouseKeepDataDao.getKeepDataTotalUserCountByGroup(sqlParams);
            keepDataTotalUserCountByGroup.forEach(item->{
                List<Map<String,Object>> groupDetailList = new ArrayList<>();
                String groupStr = "";
                for (SubmitGroupPropertyParam groupPropertyParam : groupPropertyParams) {
                    groupStr+=item.get(groupPropertyParam.getPropertyEn())+"-";
                }
                    for (Map<String, Object> stringObjectMap : keepDataRetentionUserCountGroup) {
                        String dateStr = "";
                        for (SubmitGroupPropertyParam groupPropertyParam : groupPropertyParams) {
                            dateStr+=stringObjectMap.get(groupPropertyParam.getPropertyEn())+"-";
                        }
                        if(dateStr.equals(groupStr)){
                            item.put(KeepDataAnalysisConstants.MORROW_PREFIX_KEY+stringObjectMap.get(KeepDataAnalysisConstants.DATEDIFF_KEY),
                                    stringObjectMap.get(KeepDataAnalysisConstants.DATE_TOTAL_USER_COUNT_KEY));
                        }
                    }
                for (Map<String, Object> stringObjectMap : keepDataUserCount) {
                    String dateStr = "";
                    for (SubmitGroupPropertyParam groupPropertyParam : groupPropertyParams) {
                        dateStr+=stringObjectMap.get(groupPropertyParam.getPropertyEn())+"-";
                    }
                    if(dateStr.equals(groupStr)){
                        groupDetailList.add(stringObjectMap);
                    }
                }
                Map<String,BigDecimal> totalMap = new HashMap<>();
                Map<String,BigDecimal> totalFisrtMap = new HashMap<>();
                //计算留存比例
                calcKeepRate(item,groupDetailList, totalMap, totalFisrtMap,submitModelAnalysisParam.getTimeValue());
                if(StringUtils.isNotBlank(groupStr)){
                    groupStr = groupStr.substring(0,groupStr.length()-1);
                }
                item.put(KeepDataAnalysisConstants.GROUP_STR_KEY,groupStr);
                item.put(KeepDataAnalysisConstants.DETAILS_KEY,groupDetailList);
            });
            dataMap.addAll(keepDataTotalUserCountByGroup);
        }

        Map<String,Object> columnGroupItem = new HashMap<>();
        columnGroupItem.put("title",KeepDataAnalysisConstants.DATE_TOTAL_USER_COUNT_KEY_TEXT);
        columnGroupItem.put("dataIndex",KeepDataAnalysisConstants.DATE_TOTAL_USER_COUNT_KEY);
        columnMaps.add(columnGroupItem);
        for (Integer i = 0; i <= timeValue; i++) {
            Map<String,Object> groupItem = new HashMap<>();
            String title;
            if(i==0){
                title="当日";
            }else if(i==1){
                title="次日";
            }else{
                title="第"+i+"日";
            }
            groupItem.put("title",title);
            groupItem.put("dataIndex",KeepDataAnalysisConstants.MORROW_PREFIX_KEY+i);
            columnMaps.add(groupItem);
        }

        //未来留存,形成数组
//        for (Map<String, Object> mapItem : dataMap) {
//            int[] morrowUserCounts = new int[timeValue+1];
//            BigDecimal[] morrowUserRates = new BigDecimal[timeValue+1];
//            for (int i = 0; i <=timeValue; i++) {
//                String userCountKey = KeepDataAnalysisConstants.MORROW_PREFIX_KEY+i;
//                String userRateKey = KeepDataAnalysisConstants.RATE_MORROW_PREFIX_KEY+userCountKey;
//                morrowUserCounts[i]=Integer.valueOf(mapItem.get(userCountKey).toString());
//                morrowUserRates[i]=new BigDecimal(mapItem.get(userRateKey).toString());
//                mapItem.remove(userCountKey);
//                mapItem.remove(userRateKey);
//            }
//            if(mapItem.containsKey(KeepDataAnalysisConstants.DETAILS_KEY)){
//                List<Map<String,Object>> mapItems = (List<Map<String, Object>>) mapItem.get(KeepDataAnalysisConstants.DETAILS_KEY);
//                for (Map<String, Object> item : mapItems) {
//                    String[] detailsMorrowUserCounts = new String[timeValue+1];
//                    String[] detailsMUserRates = new String[timeValue+1];
//                    for (int i = 0; i <=timeValue; i++) {
//                        String userCountKey = KeepDataAnalysisConstants.MORROW_PREFIX_KEY+i;
//                        String userRateKey = KeepDataAnalysisConstants.RATE_MORROW_PREFIX_KEY+userCountKey;
//                        detailsMorrowUserCounts[i]=item.get(userCountKey).toString();
//                        detailsMUserRates[i]=item.get(userRateKey).toString();
//                        item.remove(userCountKey);
//                        item.remove(userRateKey);
//                        item.remove("isGreat"+i);
//                        item.remove("nows");
//                    }
//                    item.put("morrowUserCounts",detailsMorrowUserCounts);
//                    item.put("morrowUserRates",detailsMUserRates);
//                }
//            }
//            mapItem.put("morrowUserCounts",morrowUserCounts);
//            mapItem.put("morrowUserRates",morrowUserRates);
//        }
        rtnMap.put("columns",columnMaps);
        rtnMap.put("datas",dataMap);
        return rtnMap;
    }

    @Override
    public void exportExcel(SubmitModelAnalysisParam submitModelAnalysisParam, HttpServletResponse response,String fileName) {

    }

    /**
     * 每日的留存人数
     * @param groupDetailList  每日留存人数明细
     * @param totalMap 留存人数不为空的留存日对应的总留存人数
     * @param totalFisrtMap 留存人数不为空的留存日对应的第一天访问了起始事件的总人数
     * @param timeValue 几日留存
     */
    private void calcKeepRate(Map<String,Object> rtnMap,
                              List<Map<String,Object>> groupDetailList,
                              Map<String,BigDecimal> totalMap,
                              Map<String,BigDecimal> totalFisrtMap,
                              Integer timeValue
                              ){
        groupDetailList.forEach(detailList->{
//            if(CollectionUtils.isEmpty(groupPropertyParams)){
//                detailList.put(KeepDataAnalysisConstants.GROUP_STR_KEY,detailList.get(KeepDataAnalysisConstants.DATE_KEY));
//            }else{
//                detailList.put(groupPropertyParams.get(groupPropertyParams.size()),detailList.get(KeepDataAnalysisConstants.DATE_KEY));
//            }
            Set<String> mapKeySet = new HashSet<>(detailList.keySet());
            //当日访问起始事件的总人数Key
            Object nowTotal = detailList.get(KeepDataAnalysisConstants.DATE_TOTAL_USER_COUNT_KEY);
            //当日访问起始事件的总人数
            BigDecimal nowTotalBigDecimal = new BigDecimal(nowTotal.toString());
            for (String mapKey : mapKeySet) {
                //未来留存
                if(mapKey.startsWith(KeepDataAnalysisConstants.MORROW_PREFIX_KEY)){
                    Object valueObject = detailList.get(mapKey);
                    String valueStr = valueObject.toString();
                    if(StringUtils.isNotBlank(valueStr)){
                        BigDecimal valueDecimal = new BigDecimal(valueObject.toString());
                        //计算该留存周期对应的总留存人数
                        if(totalMap.containsKey(mapKey)){
                            BigDecimal oldValueBigDecimal = totalMap.get(mapKey);
                            totalMap.put(mapKey,oldValueBigDecimal.add(valueDecimal));
                        }else{
                            totalMap.put(mapKey,valueDecimal);
                        }
                        //计算该留存周期对应的总访问起始事件的人数
                        if(totalFisrtMap.containsKey(mapKey)){
                            BigDecimal oldUserTotalValueBigDecimal = totalFisrtMap.get(mapKey);
                            totalFisrtMap.put(mapKey,oldUserTotalValueBigDecimal.add(nowTotalBigDecimal));
                        }else{
                            totalFisrtMap.put(mapKey,nowTotalBigDecimal);
                        }
                    }
                }
            }
        });

        //各分组的留存比例计算
        Set<String> totalKeySet = totalMap.keySet();
        if(totalKeySet!=null && totalKeySet.size()>0){
            totalKeySet.forEach(item->{
                BigDecimal totalDecimal = totalMap.get(item);
                BigDecimal totalFirstDecimal = totalFisrtMap.getOrDefault(item,new BigDecimal(BigInteger.ZERO));
                if(totalFirstDecimal.compareTo(BigDecimal.ZERO)!=0){
                    rtnMap.put(KeepDataAnalysisConstants.RATE_MORROW_PREFIX_KEY+item,totalDecimal.divide(totalFirstDecimal,4,BigDecimal.ROUND_HALF_UP));
                }else{
                    rtnMap.put(KeepDataAnalysisConstants.RATE_MORROW_PREFIX_KEY+item,0);
                }
            });
        }
        //补0
        for (Integer i = 0; i <= timeValue; i++) {
            String userCountKey = KeepDataAnalysisConstants.MORROW_PREFIX_KEY+i;
            String userRateKey = KeepDataAnalysisConstants.RATE_MORROW_PREFIX_KEY+ KeepDataAnalysisConstants.MORROW_PREFIX_KEY+i;
            if(!rtnMap.containsKey(userCountKey)){
                rtnMap.put(userCountKey,0);
            }
            if(!rtnMap.containsKey(userRateKey)){
                rtnMap.put(userRateKey,0);
            }
        }

    }
}
