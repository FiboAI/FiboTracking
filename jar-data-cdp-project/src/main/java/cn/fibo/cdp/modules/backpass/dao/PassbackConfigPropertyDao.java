package cn.fibo.cdp.modules.backpass.dao;

import cn.fibo.cdp.modules.backpass.entity.PassbackConfigPropertyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 回传配置附加属性
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Mapper
public interface PassbackConfigPropertyDao extends BaseMapper<PassbackConfigPropertyEntity> {

    int deletePropertyByConfigId(@Param("configId") Long configId);
}
