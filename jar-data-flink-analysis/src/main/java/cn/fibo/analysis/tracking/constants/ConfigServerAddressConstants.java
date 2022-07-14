package cn.fibo.analysis.tracking.constants;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-26 17:16:56
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class ConfigServerAddressConstants {

    //部署正式环境，改为true
    public static final boolean IS_PROD = true;
    //kafka地址
    public static  String KAFKA_SERVER_URL;
    //kafka消费组
    public static final String KAFKA_PRO_GROUPID = "tracking-java";

    //kafka本地消费组，本地启动使用，防止本地启动消费了正式环境的数据。
    public static final String KAFKA_TEST_GROUPID = "tracking-test";

    public static String CLICKHOUSE_SERVER_URL;
    public static final String CLICKHOUSE_USERNAME = "default";
    public static final String CLICKHOUSE_PASSWORD = "123456";
    static{
        if(IS_PROD){
            KAFKA_SERVER_URL = "127.0.0.1:9092";
            CLICKHOUSE_SERVER_URL="jdbc:clickhouse://127.0.0.1:8123/tracking";
        }else{
            KAFKA_SERVER_URL = "127.0.0.1:9092";
            CLICKHOUSE_SERVER_URL = "jdbc:clickhouse://127.0.0.1:8123/tracking";
        }
    }



}
