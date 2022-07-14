

package cn.fibo.cdp.modules.sys.dao;

import cn.fibo.cdp.modules.sys.entity.SysUserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author lisw
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);


	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
