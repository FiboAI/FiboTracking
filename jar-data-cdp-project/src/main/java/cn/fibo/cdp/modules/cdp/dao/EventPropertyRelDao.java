package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.EventPropertyRelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件属性关联关系表
 * 
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface EventPropertyRelDao extends BaseMapper<EventPropertyRelEntity> {
	
}
