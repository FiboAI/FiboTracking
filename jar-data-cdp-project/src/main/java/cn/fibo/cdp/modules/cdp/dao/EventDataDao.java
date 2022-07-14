package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 事件定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface EventDataDao extends BaseMapper<EventDataEntity> {

    EventDataEntity getDetailById(@Param(Constants.WRAPPER)QueryWrapper queryWrapper);

}
