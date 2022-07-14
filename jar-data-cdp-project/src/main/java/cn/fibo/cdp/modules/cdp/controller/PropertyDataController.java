package cn.fibo.cdp.modules.cdp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.fibo.cdp.common.constants.PermissionConstants;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.modules.cdp.entity.EventDataEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fibo.cdp.modules.cdp.entity.PropertyDataEntity;
import cn.fibo.cdp.modules.cdp.service.PropertyDataService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.R;



/**
 * 属性定义表
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@RestController
@RequestMapping("cdp/propertydata")
@Api(tags = "元数据-属性管理")
@RequiresPermissions(PermissionConstants.ANALYSIS_METADATA_PROPERTY)
public class PropertyDataController {
    @Autowired
    private PropertyDataService propertyDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation("属性列表")
//    @RequiresPermissions("cdp:propertydata:list")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils page = propertyDataService.queryPage(params);

        return R.okDatas(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/listAll")
    @ApiOperation("获取所有属性")
//    @RequiresPermissions("cdp:propertydata:list")
    public R listAll(){
        List<PropertyDataEntity> list = propertyDataService.list();
        return R.okDatas(list);
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @ApiOperation("获取属性详情")
//    @RequiresPermissions("cdp:propertydata:info")
    public R info(@PathVariable("id") Long id){
		PropertyDataEntity propertyData = propertyDataService.getById(id);

        return R.okDatas(propertyData);
    }

    /**
     * 保存
     */
    @RequestMapping("/saveOrUpdate")
    @ApiOperation("保存或修改属性")
//    @RequiresPermissions("cdp:propertydata:save")
    public R saveOrUpdate(@RequestBody PropertyDataEntity propertyData){
        //判断事件英文名是否已经存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name_en",propertyData.getNameEn());
        queryWrapper.eq("del_flag",StatusConstants.del_flag_0);
        if(propertyData.getId()!=null){
            queryWrapper.ne("id",propertyData.getId());
        }else{
            //新增时，校验属性在埋点中是否存在
            propertyDataService.checkPropertyExist(propertyData.getNameEn());
        }
        PropertyDataEntity one = propertyDataService.getOne(queryWrapper);
        if(one!=null){
            throw new CustomExceptionException(RtnCode.CDP_PROPERTY_EXIST);
        }
		propertyDataService.saveOrUpdate(propertyData);
        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ApiOperation("删除属性")
//    @RequiresPermissions("cdp:propertydata:delete")
    public R delete(@RequestBody Map<String,Object> params){
        if(!params.containsKey("id")){
            throw new CustomExceptionException(RtnCode.HTTP_PARAMS_ERROR);
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",params.get("id"));
        updateWrapper.set("del_flag", StatusConstants.del_flag_1);
        propertyDataService.update(updateWrapper);
        return R.ok();
    }

}
