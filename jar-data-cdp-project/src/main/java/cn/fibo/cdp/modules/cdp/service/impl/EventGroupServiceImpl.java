package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.EventGroupDao;
import cn.fibo.cdp.modules.cdp.entity.EventGroupEntity;
import cn.fibo.cdp.modules.cdp.service.EventGroupService;


@Service("eventGroupService")
public class EventGroupServiceImpl extends ServiceImpl<EventGroupDao, EventGroupEntity> implements EventGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EventGroupEntity> page = this.page(
                new Query<EventGroupEntity>().getPage(params),
                new QueryWrapper<EventGroupEntity>()
        );

        return new PageUtils(page);
    }

}