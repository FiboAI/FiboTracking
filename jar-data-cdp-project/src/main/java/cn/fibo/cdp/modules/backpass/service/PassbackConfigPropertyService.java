package cn.fibo.cdp.modules.backpass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.backpass.entity.PassbackConfigPropertyEntity;

import java.util.Map;

/**
 * 回传配置附加属性
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
public interface PassbackConfigPropertyService extends IService<PassbackConfigPropertyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

