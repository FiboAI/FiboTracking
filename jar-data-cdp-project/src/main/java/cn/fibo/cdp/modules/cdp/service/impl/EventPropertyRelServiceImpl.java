package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.EventPropertyRelDao;
import cn.fibo.cdp.modules.cdp.entity.EventPropertyRelEntity;
import cn.fibo.cdp.modules.cdp.service.EventPropertyRelService;


@Service("eventPropertyRelService")
public class EventPropertyRelServiceImpl extends ServiceImpl<EventPropertyRelDao, EventPropertyRelEntity> implements EventPropertyRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EventPropertyRelEntity> page = this.page(
                new Query<EventPropertyRelEntity>().getPage(params),
                new QueryWrapper<EventPropertyRelEntity>()
        );

        return new PageUtils(page);
    }

}