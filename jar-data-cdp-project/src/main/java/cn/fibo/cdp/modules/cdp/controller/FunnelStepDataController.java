package cn.fibo.cdp.modules.cdp.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fibo.cdp.modules.cdp.entity.FunnelStepDataEntity;
import cn.fibo.cdp.modules.cdp.service.FunnelStepDataService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@RestController
@RequestMapping("cdp/funnelstepdata")
public class FunnelStepDataController {
    @Autowired
    private FunnelStepDataService funnelStepDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:funnelstepdata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = funnelStepDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:funnelstepdata:info")
    public R info(@PathVariable("id") Long id){
		FunnelStepDataEntity funnelStepData = funnelStepDataService.getById(id);

        return R.ok().put("funnelStepData", funnelStepData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:funnelstepdata:save")
    public R save(@RequestBody FunnelStepDataEntity funnelStepData){
		funnelStepDataService.save(funnelStepData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:funnelstepdata:update")
    public R update(@RequestBody FunnelStepDataEntity funnelStepData){
		funnelStepDataService.updateById(funnelStepData);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:funnelstepdata:delete")
    public R delete(@RequestBody Long[] ids){
		funnelStepDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
