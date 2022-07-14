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
@TableName("t_analysis_model_select")
public class ModelSelectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 分析模型ID
	 */
	private Long modelId;
	/**
	 * 事件英文名
	 */
	private String eventEn;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 属性英文名
	 */
	private String propertyEn;
	/**
	 * 计算方式总次数total、用户数users、
	 */
	private String calcType;
	/**
	 * 排序值
	 */
	private String sort;

}
