package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.FunnelDataEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Wrapper;
import java.util.List;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface FunnelDataDao extends BaseMapper<FunnelDataEntity> {

    int deleteFunnelStepByFunnelId(@Param("funnelId") Long funnelId);

    FunnelDataEntity getDetail(@Param("funnelId") Long funnelId);

    List<FunnelDataEntity> getList(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
