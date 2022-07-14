package cn.fibo.cdp.modules.backpass.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fibo.cdp.modules.backpass.entity.PassbackConfigPropertyEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackConfigPropertyService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 回传配置附加属性
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@RestController
@RequestMapping("backpass/passbackconfigproperty")
public class PassbackConfigPropertyController {
    @Autowired
    private PassbackConfigPropertyService passbackConfigPropertyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = passbackConfigPropertyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PassbackConfigPropertyEntity passbackConfigProperty = passbackConfigPropertyService.getById(id);

        return R.ok().put("passbackConfigProperty", passbackConfigProperty);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PassbackConfigPropertyEntity passbackConfigProperty){
		passbackConfigPropertyService.save(passbackConfigProperty);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PassbackConfigPropertyEntity passbackConfigProperty){
		passbackConfigPropertyService.updateById(passbackConfigProperty);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		passbackConfigPropertyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
