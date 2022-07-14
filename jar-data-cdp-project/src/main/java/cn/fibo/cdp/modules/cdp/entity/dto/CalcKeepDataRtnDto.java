package cn.fibo.cdp.modules.cdp.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-06-10 10:29:17
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@ApiModel("留存分析计算返回值")
public class CalcKeepDataRtnDto {

    @ApiModelProperty("分组总人数")
    private Integer total;

    @ApiModelProperty("分组名称")
    private String groupStr;

    @ApiModelProperty("该分组日期明细")
    private List<CalcKeepDataDetailsRtnDto> details;

    @ApiModelProperty("未来留存人数")
    private int[] morrowUserCounts;

    @ApiModelProperty("未来留存比例")
    private int[] morrowUserRates;



}
