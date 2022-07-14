package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Data
@TableName("t_analysis_model_data")
@ApiModel("分析模型-基本信息-不含分路")
public class ModelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 分型模型类型
	 */
	@ApiModelProperty("分型模型类型 0:事件 1:漏斗 2:留存")
	private Integer type;
	/**
	 * 模型名称
	 */
	@ApiModelProperty("分型模型类型 0:事件 1:漏斗 2:留存")
	private String name;
	/**
	 * 时间粒度day、week、month、hour、minute
	 */
	@ApiModelProperty("时间粒度day、week、month、hour、minute")
	private String timeGranularity;
	/**
	 * 时间值
	 */
	private Integer timeValue;
	/**
	 * 0:线图 1:柱状图 2:环图 3:堆积图 4:表格 5:数值 仅事件分析存在
	 */
	@ApiModelProperty("0:线图 1:柱状图 2:环图 3:堆积图 4:表格 5:数值 仅事件分析存在")
	private Integer chartType;

	@ApiModelProperty("漏斗ID")
	private Long funnelId;

	/**
	 * 全局筛选uuid
	 */
	private String whereUuid;
	/**
	 * 是否同时显示均值
	 */
	private Integer isAvg;
	/**
	 * 是否同时合计
	 */
	private Integer isSum;
	/**
	 * 是否同时显示同环比
	 */
	@ApiModelProperty("是否同时显示同环比")
	private Integer isYearToYear;
	/**
	 * 窗口尺寸
	 */
	@ApiModelProperty("窗口尺寸，mini、normal、large")
	private String windowSize;

	/**
	 * 归属概览ID
	 */
	private Long overviewId;

	/**
	 * people按人数、count按次数 漏斗分析
	 */
	private String calcType;
	private Integer sort;

	private String startTime;

	private String endTime;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	private Integer status;
	/**
	 *
	 */
	private String remarks;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createUserId;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	private Integer delFlag;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateUserId;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}
