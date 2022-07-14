

package cn.fibo.cdp.modules.oss.service;

import cn.fibo.cdp.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author lisw
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
