package cn.fibo.cdp.modules.backpass.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackConfigPropertyDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackConfigPropertyEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackConfigPropertyService;


@Service("passbackConfigPropertyService")
public class PassbackConfigPropertyServiceImpl extends ServiceImpl<PassbackConfigPropertyDao, PassbackConfigPropertyEntity> implements PassbackConfigPropertyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackConfigPropertyEntity> page = this.page(
                new Query<PassbackConfigPropertyEntity>().getPage(params),
                new QueryWrapper<PassbackConfigPropertyEntity>()
        );

        return new PageUtils(page);
    }

}