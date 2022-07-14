

package cn.fibo.cdp.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.fibo.cdp.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author lisw
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

}
