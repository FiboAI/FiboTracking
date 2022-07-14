package cn.fibo.cdp.modules.backpass.service.impl;

import cn.fibo.cdp.modules.backpass.dao.PassbackConfigPropertyDao;
import cn.fibo.cdp.modules.backpass.entity.platform.PlatformEvent;
import cn.fibo.cdp.modules.backpass.service.PassbackConfigPropertyService;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackConfigDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackConfigEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackConfigService;


@Service("passbackConfigService")
public class PassbackConfigServiceImpl extends ServiceImpl<PassbackConfigDao, PassbackConfigEntity> implements PassbackConfigService {

    @Autowired
    private PassbackConfigPropertyDao passbackConfigPropertyDao;

    @Autowired
    private PassbackConfigPropertyService passbackConfigPropertyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackConfigEntity> page = this.page(
                new Query<PassbackConfigEntity>().getPage(params),
                new QueryWrapper<PassbackConfigEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveOrUpdate(PassbackConfigEntity passbackConfig) {
        //修改时，清空分路关系表
        if(passbackConfig.getId()!=null){
            passbackConfigPropertyDao.deletePropertyByConfigId(passbackConfig.getId());
        }

        //保存回传配置
        super.saveOrUpdate(passbackConfig);


        //保存属性对应关系分路表
        if(!CollectionUtils.isEmpty(passbackConfig.getPropertyEntityList())){
            passbackConfigPropertyService.saveBatch(passbackConfig.getPropertyEntityList());
        }

        return true;
    }

    @Override
    public List<PlatformEvent> getList() {
        return null;
    }

}
