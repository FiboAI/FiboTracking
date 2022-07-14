package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.ModelGroupDao;
import cn.fibo.cdp.modules.cdp.entity.ModelGroupEntity;
import cn.fibo.cdp.modules.cdp.service.ModelGroupService;


@Service("modelGroupService")
public class ModelGroupServiceImpl extends ServiceImpl<ModelGroupDao, ModelGroupEntity> implements ModelGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelGroupEntity> page = this.page(
                new Query<ModelGroupEntity>().getPage(params),
                new QueryWrapper<ModelGroupEntity>()
        );

        return new PageUtils(page);
    }

}