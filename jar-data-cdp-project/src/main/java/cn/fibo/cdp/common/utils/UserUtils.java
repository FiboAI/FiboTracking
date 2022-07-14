package cn.fibo.cdp.common.utils;

import cn.fibo.cdp.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

/**
 * @author lisw
 * @program ly-project
 * @description
 * @createDate 2021-05-23 22:46:44
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class UserUtils {
    public static Long getUserId(){
        Subject subject = ThreadContext.getSubject();
        if(subject == null){
            return null;
        }
        if(SecurityUtils.getSubject()!=null && SecurityUtils.getSubject().getPrincipal()!=null){
            SysUserEntity sysUserEntity =  (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            return sysUserEntity.getUserId();
        }
        return  null;
    }

    public static boolean isAdmin(){
        Subject subject = ThreadContext.getSubject();
        if(subject == null){
            return false;
        }
        if(SecurityUtils.getSubject()!=null && SecurityUtils.getSubject().getPrincipal()!=null){
            SysUserEntity sysUserEntity =  (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            return sysUserEntity.getUserId()==1L;
        }
        return  false;
    }
}
