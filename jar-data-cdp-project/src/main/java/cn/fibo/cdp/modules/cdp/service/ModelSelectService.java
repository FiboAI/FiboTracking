package cn.fibo.cdp.modules.cdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.ModelSelectEntity;

import java.util.Map;

/**
 * 
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface ModelSelectService extends IService<ModelSelectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

