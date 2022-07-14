

package cn.fibo.cdp.modules.sys.service.impl;

import cn.fibo.cdp.common.utils.Constant;
import cn.fibo.cdp.common.utils.UserUtils;
import cn.fibo.cdp.modules.sys.dao.SysMenuDao;
import cn.fibo.cdp.modules.sys.dao.SysUserDao;
import cn.fibo.cdp.modules.sys.entity.SysMenuEntity;
import cn.fibo.cdp.modules.sys.entity.SysUserEntity;
import cn.fibo.cdp.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(UserUtils.isAdmin()){
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }


    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.selectById(userId);
    }
}
