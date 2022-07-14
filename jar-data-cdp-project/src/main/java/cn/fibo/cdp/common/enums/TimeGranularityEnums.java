package cn.fibo.cdp.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 时间粒度
 */
public enum TimeGranularityEnums {


    minute("minute","分钟","%Y-%m-%d %H:%M", "yyyy-MM-dd HH:mm"),
    hour("hour","小时","%Y-%m-%d %H:00", "yyyy-MM-dd HH:00"),
    day("day","天","%Y-%m-%d", "yyyy-MM-dd"),
    week("week","周","Y-%m-%d", "yyyy-MM-dd"),
    month("month","月","%Y-%m", "yyyy-MM");

    private String code;
    private String name;
    private String dbFormt;
    private String javaFormat;


    private static Map<String,TimeGranularityEnums > zyMap = new HashMap<>();
    static {
        for (TimeGranularityEnums value : TimeGranularityEnums .values()) {
            zyMap.put(value.getCode(),value);
        }
    }
    public static TimeGranularityEnums getByCode(String code){
        return zyMap.get(code);
    }

    TimeGranularityEnums(String code, String name,String dbFormt,String javaFormat) {
        this.code = code;
        this.name = name;
        this.dbFormt = dbFormt;
        this.javaFormat = javaFormat;
    }

    public String getJavaFormat() {
        return javaFormat;
    }

    public void setJavaFormat(String javaFormat) {
        this.javaFormat = javaFormat;
    }

    public String getDbFormt() {
        return dbFormt;
    }

    public void setDbFormt(String dbFormt) {
        this.dbFormt = dbFormt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
