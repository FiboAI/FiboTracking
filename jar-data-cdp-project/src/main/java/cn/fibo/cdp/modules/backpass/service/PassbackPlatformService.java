package cn.fibo.cdp.modules.backpass.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.backpass.entity.PassbackPlatformEntity;

import java.util.List;
import java.util.Map;

/**
 * 平台信息
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
public interface PassbackPlatformService extends IService<PassbackPlatformEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PassbackPlatformEntity> getListAll(QueryWrapper queryWrapper);
}

