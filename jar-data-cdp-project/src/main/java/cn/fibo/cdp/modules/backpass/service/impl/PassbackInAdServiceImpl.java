package cn.fibo.cdp.modules.backpass.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackInAdDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackInAdEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackInAdService;


@Service("passbackInAdService")
public class PassbackInAdServiceImpl extends ServiceImpl<PassbackInAdDao, PassbackInAdEntity> implements PassbackInAdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackInAdEntity> page = this.page(
                new Query<PassbackInAdEntity>().getPage(params),
                new QueryWrapper<PassbackInAdEntity>()
        );

        return new PageUtils(page);
    }

}