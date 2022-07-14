package cn.fibo.cdp.common.utils;

import java.util.UUID;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 16:57:45
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class StringUtils extends org.apache.commons.lang.StringUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
