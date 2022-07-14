

package cn.fibo.cdp.service;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.sys.dao.SysUserDao;
import cn.fibo.cdp.modules.sys.entity.SysUserEntity;
import cn.fibo.cdp.modules.tracking.dao.TrackingBaseDataDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试多数据源
 *
 * @author lisw
 */
@Service
//@DataSource("slave1")
public class DynamicDataSourceTestService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private TrackingBaseDataDao trackingBaseDataDao;

    @Transactional
    public void updateUser(Long id){
        SysUserEntity user = new SysUserEntity();
        user.setUserId(id);
        user.setMobile("13500000000");
        sysUserDao.updateById(user);
    }

    @Transactional
    @DataSource("slave1")
    public void updateUserBySlave1(Long id){
        SysUserEntity user = new SysUserEntity();
        user.setUserId(id);
        user.setMobile("13500000001");
        sysUserDao.updateById(user);
    }

    @DataSource("slave2")
    @Transactional
    public void updateUserBySlave2(Long id){
        SysUserEntity user = new SysUserEntity();
        user.setUserId(id);
        user.setMobile("13500000002");
        sysUserDao.updateById(user);

        //测试事物
        int i = 1/0;
    }

    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public void testClickHouse(){
        Integer count = trackingBaseDataDao.getCount();
        System.out.println("==========="+count);
    }
}
