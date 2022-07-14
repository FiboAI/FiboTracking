package cn.fibo.cdp.modules.backpass.dao;

import cn.fibo.cdp.modules.backpass.entity.PassbackRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回传记录
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@Mapper
public interface PassbackRecordDao extends BaseMapper<PassbackRecordEntity> {
	
}
