package cn.fibo.cdp.modules.cdp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.fibo.cdp.common.constants.StatusConstants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fibo.cdp.modules.cdp.entity.EventGroupEntity;
import cn.fibo.cdp.modules.cdp.service.EventGroupService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 事件分组
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@RestController
@RequestMapping("cdp/eventgroup")
@Api(tags = "元数据-事件分组")
public class EventGroupController {
    @Autowired
    private EventGroupService eventGroupService;


    @RequestMapping("/listAll")
    @ApiOperation("获取所有分组信息")
    public R listAll(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag", StatusConstants.del_flag_0);
        queryWrapper.eq("status",StatusConstants.status_0);
        List list = eventGroupService.list(queryWrapper);
        return R.okDatas(list);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("cdp:eventgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = eventGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("cdp:eventgroup:info")
    public R info(@PathVariable("id") Long id){
		EventGroupEntity eventGroup = eventGroupService.getById(id);

        return R.ok().put("eventGroup", eventGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("cdp:eventgroup:save")
    public R save(@RequestBody EventGroupEntity eventGroup){
		eventGroupService.save(eventGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cdp:eventgroup:update")
    public R update(@RequestBody EventGroupEntity eventGroup){
		eventGroupService.updateById(eventGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cdp:eventgroup:delete")
    public R delete(@RequestBody Long[] ids){
		eventGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
