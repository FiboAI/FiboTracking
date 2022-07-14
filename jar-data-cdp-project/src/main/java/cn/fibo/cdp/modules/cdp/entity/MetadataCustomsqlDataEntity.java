package cn.fibo.cdp.modules.cdp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-06-24 10:20:27
 */
@Data
@TableName("t_analysis_metadata_customsql_data")
@ApiModel("自定义分析实体对象")
public class MetadataCustomsqlDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * sql语句
	 */
	@ApiModelProperty("SQL语句")
	private String querySql;
	/**
	 * 参数设置
	 */
	@ApiModelProperty("参数")
	private String params;

	/**
	 * 分析模型ID
	 */
	@ApiModelProperty(value = "模型ID",hidden = true)
	private Long modelId;

}
