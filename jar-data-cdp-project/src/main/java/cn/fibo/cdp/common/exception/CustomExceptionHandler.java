

package cn.fibo.cdp.common.exception;

import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author lisw
 */
@RestControllerAdvice
public class CustomExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(CustomExceptionException.class)
	public R handleRRException(CustomExceptionException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return R.error(RtnCode.NOFOUND_ERROR.getCode(), RtnCode.NOFOUND_ERROR.getMsg());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error(RtnCode.DUPLICATEKEY_ERROE.getCode(), RtnCode.DUPLICATEKEY_ERROE.getMsg());
	}

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error(RtnCode.NOT_AUTH_ERROR.getCode(),RtnCode.NOT_AUTH_ERROR.getMsg());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		return R.error(RtnCode.HTTP_METHOR_ERROR.getCode(),RtnCode.HTTP_METHOR_ERROR.getMsg()+e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
		return R.error(RtnCode.HTTP_PARAMS_ERROR.getCode(),RtnCode.HTTP_PARAMS_ERROR.getMsg()+e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
}
