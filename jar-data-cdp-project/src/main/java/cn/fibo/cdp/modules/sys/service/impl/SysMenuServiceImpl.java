

package cn.fibo.cdp.modules.sys.service.impl;


import cn.fibo.cdp.common.utils.Constant;
import cn.fibo.cdp.common.utils.MapUtils;
import cn.fibo.cdp.common.utils.UserUtils;
import cn.fibo.cdp.modules.sys.dao.SysMenuDao;
import cn.fibo.cdp.modules.sys.entity.SysMenuEntity;
import cn.fibo.cdp.modules.sys.service.SysMenuService;
import cn.fibo.cdp.modules.sys.service.SysRoleMenuService;
import cn.fibo.cdp.modules.sys.service.SysUserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Override
	public List<SysMenuEntity> queryMenuTreeListByUserId(Long userId) {
		return baseMapper.queryMenuTreeListByUserId(userId);
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}

		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryAllMenuTreeList();
	}

	@Override
	public JSONArray getUserMenuList(Long userId) {
		List<SysMenuEntity> sysMenuEntities;
		//系统管理员，拥有最高权限
		if(UserUtils.isAdmin()){
			QueryWrapper queryWrapper = new QueryWrapper();
			sysMenuEntities = baseMapper.selectList(queryWrapper);
		}else{
			sysMenuEntities = baseMapper.queryMenuTreeListByUserId(userId);
		}
		return getMenuList(sysMenuEntities,0,null);
	}

	/**
	 * 获取拥有的菜单列表
	 * @return
	 */
	private JSONArray getMenuList(List<SysMenuEntity> sysMenuEntities,Integer level,Long menuId) {
		JSONArray array = new JSONArray();
		if(level==0){
			for (SysMenuEntity item : sysMenuEntities) {
				if(item.getType().equals(level)){
					JSONObject object = new JSONObject();
					object.put("title",item.getName());
					object.put("key",item.getMenuId());
					object.put(item.getIcon(),true);
					object.put("sub",getMenuList(sysMenuEntities,1,item.getMenuId()));
					array.add(object);
				}
			}
		}else if(level==1){
			for (SysMenuEntity item : sysMenuEntities) {
				if(item.getParentId().equals(menuId)){
					JSONObject object = new JSONObject();
					object.put("title",item.getName());
					object.put("key",item.getUrl());
					object.put("disabled",item.getDisabled());
					array.add(object);
				}
			}
		}
		return array;
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.removeById(menuId);
		//删除菜单与角色关联
		sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

		for(SysMenuEntity entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}

		return subMenuList;
	}
}
