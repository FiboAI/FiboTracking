

package cn.fibo.cdp.config;

import cn.fibo.cdp.common.constants.StatusConstants;
import cn.fibo.cdp.common.utils.UserUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * mybatis-plus配置
 *
 * @author lisw
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject.hasGetter("delFlag")
                && metaObject.getValue("delFlag")==null
        ){
            setFieldValByName("delFlag", StatusConstants.del_flag_0,metaObject);
        }
        if(metaObject.hasGetter("status")
                && metaObject.getValue("status")==null
        ){
            setFieldValByName("status", StatusConstants.status_0,metaObject);
        }
        if(metaObject.hasGetter("createUserId")
                && metaObject.getValue("createUserId")== null
        ){
            setFieldValByName("createUserId", UserUtils.getUserId(),metaObject);
        }
        if(metaObject.hasGetter("createTime")
                && metaObject.getValue("createTime")==null
        ){
            setFieldValByName("createTime",new Date(),metaObject);
        }
        if(metaObject.hasGetter("updateUserId")
                && metaObject.getValue("updateUserId")==null
        ){
            setFieldValByName("updateUserId",UserUtils.getUserId(),metaObject);
        }
        if(metaObject.hasGetter("updateTime")
                && metaObject.getValue("updateTime")==null){
            setFieldValByName("updateTime",new Date(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasGetter("updateUserId")&& metaObject.getValue("updateUserId")==null){
            setFieldValByName("updateUserId",UserUtils.getUserId(),metaObject);
        }
        if(metaObject.hasGetter("updateTime")&& metaObject.getValue("updateTime")==null){
            setFieldValByName("updateTime",new Date(),metaObject);
        }
    }
}
