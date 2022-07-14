package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.PropertyDataEntity;
import cn.fibo.cdp.modules.cdp.entity.dto.EventPropertyDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface PropertyDataDao extends BaseMapper<PropertyDataEntity> {


    List<EventPropertyDto> getEventPropertyList(@Param("eventEn") String eventEn);

    List<EventPropertyDto> getAllPropertyList(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}
