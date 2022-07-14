

package cn.fibo.cdp.modules.sys.service.impl;


import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.modules.sys.service.SysCaptchaService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 *
 * @author lisw
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl implements SysCaptchaService {
    @Autowired
    private Producer producer;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new CustomExceptionException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();
        redisTemplate.opsForValue().set(RedisKeys.USER_LOGIN_CAPTCHA_KEY+uuid,code,5*60, TimeUnit.SECONDS);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        Object value = redisTemplate.opsForValue().get(RedisKeys.USER_LOGIN_CAPTCHA_KEY + uuid);
        if(value == null){
            return false;
        }
        if(value.toString().equalsIgnoreCase(code)){
            return true;
        }
        return false;
    }
}
