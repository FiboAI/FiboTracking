package cn.fibo.cdp.modules.cdp.controller;

import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.enums.ModelTypeEnums;
import cn.fibo.cdp.common.utils.RedisUtils;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-06-27 09:55:29
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Controller
@RequestMapping("/exportData")
@Api(tags = "数据导出")
public class CDPAnalysisExportDataController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CDPAnalysisUtils cdpAnalysisUtils;
    @Resource(name = "modelConfigDefaultService")
    private ModelConfigDataService modelConfigDataService;

    @Autowired
    private Map<String, ModelConfigDataService> modelConfigDataServiceMap = new ConcurrentHashMap<>();

    @GetMapping("/exportData")
    @ApiOperation("根据规则导出数据")
    public void export(@ApiParam("导出id") @RequestParam("exportid") String exportid,
                       HttpServletResponse httpServletResponse,
                       @ApiParam("文件名称") @RequestParam(value = "exportFileName",required = false) String fileName){
        String redisKey = MessageFormat.format(RedisKeys.CDP_DATA_EXPORT, exportid);
        String para = redisUtils.get(redisKey);
        SubmitModelAnalysisParam submitModelAnalysisParam = JSONObject.parseObject(para, SubmitModelAnalysisParam.class);
        ModelTypeEnums modelTypeEnums = ModelTypeEnums.getByCode(submitModelAnalysisParam.getType());
        cdpAnalysisUtils.dataFull(submitModelAnalysisParam,modelTypeEnums);
        //读取缓存
        if(StringUtils.isBlank(fileName)){
            fileName="数据导出";
        }
        fileName+="-"+System.currentTimeMillis();
        modelConfigDataServiceMap.get(modelTypeEnums.getService()).exportExcel(submitModelAnalysisParam, httpServletResponse,fileName);
    }
    @GetMapping("/exportDataById")
    @ApiOperation("根据模型ID导出数据")
    public void export(@ApiParam("modelId") @RequestParam("modelId") Long modelId,
                       HttpServletResponse httpServletResponse){
        SubmitModelAnalysisParam submitModelAnalysisParam = modelConfigDataService.getModelDetail(modelId);
        ModelTypeEnums modelTypeEnums = ModelTypeEnums.getByCode(submitModelAnalysisParam.getType());
        cdpAnalysisUtils.dataFull(submitModelAnalysisParam,modelTypeEnums);
        //读取缓存
        String fileName = submitModelAnalysisParam.getName();
        if(StringUtils.isBlank(submitModelAnalysisParam.getName())){
            fileName="数据导出";
        }
        fileName+="-"+System.currentTimeMillis();
        modelConfigDataServiceMap.get(modelTypeEnums.getService()).exportExcel(submitModelAnalysisParam, httpServletResponse,fileName);
    }
}
