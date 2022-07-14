package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.cdp.dao.ClickhouseBaseDao;
import cn.fibo.cdp.modules.cdp.entity.dto.EventPropertyDto;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.PropertyDataDao;
import cn.fibo.cdp.modules.cdp.entity.PropertyDataEntity;
import cn.fibo.cdp.modules.cdp.service.PropertyDataService;
import org.springframework.transaction.annotation.Transactional;


@Service("propertyDataService")
public class PropertyDataServiceImpl extends ServiceImpl<PropertyDataDao, PropertyDataEntity> implements PropertyDataService {

    @Autowired
    private PropertyDataDao propertyDataDao;

    @Autowired
    private ClickhouseBaseDao clickhouseBaseDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PropertyDataEntity> page = this.page(
                new Query<PropertyDataEntity>().getPage(params),
                new QueryWrapper<PropertyDataEntity>().eq("del_flag",StatusConstants.del_flag_0).orderByDesc("sort")
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(cacheNames = RedisKeys.CDP_EVENT_PROPERTY_ALL_LIST,key = "#eventNameEn",cacheManager = RedisKeys.CACHEMANAGER_NAME)
    public List<EventPropertyDto> getEventPropertyList(String eventNameEn) {
        List<EventPropertyDto> eventPropertyList = propertyDataDao.getEventPropertyList(eventNameEn);
        return eventPropertyList;
    }

    @Override
    @Cacheable(cacheNames = RedisKeys.CDP_PROPERTY_ALL_LIST,key = "#isGroupView",cacheManager =  RedisKeys.CACHEMANAGER_NAME)
    public List<EventPropertyDto> getAllPropertyList(Integer isGroupView) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pd.del_flag", StatusConstants.del_flag_0);
        queryWrapper.eq("pd.status",StatusConstants.status_0);
        if(isGroupView != null && isGroupView != -1){
            queryWrapper.eq("is_group_view",isGroupView);
        }
        List<EventPropertyDto> eventPropertyList = propertyDataDao.getAllPropertyList(queryWrapper);
        return eventPropertyList;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {
            @CacheEvict(cacheNames = {RedisKeys.CDP_PROPERTY_ALL_LIST},key = "1",cacheManager =  RedisKeys.CACHEMANAGER_NAME),
            @CacheEvict(cacheNames = {RedisKeys.CDP_EVENT_PROPERTY_ALL_LIST},allEntries = true,cacheManager =  RedisKeys.CACHEMANAGER_NAME)
    })
    public boolean saveOrUpdate(PropertyDataEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public boolean checkPropertyExist(String propertyEn) {
        try{
            String sql = "select `"+propertyEn+"` from "+ClickhouseOptions.BASEDATA_TABLE_NAME+" where project = '"+ClickhouseOptions.PROJECT+"' limit 0,1";
            clickhouseBaseDao.execSql(sql);
        }catch (Exception e){
            throw new CustomExceptionException(RtnCode.MA_PROPERTY_NO_EXIST);
        }
        return true;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = {RedisKeys.CDP_PROPERTY_ALL_LIST},key = "1",cacheManager =  RedisKeys.CACHEMANAGER_NAME),
            @CacheEvict(cacheNames = {RedisKeys.CDP_EVENT_PROPERTY_ALL_LIST},allEntries = true,cacheManager =  RedisKeys.CACHEMANAGER_NAME)
    })
    public boolean update(Wrapper updateWrapper) {
        return super.update(updateWrapper);
    }
}
