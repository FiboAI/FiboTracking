package cn.fibo.cdp.modules.cdp.service;

import cn.fibo.cdp.modules.cdp.entity.dto.EventPropertyDto;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.PropertyDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface PropertyDataService extends IService<PropertyDataEntity> {

    PageUtils queryPage(Map<String, Object> params);


    List<EventPropertyDto> getEventPropertyList(String eventNameEn);

    List<EventPropertyDto> getAllPropertyList(Integer isGroupView);

   @Override
   boolean saveOrUpdate(PropertyDataEntity entity);

   boolean checkPropertyExist(String propertyEn);

   @Override
   boolean update(Wrapper updateWrapper);



}

