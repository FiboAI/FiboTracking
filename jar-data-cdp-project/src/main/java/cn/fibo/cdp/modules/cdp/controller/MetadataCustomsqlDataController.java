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

import cn.fibo.cdp.modules.cdp.entity.MetadataCustomsqlDataEntity;
import cn.fibo.cdp.modules.cdp.service.MetadataCustomsqlDataService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-06-24 10:20:27
 */
@RestController
@RequestMapping("cdp/metadatacustomsqldata")
public class MetadataCustomsqlDataController {
    @Autowired
    private MetadataCustomsqlDataService metadataCustomsqlDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:metadatacustomsqldata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = metadataCustomsqlDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:metadatacustomsqldata:info")
    public R info(@PathVariable("id") Long id){
		MetadataCustomsqlDataEntity metadataCustomsqlData = metadataCustomsqlDataService.getById(id);

        return R.ok().put("metadataCustomsqlData", metadataCustomsqlData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:metadatacustomsqldata:save")
    public R save(@RequestBody MetadataCustomsqlDataEntity metadataCustomsqlData){
		metadataCustomsqlDataService.save(metadataCustomsqlData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:metadatacustomsqldata:update")
    public R update(@RequestBody MetadataCustomsqlDataEntity metadataCustomsqlData){
		metadataCustomsqlDataService.updateById(metadataCustomsqlData);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:metadatacustomsqldata:delete")
    public R delete(@RequestBody Long[] ids){
		metadataCustomsqlDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
