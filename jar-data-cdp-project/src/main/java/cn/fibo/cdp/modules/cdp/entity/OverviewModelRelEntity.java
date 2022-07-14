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
 * @date 2022-05-13 15:19:41
 */
@Data
@TableName("t_analysis_overview_model_rel")
public class OverviewModelRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 概览ID
	 */
	private Long overviewId;
	/**
	 * 分析模型ID
	 */
	private Long modelId;

}
