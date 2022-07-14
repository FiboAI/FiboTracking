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

import cn.fibo.cdp.modules.cdp.entity.ModelSelectEntity;
import cn.fibo.cdp.modules.cdp.service.ModelSelectService;
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
@RequestMapping("cdp/modelselect")
public class ModelSelectController {
    @Autowired
    private ModelSelectService modelSelectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:modelselect:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelSelectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:modelselect:info")
    public R info(@PathVariable("id") Long id){
		ModelSelectEntity modelSelect = modelSelectService.getById(id);

        return R.ok().put("modelSelect", modelSelect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:modelselect:save")
    public R save(@RequestBody ModelSelectEntity modelSelect){
		modelSelectService.save(modelSelect);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:modelselect:update")
    public R update(@RequestBody ModelSelectEntity modelSelect){
		modelSelectService.updateById(modelSelect);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:modelselect:delete")
    public R delete(@RequestBody Long[] ids){
		modelSelectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
