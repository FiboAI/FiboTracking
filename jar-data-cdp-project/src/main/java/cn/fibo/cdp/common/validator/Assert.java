

package cn.fibo.cdp.common.validator;

import cn.fibo.cdp.common.exception.CustomExceptionException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author lisw
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CustomExceptionException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CustomExceptionException(message);
        }
    }
}
