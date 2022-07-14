package cn.fibo.cdp.modules.cdp.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-25 13:27:53
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
public class SubmitKeepEventParam {

    @ApiModelProperty("初始行为事件英文名")
    private String firstEventEn;
    @ApiModelProperty("后续行为事件英文名")
    private String lastEventEn;

    @ApiModelProperty("初始行为事件是否是虚拟事件 0，否 1，是")
    private Integer firstIsVirtual;
    @ApiModelProperty("后续行为事件是否是虚拟事件 0，否 1，是")
    private Integer lastIsVirtual;

    @ApiModelProperty(hidden = true,value = "初始行为事件的筛选条件,一期暂时不用")
    private List<SubmitWherePropertyParam> firstWherePropertyParamList;

    @ApiModelProperty(hidden = true,value = "后续行为事件的筛选条件,一期暂时不用")
    private List<SubmitWherePropertyParam> lastWherePropertyParamList;

    @ApiModelProperty(hidden = true,value = "初始行为事件如果是虚拟事件，对应的真实事件明细")
    private List<VirturalEventDetailParam> firstVirturalEventDetailParams;

    @ApiModelProperty(hidden = true,value = "后续行为事件如果是虚拟事件，对应的真实事件明细")
    private List<VirturalEventDetailParam> lastVirturalEventDetailParams;
}
