package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.KeepDataDao;
import cn.fibo.cdp.modules.cdp.entity.KeepDataEntity;
import cn.fibo.cdp.modules.cdp.service.KeepDataService;


@Service("keepDataService")
public class KeepDataServiceImpl extends ServiceImpl<KeepDataDao, KeepDataEntity> implements KeepDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<KeepDataEntity> page = this.page(
                new Query<KeepDataEntity>().getPage(params),
                new QueryWrapper<KeepDataEntity>()
        );

        return new PageUtils(page);
    }

}