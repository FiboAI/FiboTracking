package cn.fibo.cdp.modules.cdp.service.utils;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.common.constants.EventDataAnalysisConstants;
import cn.fibo.cdp.common.enums.*;
import cn.fibo.cdp.common.utils.DateUtils;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.modules.cdp.entity.FunnelStepDataEntity;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitGroupPropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitSelectPropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitWherePropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.VirturalEventDetailParam;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-18 16:36:24
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class CalculateUtils {


    //生成聚合函数
    public static String getAggregateFunctionStr(SelectCalcTypeEnums selectCalcTypeEnums,String propertyEn,String propertyCn){
        String rtnString = "";
        //属性包含字样，做除以100的转换
        String amountConvertProperty = "金额";
        String amountConvertValue = "";
        if(StringUtils.contains(propertyCn,amountConvertProperty)){
            amountConvertValue="/100";
        }
        switch (selectCalcTypeEnums) {
            case total:
                rtnString = SelectCalcTypeEnums.total.getFunction();
                break;
            case users:
                rtnString = SelectCalcTypeEnums.users.getFunction();
                break;
            case count:
                rtnString = SelectCalcTypeEnums.count.getFunction().replace("{propertyEn}",propertyEn);
                break;
            case sum:
                rtnString =SelectCalcTypeEnums.sum.getFunction().replace("{propertyEn}",propertyEn).replace("{convertValue}",amountConvertValue);
                break;
            case avg:
                rtnString = SelectCalcTypeEnums.avg.getFunction().replace("{propertyEn}", propertyEn).replace("{convertValue}",amountConvertValue);
                break;
            case max:
                rtnString =  SelectCalcTypeEnums.max.getFunction().replace("{propertyEn}",propertyEn).replace("{convertValue}",amountConvertValue);
                break;
            case min:
                rtnString =  SelectCalcTypeEnums.min.getFunction().replace("{propertyEn}",propertyEn).replace("{convertValue}",amountConvertValue);
                break;
            case peopleAvg:
                rtnString =  SelectCalcTypeEnums.peopleAvg.getFunction().replace("{propertyEn}",propertyEn).replace("{convertValue}",amountConvertValue);
                break;
        }
        return rtnString;
    }


    // 拼接Left JOin
    public static Map<String,String> selectSQLAppend(List<SubmitSelectPropertyParam> selectPropertyParams,
                                                     List<SubmitGroupPropertyParam> groupPropertyParams,
                                                     List<SubmitWherePropertyParam> wherePropertyParams,
                                                     String format,String startTime,String endTime,String mainTableAliasName,WhereRelationTypeEnums whereRelationTypeEnums

    ){
        //事件分析中的select字段，其实又是根据事件进行了筛选，故而此处统一返回select与部分where条件，减少后续拼接where又重新遍历selectPropertyParams
        Map<String,String> sqlMap = new HashMap<>();

        for (int i = 0; i < selectPropertyParams.size(); i++) {
            SubmitSelectPropertyParam selectPropertyParam = selectPropertyParams.get(i);
            StringBuilder sqlSelect = new StringBuilder();
            StringBuilder sqlWhere = new StringBuilder();
            StringBuilder sqlGroup = new StringBuilder();
            StringBuilder sqlJoin = new StringBuilder();
            String leftJoinSqlTemplate = EventDataAnalysisConstants.leftJoinTemplate;
            String tbName = "tb"+(i+2);
            String tempTbName = "temp"+i;
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{format}",format);
            groupSelectSQLAppend(groupPropertyParams, tempTbName,sqlSelect,sqlGroup);
            String selectItemName = "`"+selectPropertyParam.getName()+"`";
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{tempTable}","temp"+i);
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{startTime}",startTime);
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{endTime}",endTime);
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{groupSQL}",sqlGroup);
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{tbName}",tbName);
            joinSQLAppend(sqlJoin,tbName,groupPropertyParams,mainTableAliasName);
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{joinSQL}",sqlJoin);
            String propertyEn = selectPropertyParam.getPropertyEn();
            String propertyCn = selectPropertyParam.getPropertyCn();
            String calcType = selectPropertyParam.getCalcType();
            SelectCalcTypeEnums selectCalcTypeEnums = SelectCalcTypeEnums.getByCode(calcType);
            sqlSelect.append(",");
            sqlSelect.append(getAggregateFunctionStr(selectCalcTypeEnums,propertyEn,propertyCn));
            sqlSelect.append(" as "+selectItemName);
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{selectSQL}",sqlSelect);
            //按事件统计,则需要按事件筛选
             sqlWhere.append(virturalSqlStatement(tempTbName,selectPropertyParam.getVirturalEventDetailParams(),selectPropertyParam.getEventEn(),null));
            //全局筛选
            sqlWhere.append(whereSQLAppend(wherePropertyParams,tempTbName,startTime,endTime,whereRelationTypeEnums));
            leftJoinSqlTemplate = leftJoinSqlTemplate.replace("{whereSQL}",sqlWhere);
            sqlMap.put(tbName+"."+selectItemName,leftJoinSqlTemplate);
        }
        return sqlMap;
    }

    //根据事件生成where条件
    public static String virturalSqlStatement(String tempTbName,List<VirturalEventDetailParam> virturalEventDetailParams,
                                              String eventName,List<SubmitWherePropertyParam> submitWherePropertyParams){
        //虚拟事件处理条件
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(" and (");
        if(!CollectionUtils.isEmpty(virturalEventDetailParams)){
            for (int i = 0; i < virturalEventDetailParams.size(); i++) {
                VirturalEventDetailParam virturalEventDetailParam = virturalEventDetailParams.get(i);
                if(i>0){
                    sqlWhere.append("or");
                }
                sqlWhere.append(" ( ");
                if(StringUtils.isNotBlank(tempTbName)){
                    sqlWhere.append(tempTbName);
                    sqlWhere.append(".");
                }
                sqlWhere.append(ClickhouseOptions.EVENT_FIELD_NAME);
                sqlWhere.append("=");
                sqlWhere.append("'");
                sqlWhere.append(virturalEventDetailParam.getEventEn());
                sqlWhere.append("' ");
                if(!CollectionUtils.isEmpty(virturalEventDetailParam.getSubmitWherePropertyParams())){
                    String virEventWhere = whereSQLAppend(virturalEventDetailParam.getSubmitWherePropertyParams(),tempTbName,null,null,WhereRelationTypeEnums.AND);
                    sqlWhere.append(virEventWhere);
                }
                sqlWhere.append(" ) ");
            }
        }else{
            sqlWhere.append(ClickhouseOptions.EVENT_FIELD_NAME);
            sqlWhere.append("=");
            sqlWhere.append("'");
            sqlWhere.append(eventName);
            sqlWhere.append("' ");
        }
        if(CollectionUtils.isEmpty(submitWherePropertyParams)){
            sqlWhere.append(whereSQLAppend(submitWherePropertyParams, tempTbName, null, null,WhereRelationTypeEnums.AND));
        }
        sqlWhere.append(" ) ");
        return sqlWhere.toString();
    }

    //根据筛选条件拼接where
    public static String whereSQLAppend(List<SubmitWherePropertyParam> wherePropertyParams, String tbName,
                                        String startTime, String endTime, WhereRelationTypeEnums whereRelationTypeEnums
                                        ){
        StringBuffer sqlBuffer = new StringBuffer();
        if(StringUtils.isNotBlank(startTime)){
           sqlBuffer.append(ClickhouseOptions.getDateTimeWhereSQLStatement(tbName,startTime,endTime));
        }
        if(!CollectionUtils.isEmpty(wherePropertyParams)){
            sqlBuffer.append("and ( ");
            for (int i = 0; i < wherePropertyParams.size(); i++) {
                SubmitWherePropertyParam wherePropertyParam = wherePropertyParams.get(i);
                String propertyEn = wherePropertyParam.getPropertyEn();
                String fieldType = wherePropertyParam.getFieldType();
                String value = wherePropertyParam.getValue();
                String maxValue = wherePropertyParam.getMaxValue();
                WhereConditionsTypeEnums whereConditionsTypeEnums = WhereConditionsTypeEnums.getByCode(wherePropertyParam.getConditions());
                if(i>0){
                    sqlBuffer.append(" "+whereRelationTypeEnums.name()+" ");
                    //todo 一期只有一级且全部为且的关系，后续增加or，此处变为动态即可。
                }
                if(StringUtils.isNotBlank(tbName)){
                    sqlBuffer.append(tbName);
                    sqlBuffer.append(".");
                }
                //判断事件
                String sqlStatementContions = whereConditionsTypeEnums.getCode();
                String sqlStatementContionValue = value;
                switch (whereConditionsTypeEnums){
                    case range:
                        if(StringUtils.isNotBlank(value)){
                            sqlBuffer.append(propertyEn +" "+ WhereConditionsTypeEnums.greateq.getCode() +" "+ value);
                        }
                        if(StringUtils.isNotBlank(value) && StringUtils.isNotBlank(maxValue)){
                            sqlBuffer.append(" and ");
                        }
                        if(StringUtils.isNotBlank(maxValue)){
                            sqlBuffer.append(propertyEn +" "+ WhereConditionsTypeEnums.lesseq.getCode() +" "+ maxValue);
                        }
                        break;
                    case isnotnull:
                        sqlStatementContions = WhereConditionsTypeEnums.notequals.getCode();
                        if(fieldType.equals(FieldTypeEnums.number.getCode())){
                            sqlStatementContionValue="0";
                        }else{
                            sqlStatementContionValue="''";
                        }
                        sqlBuffer.append(propertyEn +" "+ sqlStatementContions +" "+ sqlStatementContionValue);
                        break;
                    case isnull:
                        sqlStatementContions = WhereConditionsTypeEnums.equals.getCode();
                        if(fieldType.equals(FieldTypeEnums.number.getCode())){
                            sqlStatementContionValue="0";
                        }else{
                            sqlStatementContionValue="''";
                        }
                        sqlBuffer.append(propertyEn +" "+ sqlStatementContions +" "+ sqlStatementContionValue);
                        break;
                    case ctrue:
                    case cfalse:
                        sqlStatementContions = WhereConditionsTypeEnums.equals.getCode();
                        sqlStatementContionValue = "'"+WhereConditionsTypeEnums.ctrue.getCode()+"'";
                        sqlBuffer.append(propertyEn +" "+ sqlStatementContions +" "+ sqlStatementContionValue);
                        break;
                    case equals:
                    case notequals:
                        sqlStatementContionValue="'"+sqlStatementContionValue+"'";
                        sqlBuffer.append(propertyEn +" "+sqlStatementContions +" "+ sqlStatementContionValue);
                        break;
                    case like:
                    case notlike:
                        sqlStatementContionValue = "'%"+value+"%'";
                        sqlBuffer.append(propertyEn +" "+ sqlStatementContions +" "+ sqlStatementContionValue);
                        break;
                    default:
                        sqlBuffer.append(propertyEn +" "+ sqlStatementContions +" "+ sqlStatementContionValue);
                        break;
                }
            }
            sqlBuffer.append(" ) ");
        }

        return sqlBuffer.toString();
    }


    //根据分组字段，拼接出需要查询的分组字段与分组字段不能为空的，以及最终的分组SQL
    public static void groupSelectSQLAppend(
            List<SubmitGroupPropertyParam> submitGroupPropertyParams,String tbName,
            StringBuilder sqlSelectBuild,
            StringBuilder sqlGroupBuild

    ){
        if(!CollectionUtils.isEmpty(submitGroupPropertyParams)){

            for (SubmitGroupPropertyParam item : submitGroupPropertyParams) {
                sqlSelectBuild.append(",");
                //指定分组字段查询出来的别名
                sqlSelectBuild.append("if(");
                if(StringUtils.isNotBlank(tbName)){
                    sqlSelectBuild.append(tbName+".");
                }
                sqlSelectBuild.append("`");
                sqlSelectBuild.append(item.getPropertyEn());
                sqlSelectBuild.append("` ");
                sqlSelectBuild.append("='','"+ClickhouseOptions.GROUP_BY_NOT_NULL_DEFAULT_VALUE+"',");
                if(StringUtils.isNotBlank(tbName)){
                    sqlSelectBuild.append(tbName+".");
                }
                sqlSelectBuild.append("`"+item.getPropertyEn()+"`");
                sqlSelectBuild.append(")");
                sqlSelectBuild.append("`");
                sqlSelectBuild.append(item.getPropertyEn());
                sqlSelectBuild.append("`");
                //分组字段拼接
                sqlGroupBuild.append(",");
                sqlGroupBuild.append("`"+item.getPropertyEn()+"`");

            }
        }
    }
    //事件分析-拼装多维度的join的on后面的语句
    public static void joinSQLAppend(StringBuilder stringBuilder,String tableName,List<SubmitGroupPropertyParam> submitGroupPropertyParams,
    String mainTableAliasName
    ){
        if(!CollectionUtils.isEmpty(submitGroupPropertyParams)){
            for (SubmitGroupPropertyParam submitGroupPropertyParam : submitGroupPropertyParams) {
                String propertyEn = submitGroupPropertyParam.getPropertyEn();
                stringBuilder.append(" and ");
                stringBuilder.append(tableName);
                stringBuilder.append(".`");
                stringBuilder.append(propertyEn);
                stringBuilder.append("`=");
                stringBuilder.append(mainTableAliasName);
                stringBuilder.append(".`");
                stringBuilder.append(propertyEn+"`");
            }/**/
        }
    }

    //计算同比时间
    public static Map<String,String> getYearToYearDates(String dates, TimeGranularityEnums timeGranularityEnums){
        //环比的时间查询值
        String preDateChainValue ="";
        //同比的时间查询值
        String preDateYearToYearValue = "";
        //根据时间粒度、找同期
        switch (timeGranularityEnums){
            case minute:
                //环比 对比上分钟，获取上分钟的时间
                preDateChainValue = DateUtils.format(DateUtils.addDateMinutes(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -1),timeGranularityEnums.getJavaFormat());
                //同比 上一个小时的这一分钟
                preDateYearToYearValue =DateUtils.format(DateUtils.addDateMinutes(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -60),timeGranularityEnums.getJavaFormat());
                break;
            case hour:
                //环比 对比上小时，获取上小时的时间
                preDateChainValue =DateUtils.format(DateUtils.addDateHours(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -1),timeGranularityEnums.getJavaFormat());
                //同比 昨天的这一个小时
                preDateYearToYearValue =DateUtils.format(DateUtils.addDateHours(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -24),timeGranularityEnums.getJavaFormat());
                break;
            case day:
                //环比 对比昨天，获取昨天的时间
                preDateChainValue =DateUtils.format(DateUtils.addDateDays(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -1),timeGranularityEnums.getJavaFormat());
                //同比 上周的今天
                preDateYearToYearValue =DateUtils.format(DateUtils.addDateDays(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -7),timeGranularityEnums.getJavaFormat());
                break;
            case month:
                //环比 对比上个月，获取上月的时间
                preDateChainValue =DateUtils.format(DateUtils.addDateMonths(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -1),timeGranularityEnums.getJavaFormat());
                //同比 去年的这个月
                preDateYearToYearValue =DateUtils.format(DateUtils.addDateMonths(DateUtils.stringToDate(dates, timeGranularityEnums.getJavaFormat()), -12),timeGranularityEnums.getJavaFormat());
                break;

        }
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("preDateChainValue",preDateChainValue);
        hashMap.put("preDateYearToYearValue",preDateYearToYearValue);
        return hashMap;
    }


    //漏斗步骤事件拼接where条件
    public static String getFunnelWhereSQL(FunnelStepDataEntity funnelStepDataEntity,String tbAliasName){
        return virturalSqlStatement(tbAliasName, funnelStepDataEntity.getVirturalEventDetailParams(),
                funnelStepDataEntity.getEventEn(), funnelStepDataEntity.getWherePropertyParams());
    }

    //漏斗步骤事件列表
    public static List<String> getFunnelStepEventNameWhereSQL(FunnelStepDataEntity funnelStepDataEntity){
        List<String> arrays = new ArrayList<>();
        if(!CollectionUtils.isEmpty(funnelStepDataEntity.getVirturalEventDetailParams())){
            funnelStepDataEntity.getVirturalEventDetailParams().forEach(item->{
                arrays.add(item.getEventEn());
            });
        }else{
            arrays.add(funnelStepDataEntity.getEventEn());
        }
        return arrays;
    }

    //事件分析所有统计量事件列表拼成where条件，为了让查询只查这些事件，缩小数据范围，提高查询性能
    public static String getEventSelectListWhereSQL(List<SubmitSelectPropertyParam> selectPropertyParams,String tableAliasName){
        if(CollectionUtils.isEmpty(selectPropertyParams)){
            return "";
        }
        Set<String> eventEnSet = new HashSet<>();
        StringBuilder rtnSb = new StringBuilder();
        rtnSb.append(" and ");
        if(StringUtils.isNotBlank(tableAliasName)){
            rtnSb.append(tableAliasName);
            rtnSb.append(".");
        }
        rtnSb.append("event in (");
        for (SubmitSelectPropertyParam selectPropertyParam : selectPropertyParams) {
            if(!CollectionUtils.isEmpty(selectPropertyParam.getVirturalEventDetailParams())){
                selectPropertyParam.getVirturalEventDetailParams().forEach(item->{
                    eventEnSet.add("'"+item.getEventEn()+"'");
                });
            }else{
                eventEnSet.add("'"+selectPropertyParam.getEventEn()+"'");
            }
        }
        String collect = eventEnSet.stream().collect(Collectors.joining(","));
        rtnSb.append(collect);
        rtnSb.append(")");
        return rtnSb.toString();
    }

    //拼接项目的筛选条件
    public static String projectWhereSQL(String tableAliasName){
        if(StringUtils.isNotBlank(tableAliasName)){
            return " and "+tableAliasName+".project='"+ClickhouseOptions.PROJECT+"'";
        }else {
            return "and project='" + ClickhouseOptions.PROJECT + "'";
        }
    }



}
