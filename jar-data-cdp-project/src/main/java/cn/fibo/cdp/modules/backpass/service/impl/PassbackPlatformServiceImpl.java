package cn.fibo.cdp.modules.backpass.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackPlatformDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackPlatformEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackPlatformService;


@Service("passbackPlatformService")
public class PassbackPlatformServiceImpl extends ServiceImpl<PassbackPlatformDao, PassbackPlatformEntity> implements PassbackPlatformService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackPlatformEntity> page = this.page(
                new Query<PassbackPlatformEntity>().getPage(params),
                new QueryWrapper<PassbackPlatformEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PassbackPlatformEntity> getListAll(QueryWrapper queryWrapper) {
        return baseMapper.getListAll(queryWrapper);
    }

}
