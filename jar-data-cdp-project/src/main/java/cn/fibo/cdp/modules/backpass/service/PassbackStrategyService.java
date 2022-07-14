package cn.fibo.cdp.modules.backpass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.backpass.entity.PassbackStrategyEntity;

import java.util.Map;

/**
 * 回传策略
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
public interface PassbackStrategyService extends IService<PassbackStrategyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

