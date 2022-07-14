package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Data
@TableName("t_analysis_metadata_property_data")
public class PropertyDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 属性中文名
	 */
	private String nameCn;
	/**
	 * 属性英文名
	 */
	private String nameEn;
	/**
	 * 属性类型
	 */
	private String fieldType;
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

	/**
	 * 是否支持分组
	 */
	private Integer isGroupView;

	/*
	* 排序值
	 */
	private Integer sort;

}
