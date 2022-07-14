

package cn.fibo.cdp.modules.sys.service;


import cn.fibo.cdp.modules.sys.entity.SysMenuEntity;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 菜单管理
 *
 * @author lisw
 */
public interface SysMenuService extends IService<SysMenuEntity> {

	/**
	 * 根据用户获取用户权限下的菜单
	 * @param userId
	 * @return
	 */
	List<SysMenuEntity> queryMenuTreeListByUserId(@Param("userId") Long userId);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();

	/**
	 * 获取用户菜单列表
	 */
	JSONArray getUserMenuList(Long userId);

	/**
	 * 删除
	 */
	void delete(Long menuId);
}
