

package cn.fibo.cdp.modules.sys.oauth2;


import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * token
 *
 * @author lisw
 */
public class OAuth2Token extends UsernamePasswordToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
