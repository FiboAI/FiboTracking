package cn.fibo.cdp.modules.cdp.service;

import cn.fibo.cdp.modules.cdp.entity.param.VirturalEventDetailParam;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.EventVirtualDataEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 虚拟事件明细表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 18:06:17
 */
public interface EventVirtualDataService extends IService<EventVirtualDataEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<VirturalEventDetailParam> getVirturalEventDetailParams(String virtualEventEn);

}

