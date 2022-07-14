package cn.fibo.cdp.modules.cdp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.FunnelDataEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface FunnelDataService extends IService<FunnelDataEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveOrUpdateInfo(FunnelDataEntity funnelDataEntity);

    FunnelDataEntity getDetail(Long funnelId);

    List<FunnelDataEntity> getList(QueryWrapper queryWrapper);
}

