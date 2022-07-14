package cn.fibo.cdp.modules.backpass.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackTaskDataDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackTaskDataEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackTaskDataService;


@Service("passbackTaskDataService")
public class PassbackTaskDataServiceImpl extends ServiceImpl<PassbackTaskDataDao, PassbackTaskDataEntity> implements PassbackTaskDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackTaskDataEntity> page = this.page(
                new Query<PassbackTaskDataEntity>().getPage(params),
                new QueryWrapper<PassbackTaskDataEntity>()
        );

        return new PageUtils(page);
    }

}