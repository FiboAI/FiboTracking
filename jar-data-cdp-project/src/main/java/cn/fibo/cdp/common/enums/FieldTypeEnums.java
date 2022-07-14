package cn.fibo.cdp.common.enums;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-23 15:57:59
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/

public enum FieldTypeEnums {

    string("string","字符型"),
    number("number","数值型"),
    bool("bool","布尔醒");


    private String code;

    private String name;

    FieldTypeEnums(String code, String name) {
        this.code = code;
        this.name = name;
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
