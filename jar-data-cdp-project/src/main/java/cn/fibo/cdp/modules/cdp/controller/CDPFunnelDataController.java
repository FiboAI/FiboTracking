package cn.fibo.cdp.modules.cdp.controller;

import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.utils.R;
import cn.fibo.cdp.common.utils.UserUtils;
import cn.fibo.cdp.modules.cdp.entity.FunnelDataEntity;
import cn.fibo.cdp.modules.cdp.service.FunnelDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 19:32:58
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@RestController
@RequestMapping("/cdpclient/funneldata")
@Api(value = "漏斗配置",tags = "漏斗配置")
public class CDPFunnelDataController {

    @Autowired
    private FunnelDataService funnelDataService;


    @PostMapping("/saveOrUpdateFunnel")
    @ApiOperation("添加或更新漏斗")
    public R saveOrUpdateFunnel(@RequestBody FunnelDataEntity funnelDataEntity){
        funnelDataService.saveOrUpdateInfo(funnelDataEntity);
        return R.ok();
    }

    @GetMapping("/getFunnelListData")
    @ApiOperation("获取漏斗列表")
    public R getFunnelListData(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag", StatusConstants.del_flag_0);
        queryWrapper.eq("status",StatusConstants.status_0);
        queryWrapper.eq("create_user_id", UserUtils.getUserId());
        System.out.println("当前登录ID" + UserUtils.getUserId());
        List<FunnelDataEntity> list = funnelDataService.getList(queryWrapper);
        return R.ok().put("datas",list);
    }

    @GetMapping("/getFunnelDetail/{id}")
    @ApiOperation("获取漏斗详情")
    public R getFunnelDetail(@PathVariable("id") Long id){
        return R.ok().put("data",funnelDataService.getDetail(id));
    }

    @PostMapping("/removeFunnel")
    @ApiOperation("删除漏斗")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "funnelId",value = "漏斗ID",dataType = "Long",example="1",required = true)
    })
    public R removeFunnel(@RequestBody Map<String,Object> requestBody){
        if(!requestBody.containsKey("funnelId")){
            return R.error(RtnCode.HTTP_METHOR_ERROR.getCode(),RtnCode.HTTP_PARAMS_ERROR.getMsg());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("del_flag",StatusConstants.del_flag_1);
        updateWrapper.eq("id",requestBody.get("funnelId"));
        funnelDataService.update(updateWrapper);
        return R.ok();
    }

}
