

package cn.fibo.cdp.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.fibo.cdp.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理
 *
 * @author lisw
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryAllMenuTreeList();

	List<SysMenuEntity> queryMenuTreeListByUserId(@Param("userId") Long userId);

}
