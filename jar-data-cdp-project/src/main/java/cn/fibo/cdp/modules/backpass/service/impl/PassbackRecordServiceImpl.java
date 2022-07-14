package cn.fibo.cdp.modules.backpass.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.backpass.dao.PassbackRecordDao;
import cn.fibo.cdp.modules.backpass.entity.PassbackRecordEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackRecordService;


@Service("passbackRecordService")
public class PassbackRecordServiceImpl extends ServiceImpl<PassbackRecordDao, PassbackRecordEntity> implements PassbackRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PassbackRecordEntity> page = this.page(
                new Query<PassbackRecordEntity>().getPage(params),
                new QueryWrapper<PassbackRecordEntity>()
        );

        return new PageUtils(page);
    }

}