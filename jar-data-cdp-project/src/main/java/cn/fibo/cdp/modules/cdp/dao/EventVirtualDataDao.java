package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.EventVirtualDataEntity;
import cn.fibo.cdp.modules.cdp.entity.param.VirturalEventDetailParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 虚拟事件明细表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 18:06:17
 */
@Mapper
public interface EventVirtualDataDao extends BaseMapper<EventVirtualDataEntity> {

    List<VirturalEventDetailParam> getVirturalEventDetailParams(@Param("virtualEventEn") String virtualEventEn);
}
