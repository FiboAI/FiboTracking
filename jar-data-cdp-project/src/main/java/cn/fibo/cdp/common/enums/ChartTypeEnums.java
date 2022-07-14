package cn.fibo.cdp.common.enums;

import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description 图表类型
 * @createDate 2022-05-17 23:28:08
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public enum ChartTypeEnums {

    chart_line(0,"线图"),
    chart_bar(1,"柱状图"),
    chart_annular(2,"环图"),
    chart_heapup(3,"堆积图"),
    chart_table(4,"表格"),
    chart_number(5,"数值"),
    chart_funnel(6,"漏斗折线"),
    chart_keep(7,"留存表格"),
    chart_customsql(8,"自定义分析-表格");

    ChartTypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer code;
    private String name;

}
