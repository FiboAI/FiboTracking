package cn.fibo.cdp.modules.backpass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 回传配置附加属性
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Data
@TableName("t_cdp_passback_config_property")
public class PassbackConfigPropertyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@TableId
	private Long id;
	/**
	 * 埋点CDP属性英文名
	 */
	private String analysisPropertyEn;
	/**
	 * 媒体侧属性英文名
	 */
	private String platformPropertyEn;
	/**
	 * 回传配置ID
	 */
	private Long configId;
	/**
	 * 策略ID
	 */
	private Long strategyId;
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
