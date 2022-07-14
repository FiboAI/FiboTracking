package cn.fibo.cdp.modules.backpass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告进入记录
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Data
@TableName("t_cdp_passback_in_ad")
public class PassbackInAdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	@TableId
	private Long id;
	/**
	 * 媒体侧给的广告ID
	 */
	private String clickid;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
