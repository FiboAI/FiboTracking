package cn.fibo.cdp.config;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-16 20:13:28
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class ShiroSessionManager extends DefaultWebSessionManager {

    private static final String HEADER_TOKEN_NAME = "token";

    public ShiroSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(HEADER_TOKEN_NAME);
        if (StringUtils.isEmpty(id)) {
            //按照默认规则从cookie中获取SessionId
            return super.getSessionId(request, response);
        } else {
            // 从Header头中获取sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}
