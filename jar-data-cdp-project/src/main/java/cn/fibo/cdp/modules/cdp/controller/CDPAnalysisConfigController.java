package cn.fibo.cdp.modules.cdp.controller;

import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.enums.ModelTypeEnums;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.common.utils.*;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.OverviewEntity;
import cn.fibo.cdp.modules.cdp.entity.dto.CalcKeepDataRtnDto;
import cn.fibo.cdp.modules.cdp.entity.dto.EventPropertyDto;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitKeepEventParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitSelectPropertyParam;
import cn.fibo.cdp.modules.cdp.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 13:16:08
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@RestController
@RequestMapping("/cdpclient/analysisconfig")
@Api(tags = "分析模型-接口统一，不同的分析类型入参与出参会不一样")
public class CDPAnalysisConfigController {
    @Autowired
    private OverviewService overviewService;

    @Resource(name = "modelConfigDefaultService")
    private ModelConfigDataService modelConfigDataService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PropertyDataService propertyDataService;

    @Autowired
    private Map<String,ModelConfigDataService> modelConfigDataServiceMap = new ConcurrentHashMap<>();

    @Autowired
    private CDPAnalysisUtils cdpAnalysisUtils;

    @PostMapping("/eventAnalysisSaveOrUpdate")
    @ApiOperation("保存/更新模型")
    public R submitEvent(@RequestBody SubmitModelAnalysisParam submitEventAnalysisParam){
        if(submitEventAnalysisParam.getSort()==null && submitEventAnalysisParam.getId()==null){
            submitEventAnalysisParam.setSort(9999);
        }
        if(submitEventAnalysisParam.getOverviewId()==null){
            throw new CustomExceptionException(RtnCode.CDP_OVERVIEW_NOTFOUND.getMsg(),RtnCode.CDP_OVERVIEW_NOTFOUND.getCode());
        }
        //查看概览是否是公共概览，如果是公共概览且此用户非admin用户，则不允许修改
        OverviewEntity byId = overviewService.getById(submitEventAnalysisParam.getOverviewId());
        if(byId==null){
            throw new CustomExceptionException(RtnCode.CDP_OVERVIEW_NOTFOUND.getMsg(),RtnCode.CDP_OVERVIEW_NOTFOUND.getCode());
        }
        if(!UserUtils.isAdmin() && byId.getOverviewType()==0){
            throw new CustomExceptionException(RtnCode.CDP_NOTAUTH.getMsg(),RtnCode.CDP_NOTAUTH.getCode());
        }
        ModelTypeEnums modelTypeEnums = ModelTypeEnums.getByCode(submitEventAnalysisParam.getType());
        modelConfigDataServiceMap.get(modelTypeEnums.getService()).submitModelData(submitEventAnalysisParam);
        return R.ok();
    }

    @GetMapping(value = "/getDetail",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("获取分析模型详情")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "datas", response = SubmitModelAnalysisParam.class, responseContainer = "Map"),
    })
    public R submitEvent(Long modelId){
        SubmitModelAnalysisParam modelDetail = modelConfigDataService.getModelDetail(modelId);
        return R.ok().put("datas",modelDetail);
    }

    @PostMapping("/removeOverview")
    @ApiOperation(value = "删除分析模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId",value = "分析模型ID",dataType = "Long",example="1",required = true)
    })
    public R removeOverview(@RequestBody Map<String,Object> requestBody){
        if(!requestBody.containsKey("modelId")){
            return R.error(RtnCode.HTTP_METHOR_ERROR.getCode(),RtnCode.HTTP_PARAMS_ERROR.getMsg());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("del_flag", StatusConstants.del_flag_1);
        updateWrapper.eq("id",requestBody.get("modelId"));
        modelConfigDataService.update(updateWrapper);
        return R.ok();
    }

    @PostMapping(value = "/getCalculateData",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "留存分析数据说明", response = CalcKeepDataRtnDto.class, responseContainer = "List"),
    })
    @ApiOperation("根据规则数据计算数据")
    public R getCalculateData(@RequestBody SubmitModelAnalysisParam submitModelAnalysisParam){
        ModelTypeEnums modelTypeEnums = ModelTypeEnums.getByCode(submitModelAnalysisParam.getType());
        if(!CollectionUtils.isEmpty(submitModelAnalysisParam.getGroupPropertyParams())){
            List<EventPropertyDto> allPropertyList = propertyDataService.getAllPropertyList(1);
            submitModelAnalysisParam.getGroupPropertyParams().forEach(item->{
                EventPropertyDto eventPropertyDto  = allPropertyList.stream().filter(e->StringUtils.equals(e.getNameEn(),item.getPropertyEn())).findFirst().get();
                if(eventPropertyDto!=null){
                    item.setPropertyCn(eventPropertyDto.getNameCn());
                }
            });
        }
        cdpAnalysisUtils.dataFull(submitModelAnalysisParam,modelTypeEnums);
        //根据规则编辑页面，无需进行计算同比
        submitModelAnalysisParam.setTempIsCalcYearToYearCondions(false);
        //读取缓存
        Map<String, Object> calculateData = modelConfigDataServiceMap.get(modelTypeEnums.getService()).getCalculateData(submitModelAnalysisParam);
        return R.ok().putDataAll(calculateData);
    }



    //缓存使用
    private Map<String,Object> getRedisCache(SubmitModelAnalysisParam submitModelAnalysisParam,ModelTypeEnums modelTypeEnums){
        String redisKey="";
        //缓存
//        String inputParams = JSON.toJSONString(submitModelAnalysisParam);
//        String inputMd5 = DigestUtils.md5Hex(inputParams);
        String times = submitModelAnalysisParam.getStartTime()+"-"+submitModelAnalysisParam.getEndTime();
        times = times.replaceAll(":","").trim();
        redisKey = RedisKeys.CDP_DATA_ANALYSIS_CALC.replace("{type}",submitModelAnalysisParam.getType().toString())
                .replace("{id}",submitModelAnalysisParam.getId().toString());
        Object cacheObject = redisUtils.hget(redisKey,times);
        Map<String, Object> calculateData = new HashMap<>();
        if(cacheObject!=null){
            calculateData = (Map<String, Object>) cacheObject;
        }else{
            calculateData = modelConfigDataServiceMap.get(modelTypeEnums.getService()).getCalculateData(submitModelAnalysisParam);
            redisUtils.hput(redisKey,times,calculateData);
        }
        return calculateData;
    }



    @GetMapping("/getCalculateDataByModelId")
    @ApiOperation("根据分析模型ID与时间范围获取模型详情及计算后的数据")
    public R getCalculateDataByModelId(@ApiParam(name = "分析模型ID") Long modelId,
                                       @ApiParam(name="开始事件") @RequestParam String startTime, @ApiParam(name="结束时间") @RequestParam String endTime
    ){

        String[] startTimeArray = startTime.split(" ");
        if(startTimeArray.length==1){
            startTime += " 00:00:00.000";
        }
        String[] endTimeArray = endTime.split(" ");
        if(endTimeArray.length==1){
            endTime += " 23:59:59.999";
        }

        SubmitModelAnalysisParam modelDetail = modelConfigDataService.getModelDetail(modelId);
        ModelTypeEnums modelTypeEnums = ModelTypeEnums.getByCode(modelDetail.getType());
        modelDetail.setStartTime(startTime);
        modelDetail.setEndTime(endTime);
        //service中涉及数据源切换，且需要查询规则数据库中的数据，提前在这里填充好
        cdpAnalysisUtils.dataFull(modelDetail,modelTypeEnums);
        //读取缓存
        Map<String, Object> calculateData = getRedisCache(modelDetail,modelTypeEnums);
        return R.ok().putDataAll(calculateData).put("modelData",modelDetail);
    }


    @PostMapping(value = "/exportData",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("根据规则数据获取导出数据的key")
    public R exportData(@RequestBody SubmitModelAnalysisParam submitModelAnalysisParam){
        String exportid = StringUtils.getUUID();
        String redisKeys = MessageFormat.format(RedisKeys.CDP_DATA_EXPORT, exportid);
        redisUtils.set(redisKeys,submitModelAnalysisParam);
        redisUtils.expire(redisKeys,5*60, TimeUnit.SECONDS);
        return R.okDatas(exportid);
    }



}
