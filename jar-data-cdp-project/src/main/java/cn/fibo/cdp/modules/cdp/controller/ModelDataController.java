//package cn.fibo.cdp.modules.cdp.controller;
//
//import java.util.Arrays;
//import java.util.Map;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
//import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
//import cn.fibo.cdp.common.utils.PageUtils;
//import cn.fibo.cdp.common.utils.R;
//
//
//
///**
// *
// *
// * @author lisw
// * @email starsoy@163.com
// * @date 2022-05-13 15:19:42
// */
//@RestController
//@RequestMapping("cdp/modeldata")
//public class ModelDataController {
//    @Autowired
//    private ModelConfigDataService modelConfigDataService;
//
//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    @RequiresPermissions("cdp:modeldata:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = modelConfigDataService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    @RequiresPermissions("cdp:modeldata:info")
//    public R info(@PathVariable("id") Long id){
//		ModelDataEntity modelData = modelConfigDataService.getById(id);
//
//        return R.ok().put("modelData", modelData);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("cdp:modeldata:save")
//    public R save(@RequestBody ModelDataEntity modelData){
//		modelConfigDataService.save(modelData);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("cdp:modeldata:update")
//    public R update(@RequestBody ModelDataEntity modelData){
//		modelConfigDataService.updateById(modelData);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("cdp:modeldata:delete")
//    public R delete(@RequestBody Long[] ids){
//		modelConfigDataService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }
//
//}
