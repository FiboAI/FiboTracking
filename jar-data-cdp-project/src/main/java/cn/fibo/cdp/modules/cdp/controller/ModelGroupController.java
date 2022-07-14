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

import cn.fibo.cdp.modules.cdp.entity.ModelGroupEntity;
import cn.fibo.cdp.modules.cdp.service.ModelGroupService;
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
@RequestMapping("cdp/modelgroup")
public class ModelGroupController {
    @Autowired
    private ModelGroupService modelGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:modelgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:modelgroup:info")
    public R info(@PathVariable("id") Long id){
		ModelGroupEntity modelGroup = modelGroupService.getById(id);

        return R.ok().put("modelGroup", modelGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:modelgroup:save")
    public R save(@RequestBody ModelGroupEntity modelGroup){
		modelGroupService.save(modelGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:modelgroup:update")
    public R update(@RequestBody ModelGroupEntity modelGroup){
		modelGroupService.updateById(modelGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:modelgroup:delete")
    public R delete(@RequestBody Long[] ids){
		modelGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
