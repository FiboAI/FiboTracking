package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.FunnelStepDataEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface FunnelStepDataDao extends BaseMapper<FunnelStepDataEntity> {
//    List<FunnelStepDataEntity> getFunnelStepDataEntityList(@Param("funnelId") Long funnelId);

}
