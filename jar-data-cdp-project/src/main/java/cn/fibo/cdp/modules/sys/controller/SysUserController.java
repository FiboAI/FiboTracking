

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
import cn.fibo.cdp.common.validator.Assert;
import cn.fibo.cdp.common.validator.ValidatorUtils;
import cn.fibo.cdp.common.validator.group.AddGroup;
import cn.fibo.cdp.common.validator.group.UpdateGroup;
import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;
import cn.fibo.cdp.modules.sys.entity.SysUserEntity;
import cn.fibo.cdp.modules.sys.form.PasswordForm;
import cn.fibo.cdp.modules.sys.service.SysUserRoleService;
import cn.fibo.cdp.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author lisw
 */
@RestController
@RequestMapping("/sys/user")
@RequiresPermissions(PermissionConstants.SYS_USER)
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	/**
	 * 所有用户列表
	 */
	@PostMapping("/list")
	@ApiOperation("获取列表")
//	@RequiresPermissions("sys:user:list")
	public R list(@RequestBody Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
//		if(!UserUtils.isAdmin()){
//			params.put("createUserId", getUserId());
//		}
		PageUtils page = sysUserService.queryPage(params);

		return R.okDatas(page);
	}

	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.okDatas(getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@ApiOperation("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");

		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@ApiOperation("获取详情")
//	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return R.okDatas(user);
	}

	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@ApiOperation("保存用户")
	@PostMapping("/saveOrUpdate")
//	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("username",user.getUsername());
		queryWrapper.eq("del_flag",StatusConstants.del_flag_0);
		if(user.getUserId()!=null){
			queryWrapper.ne("user_id",user.getUserId());
		}
		SysUserEntity one = sysUserService.getOne(queryWrapper);
		if(one!=null){
			throw new CustomExceptionException(RtnCode.CDP_SYS_USERNAME_EXIST);
		}
		if(user.getUserId() == null){
			if(StringUtils.isBlank(user.getPassword())){
				user.setPassword(Constant.DEFAULT_PASSWORD);
			}
			ValidatorUtils.validateEntity(user, AddGroup.class);
			user.setCreateUserId(getUserId());
			user.setDelFlag(StatusConstants.del_flag_0);
			user.setStatus(StatusConstants.status_1);
			sysUserService.saveUser(user);
		}else{
			ValidatorUtils.validateEntity(user, UpdateGroup.class);
			user.setCreateUserId(getUserId());
			sysUserService.update(user);
		}
		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@ApiOperation("删除用户")
	@PostMapping("/delete")
//	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Map<String,Long> params){
		if(!params.containsKey("userId")){
			throw new CustomExceptionException(RtnCode.HTTP_PARAMS_ERROR);
		}
		Long userId = params.get("userId");
		if(userId == 1L){
			return R.error("系统管理员不能删除");
		}

		if(userId.equals(UserUtils.getUserId())){
			return R.error("当前用户不能删除");
		}
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("user_id",userId);
		updateWrapper.set("del_flag", StatusConstants.del_flag_1);
		sysUserService.update(updateWrapper);
		return R.ok();
	}

}
