package cn.fibo.cdp.modules.cdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.MetadataCustomsqlDataEntity;

import java.util.Map;

/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-06-24 10:20:27
 */
public interface MetadataCustomsqlDataService extends IService<MetadataCustomsqlDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

