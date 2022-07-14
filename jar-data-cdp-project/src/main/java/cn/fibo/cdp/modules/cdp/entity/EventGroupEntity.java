package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 事件分组
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Data
@TableName("t_analysis_metadata_event_group")
public class EventGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 分组名称
	 */
	private String name;
	/**
	 *
	 */
	private Integer status;
	/**
	 *
	 */
	private String remarks;
	/**
	 *
	 */
	private Long createUserId;
	/**
	 *
	 */
	private Integer delFlag;
	/**
	 *
	 */
	private Date createTime;
	/**
	 *
	 */
	private Long updateUserId;
	/**
	 *
	 */
	private Date updateTime;

	private Integer sort;

}
