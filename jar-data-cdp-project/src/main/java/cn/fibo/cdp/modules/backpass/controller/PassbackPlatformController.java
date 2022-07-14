package cn.fibo.cdp.modules.backpass.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.fibo.cdp.common.constants.StatusConstants;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fibo.cdp.modules.backpass.entity.PassbackPlatformEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackPlatformService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 平台信息
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@RestController
@RequestMapping("backpass/passbackplatform")
@RequiresPermissions("pass:back:platform")
public class PassbackPlatformController {
    @Autowired
    private PassbackPlatformService passbackPlatformService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.del_flag", StatusConstants.del_flag_0);
        queryWrapper.groupBy("a.id");
        List<PassbackPlatformEntity> passbackPlatformEntityList = passbackPlatformService.getListAll(queryWrapper);
        return R.okDatas(passbackPlatformEntityList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PassbackPlatformEntity passbackPlatform = passbackPlatformService.getById(id);

        return R.ok().put("passbackPlatform", passbackPlatform);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PassbackPlatformEntity passbackPlatform){
		passbackPlatformService.save(passbackPlatform);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PassbackPlatformEntity passbackPlatform){
		passbackPlatformService.updateById(passbackPlatform);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		passbackPlatformService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
