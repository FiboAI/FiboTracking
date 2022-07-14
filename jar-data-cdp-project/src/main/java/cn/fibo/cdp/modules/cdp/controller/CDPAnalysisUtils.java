package cn.fibo.cdp.modules.cdp.controller;

import cn.fibo.cdp.common.enums.ModelTypeEnums;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.modules.cdp.entity.dto.EventPropertyDto;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitKeepEventParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitSelectPropertyParam;
import cn.fibo.cdp.modules.cdp.service.EventVirtualDataService;
import cn.fibo.cdp.modules.cdp.service.FunnelDataService;
import cn.fibo.cdp.modules.cdp.service.PropertyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-06-24 17:49:57
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Component
public class CDPAnalysisUtils {

    @Autowired
    private EventVirtualDataService eventVirtualDataService;

    @Autowired
    private PropertyDataService propertyDataService;

    @Autowired
    private FunnelDataService funnelDataService;

    //数据填充
    public  void dataFull(SubmitModelAnalysisParam modelDetail, ModelTypeEnums modelTypeEnums){
        switch (modelTypeEnums){
            case EVENT_MODEL:
                //虚拟事件数据填充
                if(!CollectionUtils.isEmpty(modelDetail.getSelectPropertyParams())){
                    List<SubmitSelectPropertyParam> selectPropertyParams = modelDetail.getSelectPropertyParams();
                    selectPropertyParams.forEach(item ->{
                        if(item.getIsVirtual()==1){
                            item.setVirturalEventDetailParams(eventVirtualDataService.getVirturalEventDetailParams(item.getEventEn()));
                        }
                        List<EventPropertyDto> eventPropertyList = propertyDataService.getEventPropertyList(item.getEventEn());
                        eventPropertyList.forEach(property->{
                            if(StringUtils.equals(property.getNameEn(),item.getPropertyEn())){
                                item.setPropertyCn(property.getNameCn());
                            }
                        });
                    });
                }
                //是否计算同环比
                boolean selectPropertySizeEqOne = !CollectionUtils.isEmpty(modelDetail.getSelectPropertyParams()) && modelDetail.getSelectPropertyParams().size()==1;
                boolean groupPropertyIsEmpty = CollectionUtils.isEmpty(modelDetail.getGroupPropertyParams());
                boolean isYearToyear = modelDetail.getIsYearToYear()!=null && modelDetail.getIsYearToYear()==1;
                boolean isCalcYearToyear = selectPropertySizeEqOne && groupPropertyIsEmpty && isYearToyear;
                modelDetail.setTempIsCalcYearToYearCondions(isCalcYearToyear);
                break;
            case FUNNEL_MODEL:
                //漏斗详情数据填充
                if(modelDetail.getType() == ModelTypeEnums.FUNNEL_MODEL.getCode() && modelDetail.getFunnelId()!=null){
                    modelDetail.setFunnelDataEntity(funnelDataService.getDetail(modelDetail.getFunnelId()));
                }
                break;
            case KEEP_MODEL:
                //虚拟事件数据填充
                SubmitKeepEventParam keepEventParam = modelDetail.getKeepEventParam();
                if(keepEventParam.getFirstIsVirtual() == 1){
                    keepEventParam.setFirstVirturalEventDetailParams(eventVirtualDataService.getVirturalEventDetailParams(keepEventParam.getFirstEventEn()));
                }
                if(keepEventParam.getLastIsVirtual() == 1){
                    keepEventParam.setLastVirturalEventDetailParams(eventVirtualDataService.getVirturalEventDetailParams(keepEventParam.getLastEventEn()));
                }
                break;
        }

        //时间填充
        String startTime = modelDetail.getStartTime();
        String endTime = modelDetail.getEndTime();
        String[] startTimeArray = startTime.split(" ");
        if(startTimeArray.length==1){
            startTime += " 00:00:00.000";
        }
        String[] endTimeArray = endTime.split(" ");
        if(endTimeArray.length==1){
            endTime += " 23:59:59.999";
        }
        modelDetail.setStartTime(startTime);
        modelDetail.setEndTime(endTime);
    }

}
