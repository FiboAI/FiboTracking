package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Data
@TableName("t_analysis_keep_data")
public class KeepDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 起始行为事件英文名
	 */
	private String startEvent;
	/**
	 * 后续行为事件英文名
	 */
	private String endEvent;
	/**
	 * 起始行为事件筛选条件
	 */
	private String startWhereUuid;
	/**
	 * 后续行为事件筛选条件
	 */
	private String endWhereUuid;
	/**
	 * 分析模型ID
	 */
	private Long modelId;

}
