

package cn.fibo.cdp.common.exception;

import cn.fibo.cdp.common.enums.RtnCode;

/**
 * 自定义异常
 *
 * @author lisw
 */
public class CustomExceptionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public CustomExceptionException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public CustomExceptionException(RtnCode rtnCode) {
		super(rtnCode.getMsg());
		this.msg = rtnCode.getMsg();
		this.code = rtnCode.getCode();
	}

	public CustomExceptionException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public CustomExceptionException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public CustomExceptionException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}
