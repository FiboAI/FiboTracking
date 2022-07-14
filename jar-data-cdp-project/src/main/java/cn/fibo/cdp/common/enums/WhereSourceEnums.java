package cn.fibo.cdp.common.enums;

import lombok.Data;

public enum WhereSourceEnums {

    model_global(1,"模型分析的全局筛选"),
    event_model_select(2,"事件分析的select筛选"),
    funnel_step(3,"漏斗中的步骤筛选"),
    virtual_field(4,"虚拟属性对应的真实属性筛选"),
    keep_event(5,"留存分析的起始或后续行为事件筛选");

    private Integer code;
    private String name;

    WhereSourceEnums(Integer code, String name) {
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
}
