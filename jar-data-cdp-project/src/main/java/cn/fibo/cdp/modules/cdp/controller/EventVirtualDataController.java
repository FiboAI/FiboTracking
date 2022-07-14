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

import cn.fibo.cdp.modules.cdp.entity.EventVirtualDataEntity;
import cn.fibo.cdp.modules.cdp.service.EventVirtualDataService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 虚拟事件明细表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 18:06:17
 */
@RestController
@RequestMapping("cdp/eventvirtualdata")
public class EventVirtualDataController {
    @Autowired
    private EventVirtualDataService eventVirtualDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:eventvirtualdata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = eventVirtualDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:eventvirtualdata:info")
    public R info(@PathVariable("id") Long id){
		EventVirtualDataEntity eventVirtualData = eventVirtualDataService.getById(id);

        return R.ok().put("eventVirtualData", eventVirtualData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:eventvirtualdata:save")
    public R save(@RequestBody EventVirtualDataEntity eventVirtualData){
		eventVirtualDataService.save(eventVirtualData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:eventvirtualdata:update")
    public R update(@RequestBody EventVirtualDataEntity eventVirtualData){
		eventVirtualDataService.updateById(eventVirtualData);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:eventvirtualdata:delete")
    public R delete(@RequestBody Long[] ids){
		eventVirtualDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
