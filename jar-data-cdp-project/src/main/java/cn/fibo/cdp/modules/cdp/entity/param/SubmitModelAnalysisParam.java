package cn.fibo.cdp.modules.cdp.entity.param;

import cn.fibo.cdp.modules.cdp.entity.FunnelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.MetadataCustomsqlDataEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 13:35:56
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@ApiModel("分析模型")
public class SubmitModelAnalysisParam {

    @ApiModelProperty("分析模型id")
    private Long id;

    @ApiModelProperty("分析模型名称")
    private String name;
    @ApiModelProperty("分型模型类型 0:事件 1:漏斗 2:留存")
    private Integer type;
    @ApiModelProperty("时间粒度day、week、month、hour、minute  ")
    private String timeGranularity;
    @ApiModelProperty("时间值,天数")
    private Integer timeValue;
    @ApiModelProperty("0:线图 1:柱状图 2:环图 3:堆积图 4:表格 5:数值")
    private Integer chartType;
    @ApiModelProperty("是否同时显示均值")
    private Integer isAvg;
    @ApiModelProperty("是否同时合计")
    private Integer isSum;
    @ApiModelProperty("是否同时显示同环比")
    private Integer isYearToYear;
    @ApiModelProperty("窗口尺寸，mini、normal、large")
    private String windowSize;

    @ApiModelProperty("归属概览ID")
    private Long overviewId;

    @ApiModelProperty("关联的漏斗ID，如果是漏斗模型")
    private Long funnelId;

    @ApiModelProperty("按...计算,users按人数、total按次数,如果是漏斗模型")
    private String calcType;

    @ApiModelProperty("需要统计的属性配置，仅事件分析传输")
    private List<SubmitSelectPropertyParam> selectPropertyParams;

    @ApiModelProperty("全局筛选条件")
    private List<SubmitWherePropertyParam> wherePropertyParams;

    @ApiModelProperty("筛选逻辑关系，AND、OR")
    private String relation;

    @ApiModelProperty("按....查看")
    private List<SubmitGroupPropertyParam> groupPropertyParams;

    @ApiModelProperty("留存分析-事件数据对象")
    private SubmitKeepEventParam keepEventParam;

    @ApiModelProperty("自定义分析-数据对象")
    private MetadataCustomsqlDataEntity metadataCustomsqlData;


    @ApiModelProperty("序号")
    private Integer sort;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("漏斗详情")
    private FunnelDataEntity funnelDataEntity;

    @ApiModelProperty(hidden = true)
    private boolean tempIsCalcYearToYearCondions;


}
