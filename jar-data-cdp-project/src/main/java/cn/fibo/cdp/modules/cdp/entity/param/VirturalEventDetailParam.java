package cn.fibo.cdp.modules.cdp.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-18 17:56:55
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
public class VirturalEventDetailParam {

    //虚拟事件对应的真实事件英文名
    private String eventEn;

    //该事件在虚拟事件中需要什么筛选条件
    List<SubmitWherePropertyParam> submitWherePropertyParams;
}
