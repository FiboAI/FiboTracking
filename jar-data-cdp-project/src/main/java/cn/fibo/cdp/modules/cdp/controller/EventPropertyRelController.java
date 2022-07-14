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

import cn.fibo.cdp.modules.cdp.entity.EventPropertyRelEntity;
import cn.fibo.cdp.modules.cdp.service.EventPropertyRelService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 事件属性关联关系表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@RestController
@RequestMapping("cdp/eventpropertyrel")
public class EventPropertyRelController {
    @Autowired
    private EventPropertyRelService eventPropertyRelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:eventpropertyrel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = eventPropertyRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:eventpropertyrel:info")
    public R info(@PathVariable("id") Long id){
		EventPropertyRelEntity eventPropertyRel = eventPropertyRelService.getById(id);

        return R.ok().put("eventPropertyRel", eventPropertyRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:eventpropertyrel:save")
    public R save(@RequestBody EventPropertyRelEntity eventPropertyRel){
		eventPropertyRelService.save(eventPropertyRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:eventpropertyrel:update")
    public R update(@RequestBody EventPropertyRelEntity eventPropertyRel){
		eventPropertyRelService.updateById(eventPropertyRel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:eventpropertyrel:delete")
    public R delete(@RequestBody Long[] ids){
		eventPropertyRelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
