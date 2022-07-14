package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

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
@TableName("t_analysis_model_where")
@ApiModel("数据筛选")
public class ModelWhereEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@ApiModelProperty(hidden = true)
	private Long id;
	/**
	 * 存在筛选条件的业务uuid
	 */
	@ApiModelProperty(hidden = true)
	private String uuid;
	/**
	 * 事件属性英文名
	 */
	@ApiModelProperty("筛选属性英文名")
	private String propertyEn;
	/**
	 * 判断条件
	 */
	@ApiModelProperty("判断运算符等于=、不等于!=、小于<、大于>、区间range、没值isnull、有值isnotnull、真true、假false")
	private String conditions;

	@ApiModelProperty("判断运算值,一个值时或者区间值的最小值")
	private String value;

	@ApiModelProperty("判断运算值，区间值的最大值")
	private String maxValue;

	@ApiModelProperty("排序值")
	private String sort;

	@ApiModelProperty(hidden = true)
	private Integer source;
}
