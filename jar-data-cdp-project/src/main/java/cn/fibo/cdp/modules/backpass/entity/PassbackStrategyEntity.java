package cn.fibo.cdp.modules.backpass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 回传策略
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Data
@TableName("t_cdp_passback_strategy")
public class PassbackStrategyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@TableId
	private Long id;
	/**
	 * 策略名称
	 */
	private String name;
	/**
	 * 回传字段类型string、bool、number、enum
	 */
	private String fieldType;
	/**
	 * 数据筛选,对应数据筛选ID
	 */
	private String whereUuid;
	/**
	 * 回传规则
	 */
	private Integer rule;
	/**
	 * 计算值rule为0时，为回传真实数据的百分之{value}
	 */
	private Double value;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 创建人ID
	 */
	private Long createUserId;
	/**
	 * 是否删除0:否 1:是
	 */
	private Integer delFlag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private Long updateUserId;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
