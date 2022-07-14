package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.modules.cdp.dao.EventGroupDao;
import cn.fibo.cdp.modules.cdp.entity.EventPropertyRelEntity;
import cn.fibo.cdp.modules.cdp.entity.dto.EventGroupDto;
import cn.fibo.cdp.modules.cdp.service.EventPropertyRelService;
import cn.fibo.cdp.modules.cdp.service.PropertyDataService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.EventDataDao;
import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;
import cn.fibo.cdp.modules.cdp.service.EventDataService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("eventDataService")
public class EventDataServiceImpl extends ServiceImpl<EventDataDao, EventDataEntity> implements EventDataService {

    @Autowired
    private EventGroupDao eventGroupDao;

    @Autowired
    private EventPropertyRelService eventPropertyRelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EventDataEntity> page = this.page(
                new Query<EventDataEntity>().getPage(params),
                new QueryWrapper<EventDataEntity>().eq("del_flag",StatusConstants.del_flag_0)
                        .eq("is_virtual","0").orderByDesc("sort")
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(cacheNames = RedisKeys.CDP_GROUP_EVENT_ALL_LIST,cacheManager = RedisKeys.CACHEMANAGER_NAME)
    public List<EventGroupDto> getCacheList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", StatusConstants.status_0);
        queryWrapper.eq("del_flag",StatusConstants.status_0);
        List<EventGroupDto> list = eventGroupDao.getEventGroupList(queryWrapper);

        return list;
    }

    @Override
    public EventDataEntity getDetailById(QueryWrapper queryWrapper) {
       return this.baseMapper.getDetailById(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = {RedisKeys.CDP_GROUP_EVENT_ALL_LIST},allEntries = true,cacheManager = RedisKeys.CACHEMANAGER_NAME)
    public  boolean saveOrUpdate(EventDataEntity entity){
        super.saveOrUpdate(entity);
        List<EventPropertyRelEntity> eventPropertyRelEntities = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("event_en",entity.getNameEn());
        eventPropertyRelService.remove(queryWrapper);
        if(!CollectionUtils.isEmpty(entity.getPropertyDataEntityList())){
            entity.getPropertyDataEntityList().forEach(item->{
                EventPropertyRelEntity eventPropertyRelEntity = new EventPropertyRelEntity();
                eventPropertyRelEntity.setEventEn(entity.getNameEn());
                eventPropertyRelEntity.setPropertyEn(item.getNameEn());
                eventPropertyRelEntities.add(eventPropertyRelEntity);
            });
            eventPropertyRelService.saveBatch(eventPropertyRelEntities);
        }
        return true;
    }

    @Override
    @CacheEvict(cacheNames = {RedisKeys.CDP_GROUP_EVENT_ALL_LIST},allEntries = true,cacheManager = RedisKeys.CACHEMANAGER_NAME)
    public boolean update(Wrapper updateWrapper) {
        return super.update(updateWrapper);
    }

}
