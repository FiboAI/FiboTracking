package cn.fibo.cdp.modules.cdp.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-14 22:49:45
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@ApiModel("事件分组")
public class EventGroupDto implements Serializable {

    @ApiModelProperty("事件分组名称")
    private String name;
    @ApiModelProperty("事件列表")
    List<EventDataDto> eventDataDtoList;
}
