package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 虚拟事件明细表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 18:06:17
 */
@Data
@TableName("t_analysis_metadata_event_virtual_data")
public class EventVirtualDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 虚拟事件ID
	 */
	private Long virtualEventEn;
	/**
	 * 事件英文名
	 */
	private String nameEn;

}
