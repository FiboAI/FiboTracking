package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.enums.WhereSourceEnums;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.modules.cdp.entity.FunnelStepDataEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelWhereEntity;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitWherePropertyParam;
import cn.fibo.cdp.modules.cdp.service.FunnelStepDataService;
import cn.fibo.cdp.modules.cdp.service.ModelWhereService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.Query;

import cn.fibo.cdp.modules.cdp.dao.FunnelDataDao;
import cn.fibo.cdp.modules.cdp.entity.FunnelDataEntity;
import cn.fibo.cdp.modules.cdp.service.FunnelDataService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("funnelDataService")
public class FunnelDataServiceImpl extends ServiceImpl<FunnelDataDao, FunnelDataEntity> implements FunnelDataService {

    @Autowired
    private FunnelDataDao funnelDataDao;

    @Autowired
    private FunnelStepDataService funnelStepDataService;

    @Autowired
    private ModelWhereService modelWhereService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FunnelDataEntity> page = this.page(
                new Query<FunnelDataEntity>().getPage(params),
                new QueryWrapper<FunnelDataEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateInfo(FunnelDataEntity funnelDataEntity) {
        /**
         * 删除步骤及步骤中的筛选条件
         */
        if(funnelDataEntity!=null && funnelDataEntity.getId()!=null){
            funnelDataDao.deleteFunnelStepByFunnelId(funnelDataEntity.getId());
        }
        this.saveOrUpdate(funnelDataEntity);

        /**
         * 保存步骤
         */
        List<FunnelStepDataEntity> funnelStepDataEntityList = funnelDataEntity.getFunnelStepDataEntityList();
        if(!CollectionUtils.isEmpty(funnelStepDataEntityList)){
            funnelStepDataEntityList.forEach(item ->{
                item.setFunnelId(funnelDataEntity.getId());
                List<SubmitWherePropertyParam> wherePropertyParams = item.getWherePropertyParams();
                if(!CollectionUtils.isEmpty(wherePropertyParams)){
                    String whereUUid = StringUtils.getUUID();
                    List<ModelWhereEntity> modelWhereEntityList = new ArrayList<>();
                    wherePropertyParams.forEach(where->{
                        ModelWhereEntity modelWhereEntity  = new ModelWhereEntity();
                        BeanUtils.copyProperties(item,modelWhereEntity);
                        modelWhereEntity.setUuid(whereUUid);
                        modelWhereEntity.setSource(WhereSourceEnums.funnel_step.getCode());
                        modelWhereEntityList.add(modelWhereEntity);
                    });
                    modelWhereService.saveBatch(modelWhereEntityList);
                    item.setWhereUuid(whereUUid);
                }
            });
            funnelStepDataService.saveOrUpdateBatch(funnelStepDataEntityList);
        }


        return true;
    }

    @Override
    public FunnelDataEntity getDetail(Long funnelId) {
        return funnelDataDao.getDetail(funnelId);
    }

    @Override
    public List<FunnelDataEntity> getList(QueryWrapper queryWrapper) {
        return funnelDataDao.getList(queryWrapper);
    }


}
