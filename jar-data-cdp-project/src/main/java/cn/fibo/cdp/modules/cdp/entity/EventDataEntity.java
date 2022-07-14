package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 事件定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Data
@TableName("t_analysis_metadata_event_data")
@ApiModel("元数据-事件信息")
public class EventDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "事件ID")
	private Long id;
	/**
	 * 事件中文
	 */
	@ApiModelProperty(name = "nameCn",value = "事件中文名")
	private String nameCn;
	/**
	 * 事件英文名
	 */
	@ApiModelProperty(value = "事件英文名")
	private String nameEn;
	/**
	 * 分组ID
	 */
	@ApiModelProperty(value = "所属分组ID")
	private Long groupId;
	/**
	 *
	 */
	@ApiModelProperty(value = "事件状态")
	@TableField(fill = FieldFill.INSERT)
	private Integer status;
	/**
	 *
	 */
	@ApiModelProperty(value = "事件备注")
	private String remarks;
	/**
	 *
	 */
	@ApiModelProperty(hidden = true)
	@TableField(fill = FieldFill.INSERT)
	private Long createUserId;
	/**
	 *
	 */
	@ApiModelProperty(hidden = true)
	@TableField(fill = FieldFill.INSERT)
	private Integer delFlag;
	/**
	 *
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 *
	 */
	@ApiModelProperty(hidden = true)
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateUserId;
	/**
	 *
	 */
	@ApiModelProperty(value = "更新事件")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	@ApiModelProperty(value = "排序值")
	private Integer sort;
	@ApiModelProperty(value = "是否是虚拟事件0，否1，是")
	private Integer isVirtual;

	@TableField(exist = false)
	@ApiModelProperty(value = "所属分组名称")
	private String groupName;


	@TableField(exist = false)
	@ApiModelProperty(value = "关联的属性列表")
	List<PropertyDataEntity> propertyDataEntityList;

}
