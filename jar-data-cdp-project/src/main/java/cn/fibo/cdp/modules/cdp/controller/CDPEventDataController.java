package cn.fibo.cdp.modules.cdp.controller;

import cn.fibo.cdp.common.utils.R;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.modules.cdp.entity.dto.EventGroupDto;
import cn.fibo.cdp.modules.cdp.entity.dto.EventPropertyDto;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.service.EventDataService;
import cn.fibo.cdp.modules.cdp.service.PropertyDataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-14 21:27:57
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@RestController
@RequestMapping("/cdpclient/eventdata")
@Api(value = "事件属性相关",tags = "事件属性相关")
public class CDPEventDataController {

    @Autowired
    private EventDataService eventDataService;

    @Autowired
    private PropertyDataService propertyDataService;

    /**
     * 获取所有事件分组及分组中的事件
     * @return
     */
    @GetMapping(value = "/getGroupEventList",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取事件分组及分组下的事件")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "datas属性说明", response = EventGroupDto.class,responseContainer = "List")
    })
    public R getList(){
        List<EventGroupDto> cacheList = eventDataService.getCacheList();
        return R.ok().put("datas",cacheList);
    }

    /**
     * 获取属性，根据事件ID
     */
    @GetMapping(value = "/getPropertyList",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "datas属性说明", response = EventPropertyDto.class, responseContainer = "List"),
    })
    @ApiOperation(value = "根据事件英文名获取属性")
    public R getPropertyList(@ApiParam("事件英文名，可为空，为空则获取所有属性")
                             @RequestParam(value = "eventNameEn",required = false) String eventNameEn,
                             @ApiParam("是否只查可按....查看的属性，传1代表是")@RequestParam(value = "isGroupView",required = false) Integer isGroupView
                            ){
        List<EventPropertyDto> eventPropertyList = new ArrayList<>();
        if(StringUtils.isNotBlank(eventNameEn)){
            eventPropertyList = propertyDataService.getEventPropertyList(eventNameEn);
        }else{
            if(isGroupView==null){
                isGroupView = -1;
            }
             eventPropertyList = propertyDataService.getAllPropertyList(isGroupView);
        }
        return R.ok().put("datas",eventPropertyList);
    }
}
