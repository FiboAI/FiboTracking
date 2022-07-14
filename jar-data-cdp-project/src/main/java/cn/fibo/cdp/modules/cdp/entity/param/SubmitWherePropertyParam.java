package cn.fibo.cdp.modules.cdp.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 13:50:27
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@ApiModel("全局筛选条件")
@Data
public class SubmitWherePropertyParam
{

    @ApiModelProperty("属性英文名")
    private String propertyEn;

    @ApiModelProperty("属性字段类型")
    private String fieldType;

    @ApiModelProperty("判断运算符等于=、不等于!=、小于<、大于>、区间range、没值isnull、有值isnotnull、真true、假false、包含like、不包含notlike")
    private String conditions;

    @ApiModelProperty("判断运算值,一个值时或者区间值的最小值")
    private String value;

    @ApiModelProperty("判断运算值，区间值的最大值")
    private String maxValue;

    @ApiModelProperty("排序值")
    private Integer sort;

}
