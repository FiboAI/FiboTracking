

package cn.fibo.cdp.common.utils;

/**
 * Redis所有Keys
 *
 * @author lisw
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
