package cn.fibo.cdp.modules.cdp.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-15 13:53:41
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@ApiModel("按....查看")
@Data
public class SubmitGroupPropertyParam {

    @ApiModelProperty("属性英文名")
    private String propertyEn;

    @ApiModelProperty("属性中文名")
    private String propertyCn;

    @ApiModelProperty("排序值")
    private Integer sort;
}
