package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.EventGroupEntity;
import cn.fibo.cdp.modules.cdp.entity.dto.EventGroupDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 事件分组
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface EventGroupDao extends BaseMapper<EventGroupEntity> {

    List<EventGroupDto> getEventGroupList(@Param(Constants.WRAPPER)QueryWrapper queryWrapper);

}
