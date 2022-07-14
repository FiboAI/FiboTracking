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

import cn.fibo.cdp.modules.backpass.entity.PassbackStrategyEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackStrategyService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 回传策略
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@RestController
@RequestMapping("backpass/passbackstrategy")
@RequiresPermissions("pass:back:strategy")
public class PassbackStrategyController {
    @Autowired
    private PassbackStrategyService passbackStrategyService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = passbackStrategyService.queryPage(params);

        return R.ok().put("page", page);
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PassbackStrategyEntity passbackStrategy = passbackStrategyService.getById(id);

        return R.ok().put("passbackStrategy", passbackStrategy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PassbackStrategyEntity passbackStrategy){
		passbackStrategyService.save(passbackStrategy);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PassbackStrategyEntity passbackStrategy){
		passbackStrategyService.updateById(passbackStrategy);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		passbackStrategyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
