package cn.fibo.cdp.modules.cdp.entity;

import cn.fibo.cdp.modules.cdp.entity.param.SubmitWherePropertyParam;
import cn.fibo.cdp.modules.cdp.entity.param.VirturalEventDetailParam;
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
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Data
@TableName("t_analysis_funnel_step_data")
@ApiModel("漏斗步骤")
public class FunnelStepDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@ApiModelProperty(hidden = true)
	private Long id;
	/**
	 * 关联漏斗配置表
	 */
	@ApiModelProperty("所属漏斗ID")
	private Long funnelId;
	/**
	 * 漏斗步骤序号
	 */
	@ApiModelProperty("步骤序号")
	private Integer sort;
	/**
	 * 步骤名称
	 */
	@ApiModelProperty("步骤名称")
	private String name;
	/**
	 * 事件英文名
	 */
	@ApiModelProperty("对应事件英文名")
	private String eventEn;

	@ApiModelProperty(hidden = true,value = "如果为虚拟事件，则对应的虚拟事件的明细")
	@TableField(exist = false)
	private List<VirturalEventDetailParam> virturalEventDetailParams;

	/**
	 * 数据筛选条件ID
	 */
	@ApiModelProperty(hidden = true)
	private String whereUuid;

	@TableField(exist = false)
	@ApiModelProperty("该步骤对应的筛选条件,一期暂不使用")
	private List<SubmitWherePropertyParam> wherePropertyParams;

}
