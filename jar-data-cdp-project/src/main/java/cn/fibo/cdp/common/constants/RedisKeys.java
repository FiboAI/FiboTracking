package cn.fibo.cdp.common.constants;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-12 20:24:20
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class RedisKeys {


    // token缓存Key
    public static final String USER_LOGIN_TOKEN_KEY = "config:login:user:token:";
    // token 有效期
    public static final Integer USER_LOGIN_TOKEN_EXPIRE = 3600*12;

    public static final String USER_LOGIN_CAPTCHA_KEY = "config:login:captcha:code:";

    //所有事件列表缓存
    public static final String CDP_GROUP_EVENT_ALL_LIST = "cdp:metadata:events";

    //所有属性缓存
    public static final String CDP_PROPERTY_ALL_LIST = "cdp:metadata:property";

    //所有属性缓存
    public static final String CDP_EVENT_PROPERTY_ALL_LIST = "cdp:metadata:event:property";

    //数据分析计算缓存
    public static final String CDP_DATA_ANALYSIS_CALC = "cdp:data:analysis:{type}:{id}";

    //数据分析导出数据
    public static final String CDP_DATA_EXPORT = "cdp:data:export:{0}";

    //缓存管理器
    public static final String CACHEMANAGER_NAME = "cacheManager";
}
