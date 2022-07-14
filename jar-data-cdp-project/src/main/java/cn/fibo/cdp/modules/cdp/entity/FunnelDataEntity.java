package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
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
@TableName("t_analysis_funnel_data")
@ApiModel("漏斗信息")
public class FunnelDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@ApiModelProperty("漏斗ID")
	private Long id;
	/**
	 * 漏斗名称
	 */
	@ApiModelProperty("漏斗名称")
	private String name;
	/**
	 * 窗口期 仅漏斗分析存在
	 */
	@ApiModelProperty("窗口期")
	private Integer windowPeriod;

	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(hidden = true)
	private Integer status;
	/**
	 *
	 */
	@ApiModelProperty(hidden = true)
	private String remarks;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(hidden = true)
	private Long createUserId;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(hidden = true)
	private Integer delFlag;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(hidden = true)
	private Date createTime;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(hidden = true)
	private Long updateUserId;
	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(hidden = true)
	private Date updateTime;

	@TableField(exist = false)
	@ApiModelProperty("该漏斗对应的步骤列表")
	private List<FunnelStepDataEntity> funnelStepDataEntityList;

}
