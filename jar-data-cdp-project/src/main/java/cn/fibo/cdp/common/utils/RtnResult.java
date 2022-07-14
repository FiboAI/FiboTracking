package cn.fibo.cdp.common.utils;

import cn.fibo.cdp.common.enums.RtnCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-06-21 15:51:03
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@ApiModel("通用返回")
@Data
public class RtnResult<T> {

    @ApiModelProperty(name = "code",value = "响应code")
    private Integer code;
    @ApiModelProperty(name = "msg",value = "响应msg")
    private String msg;
    @ApiModelProperty(name = "datas",value = "响应数据")
    private T datas;

    public static RtnResult ok(Object data){
        RtnResult  result = new RtnResult();
        result.setCode(RtnCode.SUCCESS.getCode());
        result.setMsg(RtnCode.SUCCESS.getMsg());
        result.setDatas(data);
        return result;
    }

    public static RtnResult ok(){
        RtnResult  result = new RtnResult();
        result.setCode(RtnCode.SUCCESS.getCode());
        result.setMsg(RtnCode.SUCCESS.getMsg());
        return result;
    }

    public static RtnResult error(){
        RtnResult  result = new RtnResult();
        result.setCode(RtnCode.SERVER_ERROR.getCode());
        result.setMsg(RtnCode.SERVER_ERROR.getMsg());
        return result;
    }

    public static RtnResult error(RtnCode rtnCode){
        RtnResult  result = new RtnResult();
        result.setCode(rtnCode.getCode());
        result.setMsg(rtnCode.getMsg());
        return result;
    }
}
