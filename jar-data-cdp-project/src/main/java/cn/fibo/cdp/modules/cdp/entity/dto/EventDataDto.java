package cn.fibo.cdp.modules.cdp.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-14 22:51:03
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@ApiModel("事件")
public class EventDataDto implements Serializable {

    @ApiModelProperty("事件ID")
    private Long id;

    @ApiModelProperty("事件中文名")
    private String nameCn;
    @ApiModelProperty("事件英文名")
    private String nameEn;

    @ApiModelProperty("是否是虚拟事件 0,否 1：是")
    private Integer isVirtual;
}
