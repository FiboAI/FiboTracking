package cn.fibo.cdp.modules.backpass.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackStrategyDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackStrategyEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackStrategyService;


@Service("passbackStrategyService")
public class PassbackStrategyServiceImpl extends ServiceImpl<PassbackStrategyDao, PassbackStrategyEntity> implements PassbackStrategyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackStrategyEntity> page = this.page(
                new Query<PassbackStrategyEntity>().getPage(params),
                new QueryWrapper<PassbackStrategyEntity>()
        );

        return new PageUtils(page);
    }

}