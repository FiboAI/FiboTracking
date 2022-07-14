package cn.fibo.cdp.modules.cdp.service;

import cn.fibo.cdp.modules.cdp.entity.dto.EventGroupDto;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 事件定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface EventDataService extends IService<EventDataEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EventGroupDto> getCacheList();

    EventDataEntity getDetailById(QueryWrapper queryWrapper);

    boolean saveOrUpdate(EventDataEntity entity);

    @Override
    boolean update(Wrapper updateWrapper);


}

