

package cn.fibo.cdp.modules.sys.service.impl;

import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.utils.R;
import cn.fibo.cdp.modules.sys.oauth2.TokenGenerator;
import cn.fibo.cdp.modules.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public R createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		redisTemplate.opsForValue().set(RedisKeys.USER_LOGIN_TOKEN_KEY+token,userId+"",RedisKeys.USER_LOGIN_TOKEN_EXPIRE, TimeUnit.SECONDS);
		R r = R.ok().put("token", token).put("expire", RedisKeys.USER_LOGIN_TOKEN_EXPIRE);

		return r;
	}

	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		redisTemplate.opsForValue().set(RedisKeys.USER_LOGIN_TOKEN_KEY+token,userId,RedisKeys.USER_LOGIN_TOKEN_EXPIRE, TimeUnit.SECONDS);
	}
}
