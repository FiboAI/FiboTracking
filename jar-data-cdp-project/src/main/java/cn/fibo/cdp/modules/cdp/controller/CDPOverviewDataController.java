package cn.fibo.cdp.modules.cdp.controller;

import cn.fibo.cdp.common.constants.PermissionConstants;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.utils.R;
import cn.fibo.cdp.common.utils.UserUtils;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.OverviewEntity;
import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
import cn.fibo.cdp.modules.cdp.service.OverviewService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 21:58:54
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@RestController
@RequestMapping("/cdpclient/overviewdata")
@Api(value = "概览相关",tags = "概览相关")
public class CDPOverviewDataController {

    @Autowired
    private OverviewService overviewService;

    @Autowired
    @Resource(name = "modelConfigDefaultService")
    private ModelConfigDataService modelConfigDataService;

    @GetMapping("/getList")
    @ApiOperation(value = "获取概览列表")
    @RequiresPermissions(value = PermissionConstants.ANALYSIS_COMMON_OVERVIEW)
    public R getList(@ApiParam("是否排除公共概览,0:否 1:是,默认是") @RequestParam(value = "ignoreCommon",required = false) Integer ignoreCommon){
        QueryWrapper<OverviewEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusConstants.status_0);
        queryWrapper.eq("del_flag",StatusConstants.del_flag_0);
        if(ignoreCommon!=null && ignoreCommon==0){
            queryWrapper.and(wrapper-> wrapper.eq("create_user_id",UserUtils.getUserId()).or().eq("overview_type",0));
        }else{
            queryWrapper.and(wrapper-> wrapper.eq("create_user_id",UserUtils.getUserId()));
        }
        List<OverviewEntity> overviewEntityList = overviewService.list(queryWrapper);
       return R.ok().put("datas",overviewEntityList);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation("新增或更新概览")
    @RequiresPermissions(value = PermissionConstants.ANALYSIS_OVERVIEW)
    public R saveOrUpdate(@RequestBody OverviewEntity overviewEntity){
        overviewEntity.setOverviewType(1);
        overviewService.saveOrUpdate(overviewEntity);
        return R.ok();
    }

    @PostMapping("/removeOverview")
    @RequiresPermissions(value = PermissionConstants.ANALYSIS_OVERVIEW)
    @ApiOperation(value = "删除概览")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "overviewId",value = "概览ID",dataType = "Long",example="1",required = true)
    })
    public R removeOverview(@RequestBody Map<String,Object> requestBody){
        if(!requestBody.containsKey("overviewId")){
            return R.error(RtnCode.HTTP_METHOR_ERROR.getCode(),RtnCode.HTTP_PARAMS_ERROR.getMsg());
        }
        Long overviewId = Long.valueOf(requestBody.get("overviewId").toString());
        OverviewEntity byId = overviewService.getById(overviewId);
        if(byId==null){
            return R.error(RtnCode.CDP_OVERVIEW_NOTFOUND.getCode(),RtnCode.CDP_OVERVIEW_NOTFOUND.getMsg());
        }
        if(byId.getOverviewType()==0){
            return R.error(RtnCode.CDP_NOTAUTH.getCode(),RtnCode.CDP_NOTAUTH.getMsg());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("del_flag",StatusConstants.del_flag_1);
        updateWrapper.eq("id",overviewId);
        overviewService.update(updateWrapper);
        return R.ok();
    }

    @GetMapping("/getModelListByOverviewId")
    @ApiOperation(value = "根据概览ID获取分析模型",response = ModelDataEntity.class)
    @ApiResponse(code = 0, message = "概览下的分析模型列表", response = ModelDataEntity.class)
    @RequiresPermissions(value = PermissionConstants.ANALYSIS_COMMON_OVERVIEW)
    public R getList(@ApiParam("概览ID") @RequestParam Long overviewId){
        OverviewEntity byId = overviewService.getById(overviewId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("overview_id",overviewId);
        queryWrapper.eq("del_flag",StatusConstants.del_flag_0);
        queryWrapper.eq("status",StatusConstants.status_0);
        queryWrapper.orderByAsc("sort","create_time");
        List<ModelDataEntity> list = modelConfigDataService.list(queryWrapper);
        return R.ok().put("datas",list).put("name",byId.getName());
    }
}
