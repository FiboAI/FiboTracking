package cn.fibo.analysis.tracking.constants;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-07-04 12:48:11
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class CacheKeys {

    public static final String firstIdByKey = "{project}-firstId-{firstId}";

    public static final String secondIdByKey = "{project}-secondId-{secondId}";

    public static String getFirstIdByKey(String projectId,String firstId){
        return firstIdByKey.replace("{project}",projectId).replace("{firstId}",firstId);
    }

    public static String getSecondIdByKey(String projectId,String secondId){
        return secondIdByKey.replace("{project}",projectId).replace("{secondId}",secondId);
    }
}
