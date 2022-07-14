package cn.fibo.cdp.modules.cdp.controller;

import java.util.Arrays;
import java.util.Map;

import cn.fibo.cdp.common.constants.PermissionConstants;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.common.utils.RtnResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;
import cn.fibo.cdp.modules.cdp.service.EventDataService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 事件定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Api(tags = "元数据-事件管理")
@RestController
@RequestMapping("/cdp/eventdata")
@RequiresPermissions(PermissionConstants.ANALYSIS_METADATA_EVENT)
public class EventDataController {
    @Autowired
    private EventDataService eventDataService;
    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "事件列表(真实事件)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码"),
            @ApiImplicitParam(name = "limit",value = "获取长度")
    })
    public R list(@RequestBody @ApiParam(hidden = true) Map<String, Object> params){
        PageUtils<EventDataEntity> page = eventDataService.queryPage(params);
       return R.okDatas(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("cdp:eventdata:info")
    @ApiOperation("事件详情")
    public R info(@PathVariable("id") Long id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("a.id",id);
		EventDataEntity eventData = eventDataService.getDetailById(queryWrapper);
        return R.okDatas(eventData);
    }

    /**
     * 保存
     */
    @PostMapping("/saveOrUpdate")
    @ApiOperation("保存或修改元事件信息")
//    @RequiresPermissions("cdp:eventdata:save")
    public R saveOrUpdate(@RequestBody EventDataEntity eventData){
        //非虚拟事件
        eventData.setIsVirtual(0);
        //判断事件英文名是否已经存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name_en",eventData.getNameEn());
        queryWrapper.eq("del_flag",StatusConstants.del_flag_0);
        if(eventData.getId()!=null){
            queryWrapper.ne("id",eventData.getId());
        }
        EventDataEntity one = eventDataService.getOne(queryWrapper);
        if(one!=null){
            throw new CustomExceptionException(RtnCode.CDP_EVENT_EXIST);
        }
        if(eventData.getStatus() == null){
            eventData.setStatus(StatusConstants.status_0);
        }
        eventData.setDelFlag(StatusConstants.del_flag_0);
        eventDataService.saveOrUpdate(eventData);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除事件")
    @PostMapping("/delete")
//    @RequiresPermissions("cdp:eventdata:delete")
    public R delete(@RequestBody Map<String,Object> params){
        if(!params.containsKey("id")){
            throw new CustomExceptionException(RtnCode.HTTP_PARAMS_ERROR);
        }
		UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",params.get("id"));
        updateWrapper.set("del_flag", StatusConstants.del_flag_1);
        eventDataService.update(updateWrapper);
        return R.ok();
    }

}
