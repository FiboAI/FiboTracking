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

import cn.fibo.cdp.modules.cdp.entity.FunnelDataEntity;
import cn.fibo.cdp.modules.cdp.service.FunnelDataService;
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
@RequestMapping("cdp/funneldata")
public class FunnelDataController {
    @Autowired
    private FunnelDataService funnelDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:funneldata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = funnelDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:funneldata:info")
    public R info(@PathVariable("id") Long id){
		FunnelDataEntity funnelData = funnelDataService.getById(id);

        return R.ok().put("funnelData", funnelData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:funneldata:save")
    public R save(@RequestBody FunnelDataEntity funnelData){
		funnelDataService.save(funnelData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:funneldata:update")
    public R update(@RequestBody FunnelDataEntity funnelData){
		funnelDataService.updateById(funnelData);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:funneldata:delete")
    public R delete(@RequestBody Long[] ids){
		funnelDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
