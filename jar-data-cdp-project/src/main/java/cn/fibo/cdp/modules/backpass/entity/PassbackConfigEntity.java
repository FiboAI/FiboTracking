package cn.fibo.cdp.modules.backpass.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 回传配置
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Data
@TableName("t_cdp_passback_config")
public class PassbackConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@TableId
	private Long id;
	/**
	 * 平台code
	 */
	private String platformCode;
	/**
	 * 平台名称
	 */
	private String name;
	/**
	 * 埋点CDP事件英文名
	 */
	private String analysisEventEn;

	/**
	 * 埋点CDP事件中文名
	 */
	@TableField(exist = false)
	private String analysisEventCnName;

	/**
	 * 对应媒体侧事件英文名
	 */
	private String platformEventEn;

	/**
	 * 对应媒体侧事件中文名
	 */
	@TableField(exist = false)
	private String platformEventCnName;

	/**
	 * 回传方式 0实时 1定时
	 */
	private Integer passbackType;
	/**
	 * 回传频率 0每次 1仅首次
	 */
	private Integer passbackFrequency;
	/**
	 * 回传窗口期
	 */
	private Integer passbackWindowTime;
	/**
	 * 定时回传的百分比,50%，时间周期内数据只回传50%的数据
	 */
	private Double taskRate;
	/**
	 * 定时的执行周期，天数
	 */
	private Integer taskExecuteCycle;
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


	/**
	 * 属性对应关系
	 */
	@TableField(exist = false)
	List<PassbackConfigPropertyEntity> propertyEntityList;

}
