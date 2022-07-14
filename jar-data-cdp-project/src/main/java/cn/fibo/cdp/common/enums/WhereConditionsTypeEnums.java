package cn.fibo.cdp.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-18 16:29:24
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public enum WhereConditionsTypeEnums {

    equals("="),
    notequals("!="),
    less("<"),
    great(">"),
    greateq(">="),
    lesseq("<="),
    range("range"),
    isnull("isnull"),
    isnotnull("isnotnull"),
    ctrue("true"),
    cfalse("false"),
    like("like"),
    notlike("not like");

    private static Map<String,WhereConditionsTypeEnums > zyMap = new HashMap<>();
    static {
        for (WhereConditionsTypeEnums value : WhereConditionsTypeEnums .values()) {
            zyMap.put(value.getCode(),value);
        }
    }
    public static WhereConditionsTypeEnums getByCode(String code){
        return zyMap.get(code);
    }

    private String code;

    WhereConditionsTypeEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
