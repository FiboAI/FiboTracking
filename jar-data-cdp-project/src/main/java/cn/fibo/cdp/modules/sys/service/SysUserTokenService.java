

package cn.fibo.cdp.modules.sys.service;

import cn.fibo.cdp.common.utils.R;

/**
 * 用户Token
 *
 * @author lisw
 */
public interface SysUserTokenService{

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
