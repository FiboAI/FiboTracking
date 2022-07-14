package cn.fibo.cdp.common.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * 分析模型类型
 */
public enum ModelTypeEnums {
    EVENT_MODEL(0,"事件分析模型","modelConfigEventDataService"),
    FUNNEL_MODEL(1,"漏斗分析模型","modelConfigFunnelDataService"),
    KEEP_MODEL(2,"留存分析模型","modelConfigKeepDataService"),
    CUSTOMSQL_MODEL(3,"自定义分析","modelConfigCustomDataService");

    private int code;
    private String name;
    private String service;

    private static Map<Integer,ModelTypeEnums > zyMap = new HashMap<>();
    static {
        for (ModelTypeEnums value : ModelTypeEnums .values()) {
            zyMap.put(value.getCode(),value);
        }
    }
    public static ModelTypeEnums getByCode(Integer code){
        return zyMap.get(code);
    }


    ModelTypeEnums(int code, String name, String service) {
        this.code = code;
        this.name = name;
        this.service = service;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
