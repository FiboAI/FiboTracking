package cn.fibo.cdp.modules.backpass.service;

import cn.fibo.cdp.modules.backpass.entity.PassbackPlatformEntity;
import cn.fibo.cdp.modules.backpass.entity.platform.PlatformEvent;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.backpass.entity.PassbackConfigEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 回传配置
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
public interface PassbackConfigService extends IService<PassbackConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);


    @Override
    boolean saveOrUpdate(PassbackConfigEntity passbackConfig);

    public List<PlatformEvent> getList();
}

