package cn.fibo.cdp.modules.cdp.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.MetadataCustomsqlDataDao;
import cn.fibo.cdp.modules.cdp.entity.MetadataCustomsqlDataEntity;
import cn.fibo.cdp.modules.cdp.service.MetadataCustomsqlDataService;


@Service("metadataCustomsqlDataService")
public class MetadataCustomsqlDataServiceImpl extends ServiceImpl<MetadataCustomsqlDataDao, MetadataCustomsqlDataEntity> implements MetadataCustomsqlDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MetadataCustomsqlDataEntity> page = this.page(
                new Query<MetadataCustomsqlDataEntity>().getPage(params),
                new QueryWrapper<MetadataCustomsqlDataEntity>()
        );

        return new PageUtils(page);
    }

}