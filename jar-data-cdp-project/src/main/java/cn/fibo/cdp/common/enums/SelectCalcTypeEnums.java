package cn.fibo.cdp.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description 事件分析统计量的计算方式
 * @createDate 2022-05-17 23:31:46
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public enum SelectCalcTypeEnums {

    total("total","总次数","count()"),
    users("users","用户数","uniqExact(user_id)"),
    count("count","去重数","uniqExact({propertyEn})"),
    sum("sum","求和","round(sum({propertyEn}){convertValue},2)"),
    avg("avg","均值","round(avg({propertyEn}){convertValue},2)"),
    max("max","最大值","round(max({propertyEn}){convertValue},2)"),
    min("min","最小值","round(min({propertyEn}){convertValue},2)"),
    peopleAvg("peopleAvg","人均值","round((sum({propertyEn})/uniqExact(user_id)){convertValue},2)");

    private String code;

    private String name;

    private String function;

    private static Map<String,SelectCalcTypeEnums > zyMap = new HashMap<>();

    static {
        for (SelectCalcTypeEnums value : SelectCalcTypeEnums .values()) {
            zyMap.put(value.getCode(),value);
        }
    }
    public static SelectCalcTypeEnums getByCode(String code){
        return zyMap.get(code);
    }


    SelectCalcTypeEnums(String code, String name,String function) {
        this.code = code;
        this.name = name;
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
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
