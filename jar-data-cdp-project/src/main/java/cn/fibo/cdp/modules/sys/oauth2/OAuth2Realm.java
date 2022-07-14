

package cn.fibo.cdp.modules.sys.oauth2;

import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.config.MySimpleByteSource;
import cn.fibo.cdp.modules.sys.entity.SysUserEntity;
import cn.fibo.cdp.modules.sys.service.ShiroService;
import cn.fibo.cdp.modules.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import springfox.documentation.service.OAuth;

import java.util.Set;

/**
 * 认证
 *
 * @author lisw
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
@Autowired
private RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        Object userToken = redisTemplate.opsForValue().get(RedisKeys.USER_LOGIN_TOKEN_KEY+accessToken);
        //token失效
        if(userToken == null){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        SysUserEntity user = shiroService.queryUser(Long.valueOf(userToken.toString()));
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
//        user.setId(user.getUserId());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
