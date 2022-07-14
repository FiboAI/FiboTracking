package cn.fibo.cdp.common.enums;

public enum RtnCode {

    SUCCESS(0,"操作成功"),
    DUPLICATEKEY_ERROE(301,"数据库中已存在该记录"),
    SERVER_ERROR(500,"未知异常，请联系管理员"),
    TOKEN_INVALID_ERROR(401,"token失效，请重新登录"),
    NOT_AUTH_ERROR(402,"没有权限，请联系管理员授权"),
    LOCK_ACCOUNT_ERROR(403,"账号已被锁定,请联系管理员"),
    NOFOUND_ERROR(404,"路径不存在，请检查路径是否正确"),
    HTTP_METHOR_ERROR(405,"接口HTTP协议错误"),
    HTTP_PARAMS_ERROR(406,"参数错误"),
    CDP_OVERVIEW_NOTFOUND(8001,"概览不存在"),
    CDP_NOTAUTH(8002,"无权操作"),
    CDP_CUSTOMSQL_ERROR(8003,"SQL有误，请检查SQL语句是否正确"),
    CDP_EVENT_EXIST(8004,"事件英文名已存在"),
    CDP_PROPERTY_EXIST(8005,"属性英文名已存在"),
    MA_PROPERTY_NO_EXIST(8006,"属性在埋点中未存在，不可新增"),
    CDP_SYS_USERNAME_EXIST(1001,"用户名已存在");
    private Integer code;

    private String msg;

    RtnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
