package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.ModelWhereDao;
import cn.fibo.cdp.modules.cdp.entity.ModelWhereEntity;
import cn.fibo.cdp.modules.cdp.service.ModelWhereService;


@Service("modelWhereService")
public class ModelWhereServiceImpl extends ServiceImpl<ModelWhereDao, ModelWhereEntity> implements ModelWhereService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelWhereEntity> page = this.page(
                new Query<ModelWhereEntity>().getPage(params),
                new QueryWrapper<ModelWhereEntity>()
        );

        return new PageUtils(page);
    }

}