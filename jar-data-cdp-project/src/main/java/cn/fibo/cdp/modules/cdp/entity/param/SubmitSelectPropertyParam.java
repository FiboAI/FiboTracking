package cn.fibo.cdp.modules.cdp.entity.param;

import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 13:41:44
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@ApiModel("需要统计的属性配置")
public class SubmitSelectPropertyParam {

    @ApiModelProperty("事件英文名")
    private String eventEn;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("属性英文名")
    private String propertyEn;

    @ApiModelProperty(value = "属性中文名",hidden = true)
    private String propertyCn;

    @ApiModelProperty("计算方式,总次数total、用户数users、总和sum、去重数count、均值avg、最大值max、最小值min、人均值peopleAvg")
    private String calcType;

    @ApiModelProperty("序号")
    private String sort;

    @ApiModelProperty("是否是虚拟事件 0,否 1是")
    private Integer isVirtual;

    @ApiModelProperty(hidden = true,value = "虚拟事件时，对应的事件明细")
    private List<VirturalEventDetailParam> virturalEventDetailParams;

}
