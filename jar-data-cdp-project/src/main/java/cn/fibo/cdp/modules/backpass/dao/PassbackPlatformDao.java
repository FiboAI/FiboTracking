package cn.fibo.cdp.modules.backpass.dao;

import cn.fibo.cdp.modules.backpass.entity.PassbackPlatformEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 平台信息
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Mapper
public interface PassbackPlatformDao extends BaseMapper<PassbackPlatformEntity> {


    List<PassbackPlatformEntity> getListAll(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
