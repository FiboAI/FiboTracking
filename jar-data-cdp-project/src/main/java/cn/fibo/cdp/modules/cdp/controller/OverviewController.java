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

import cn.fibo.cdp.modules.cdp.entity.OverviewEntity;
import cn.fibo.cdp.modules.cdp.service.OverviewService;
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
@RequestMapping("cdp/overview")
public class OverviewController {
    @Autowired
    private OverviewService overviewService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:overview:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = overviewService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:overview:info")
    public R info(@PathVariable("id") Long id){
		OverviewEntity overview = overviewService.getById(id);

        return R.ok().put("overview", overview);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:overview:save")
    public R save(@RequestBody OverviewEntity overview){
		overviewService.save(overview);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:overview:update")
    public R update(@RequestBody OverviewEntity overview){
		overviewService.updateById(overview);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:overview:delete")
    public R delete(@RequestBody Long[] ids){
		overviewService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
