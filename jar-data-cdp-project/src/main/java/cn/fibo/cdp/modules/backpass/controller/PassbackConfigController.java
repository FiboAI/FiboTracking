package cn.fibo.cdp.modules.backpass.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.modules.backpass.service.PassbackStrategyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.fibo.cdp.modules.backpass.entity.PassbackConfigEntity;
import cn.fibo.cdp.modules.backpass.service.PassbackConfigService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 回传配置
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-07-04 18:37:28
 */
@RestController
@RequestMapping("backpass/passbackconfig")
@RequiresPermissions("pass:back:platform")
public class PassbackConfigController {
    @Autowired
    private PassbackConfigService passbackConfigService;

    @Autowired
    private PassbackStrategyService passbackStrategyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = passbackConfigService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/getStrategyList")
    public R listAll(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag",StatusConstants.del_flag_0);
        queryWrapper.eq("status",StatusConstants.status_0);
        List list = passbackStrategyService.list(queryWrapper);
        return R.okDatas(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PassbackConfigEntity passbackConfig = passbackConfigService.getById(id);
        return R.ok().put("passbackConfig", passbackConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/saveOrUpdate")
    public R save(@RequestBody PassbackConfigEntity passbackConfig){
		passbackConfigService.saveOrUpdate(passbackConfig);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PassbackConfigEntity passbackConfig){
		passbackConfigService.updateById(passbackConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		passbackConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 开启或关闭
     */
    @PostMapping("/openOrClose")
    public R openOrClose(@RequestBody Map<String,Object> params){
        if(!params.containsKey("id") || !params.containsKey("status")){
            throw new CustomExceptionException(RtnCode.HTTP_PARAMS_ERROR);
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("status", params.get("status"));
        updateWrapper.eq("id",params.get("id"));
        passbackConfigService.update(updateWrapper);
        return R.ok();
    }

}
