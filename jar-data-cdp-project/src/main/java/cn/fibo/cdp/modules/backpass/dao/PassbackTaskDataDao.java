package cn.fibo.cdp.modules.backpass.dao;

import cn.fibo.cdp.modules.backpass.entity.PassbackTaskDataEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时跑批数据暂存
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Mapper
public interface PassbackTaskDataDao extends BaseMapper<PassbackTaskDataEntity> {
	
}
