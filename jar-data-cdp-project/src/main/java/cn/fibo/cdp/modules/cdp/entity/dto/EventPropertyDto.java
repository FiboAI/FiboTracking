package cn.fibo.cdp.modules.cdp.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 12:53:14
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@ApiModel("属性")
public class EventPropertyDto {

    @ApiModelProperty("属性ID")
    private Long propertyId;
    @ApiModelProperty("属性中文名")
    private String nameCn;
    @ApiModelProperty("属性英文名")
    private String nameEn;
    @ApiModelProperty("属性字段类型，string、number、bool")
    private String fieldType;
    @ApiModelProperty("属性备注")
    private String remarks;
}
