

package cn.fibo.cdp.modules.sys.controller;

import cn.fibo.cdp.common.annotation.SysLog;
import cn.fibo.cdp.common.constants.PermissionConstants;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.common.utils.Constant;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;
import cn.fibo.cdp.common.utils.UserUtils;
import cn.fibo.cdp.common.validator.ValidatorUtils;
import cn.fibo.cdp.modules.sys.entity.SysRoleEntity;
import cn.fibo.cdp.modules.sys.service.SysRoleMenuService;
import cn.fibo.cdp.modules.sys.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author lisw
 */
@RestController
@RequestMapping("/sys/role")
@RequiresPermissions(PermissionConstants.SYS_ROLE)
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@PostMapping("/list")
//	@RequiresPermissions("sys:role:list")
	public R list(@RequestBody Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			params.put("createUserId", getUserId());
//		}

		PageUtils page = sysRoleService.queryPage(params);

		return R.okDatas(page);
	}

	/**
	 * 角色列表
	 */
	@GetMapping("/select")
//	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();

		//如果不是超级管理员，则只查询自己所拥有的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			map.put("create_user_id", getUserId());
//		}
		map.put("del_flag",StatusConstants.del_flag_0);
		List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);

		return R.okDatas(list);
	}

	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
//	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);

		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return R.okDatas(role);
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/saveOrUpdate")
//	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		role.setCreateUserId(getUserId());
		if(role.getRoleId()==null){
			role.setDelFlag(StatusConstants.del_flag_0);
			sysRoleService.saveRole(role);
		}else{
			sysRoleService.update(role);
		}
		return R.ok();
	}

//	/**
//	 * 修改角色
//	 */
//	@SysLog("修改角色")
//	@PostMapping("/update")
////	@RequiresPermissions("sys:role:update")
//	public R update(@RequestBody SysRoleEntity role){
//		ValidatorUtils.validateEntity(role);
//
//		role.setCreateUserId(getUserId());
//		sysRoleService.update(role);
//
//		return R.ok();
//	}

	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
//	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Map<String,Long> params){
		if(!params.containsKey("roleId")){
			throw new CustomExceptionException(RtnCode.HTTP_PARAMS_ERROR);
		}
		Long userId = params.get("roleId");
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("role_id",userId);
		updateWrapper.set("del_flag", StatusConstants.del_flag_1);
		sysRoleService.update(updateWrapper);
		return R.ok();
	}
}
