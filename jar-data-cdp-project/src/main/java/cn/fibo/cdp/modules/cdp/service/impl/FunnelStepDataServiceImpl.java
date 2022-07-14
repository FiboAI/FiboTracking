package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.FunnelStepDataDao;
import cn.fibo.cdp.modules.cdp.entity.FunnelStepDataEntity;
import cn.fibo.cdp.modules.cdp.service.FunnelStepDataService;


@Service("funnelStepDataService")
public class FunnelStepDataServiceImpl extends ServiceImpl<FunnelStepDataDao, FunnelStepDataEntity> implements FunnelStepDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FunnelStepDataEntity> page = this.page(
                new Query<FunnelStepDataEntity>().getPage(params),
                new QueryWrapper<FunnelStepDataEntity>()
        );

        return new PageUtils(page);
    }

//    @Override
//    public List<FunnelStepDataEntity> getFunnelStepDataEntityList(Long funnelId) {
//       return baseMapper.getFunnelStepDataEntityList(funnelId);
//    }

}
