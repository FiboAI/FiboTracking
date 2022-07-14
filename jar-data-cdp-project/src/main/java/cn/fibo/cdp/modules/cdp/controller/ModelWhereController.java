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

import cn.fibo.cdp.modules.cdp.entity.ModelWhereEntity;
import cn.fibo.cdp.modules.cdp.service.ModelWhereService;
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
@RequestMapping("cdp/modelwhere")
public class ModelWhereController {
    @Autowired
    private ModelWhereService modelWhereService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:modelwhere:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelWhereService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:modelwhere:info")
    public R info(@PathVariable("id") Long id){
		ModelWhereEntity modelWhere = modelWhereService.getById(id);

        return R.ok().put("modelWhere", modelWhere);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:modelwhere:save")
    public R save(@RequestBody ModelWhereEntity modelWhere){
		modelWhereService.save(modelWhere);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:modelwhere:update")
    public R update(@RequestBody ModelWhereEntity modelWhere){
		modelWhereService.updateById(modelWhere);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:modelwhere:delete")
    public R delete(@RequestBody Long[] ids){
		modelWhereService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
