package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
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
@TableName("t_analysis_overview")
@ApiModel("概览")
public class OverviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@ApiModelProperty("概览ID")
	private Long id;
	/**
	 * 概览名称
	 */
	@ApiModelProperty("概览名称")
	private String name;

	/**
	 * 0 公共概览 1用户自定义概览
	 */
	private Integer overviewType;

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
