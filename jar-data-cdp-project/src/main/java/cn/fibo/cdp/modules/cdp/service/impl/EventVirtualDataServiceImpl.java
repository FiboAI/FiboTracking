package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.modules.cdp.entity.param.VirturalEventDetailParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.EventVirtualDataDao;
import cn.fibo.cdp.modules.cdp.entity.EventVirtualDataEntity;
import cn.fibo.cdp.modules.cdp.service.EventVirtualDataService;


@Service("eventVirtualDataService")
public class EventVirtualDataServiceImpl extends ServiceImpl<EventVirtualDataDao, EventVirtualDataEntity> implements EventVirtualDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EventVirtualDataEntity> page = this.page(
                new Query<EventVirtualDataEntity>().getPage(params),
                new QueryWrapper<EventVirtualDataEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<VirturalEventDetailParam> getVirturalEventDetailParams(String virtualEventEn) {
        return this.getBaseMapper().getVirturalEventDetailParams(virtualEventEn);
    }

}
