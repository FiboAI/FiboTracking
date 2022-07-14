

package cn.fibo.cdp.modules.sys.service;


import cn.fibo.cdp.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author lisw
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
