package cn.fibo.cdp.modules.cdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.EventPropertyRelEntity;

import java.util.Map;

/**
 * 事件属性关联关系表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface EventPropertyRelService extends IService<EventPropertyRelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

