

package cn.fibo.cdp.modules.sys.service;

import cn.fibo.cdp.modules.sys.entity.SysUserEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author lisw
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);


    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
