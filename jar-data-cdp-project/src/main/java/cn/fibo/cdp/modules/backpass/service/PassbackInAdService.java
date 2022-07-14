package cn.fibo.cdp.modules.backpass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.backpass.entity.PassbackInAdEntity;

import java.util.Map;

/**
 * 广告进入记录
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
public interface PassbackInAdService extends IService<PassbackInAdEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

