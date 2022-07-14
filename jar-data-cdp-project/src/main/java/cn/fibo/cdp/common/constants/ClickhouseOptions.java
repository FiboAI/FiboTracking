package cn.fibo.cdp.common.constants;

import cn.fibo.cdp.common.utils.StringUtils;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-18 14:40:15
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class ClickhouseOptions {

    public static final String PROJECT = "hippo_shop";

    public static final String BASEDATA_TABLE_NAME = "events";

    public static final String USER_TABLE_NAME = "users";

    public static final String DATETIME_FIELD = "flume_time";

    public static final String EVENT_FIELD_NAME = "event";

    public static final String GROUP_BY_NOT_NULL_DEFAULT_VALUE = "未知";

    public static final String ANALYSIS_CLICKHOUSE="clickhouse";

    //clickhouse时间格式化函数
    public static final String DATETIME_FORMAT_FUN = "formatDateTime("+DATETIME_FIELD+",'{format}')";

    //时间筛选开始
    public static final String DATETIME_WHERE_START = "and {tbName}."+DATETIME_FIELD+">='{startTime}'";

    //时间筛选结束
    public static final String DATETIME_WHERE_END = "and {tbName}."+DATETIME_FIELD+"<='{endTime}'";


    public static String getDateTimeWhereSQLStatement(String tbName,String startTime,String endTime){
        String sqlStatement = "";
        if(StringUtils.isNotBlank(startTime)){
            sqlStatement+=DATETIME_WHERE_START.replace("{tbName}",tbName).replace("{startTime}",startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            sqlStatement+=DATETIME_WHERE_END.replace("{tbName}",tbName).replace("{endTime}",endTime);
        }
        return sqlStatement;
    }


}
