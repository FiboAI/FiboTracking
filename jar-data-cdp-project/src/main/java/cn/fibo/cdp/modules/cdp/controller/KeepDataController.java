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

import cn.fibo.cdp.modules.cdp.entity.KeepDataEntity;
import cn.fibo.cdp.modules.cdp.service.KeepDataService;
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
@RequestMapping("cdp/keepdata")
public class KeepDataController {
    @Autowired
    private KeepDataService keepDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:keepdata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = keepDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:keepdata:info")
    public R info(@PathVariable("id") Long id){
		KeepDataEntity keepData = keepDataService.getById(id);

        return R.ok().put("keepData", keepData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:keepdata:save")
    public R save(@RequestBody KeepDataEntity keepData){
		keepDataService.save(keepData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:keepdata:update")
    public R update(@RequestBody KeepDataEntity keepData){
		keepDataService.updateById(keepData);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:keepdata:delete")
    public R delete(@RequestBody Long[] ids){
		keepDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
