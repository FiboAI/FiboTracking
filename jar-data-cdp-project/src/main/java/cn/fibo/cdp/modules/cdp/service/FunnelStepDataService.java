package cn.fibo.cdp.modules.cdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.FunnelStepDataEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface FunnelStepDataService extends IService<FunnelStepDataEntity> {

    PageUtils queryPage(Map<String, Object> params);

//    List<FunnelStepDataEntity> getFunnelStepDataEntityList(Long funnelId);
}

