

package cn.fibo.cdp.modules.oss.cloud;


import cn.fibo.cdp.common.utils.ConfigConstant;
import cn.fibo.cdp.common.utils.Constant;
import cn.fibo.cdp.common.utils.SpringContextUtils;
import cn.fibo.cdp.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 *
 * @author lisw
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
