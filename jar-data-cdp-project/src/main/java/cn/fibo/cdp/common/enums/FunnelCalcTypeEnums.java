package cn.fibo.cdp.common.enums;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description 漏斗统计计算方式
 * @createDate 2022-05-17 23:37:49
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public enum FunnelCalcTypeEnums {
    total("total","按次数"),
    users("users","按人数");

    private String code;

    private String name;

    FunnelCalcTypeEnums(String code, String name) {
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
