package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.ModelSelectDao;
import cn.fibo.cdp.modules.cdp.entity.ModelSelectEntity;
import cn.fibo.cdp.modules.cdp.service.ModelSelectService;


@Service("modelSelectService")
public class ModelSelectServiceImpl extends ServiceImpl<ModelSelectDao, ModelSelectEntity> implements ModelSelectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelSelectEntity> page = this.page(
                new Query<ModelSelectEntity>().getPage(params),
                new QueryWrapper<ModelSelectEntity>()
        );

        return new PageUtils(page);
    }

}