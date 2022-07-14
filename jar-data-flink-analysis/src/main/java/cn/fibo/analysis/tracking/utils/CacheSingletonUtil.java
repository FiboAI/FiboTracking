package cn.fibo.analysis.tracking.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-07-04 09:45:14
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class CacheSingletonUtil {
    private static volatile CacheSingletonUtil cacheSingletonUtil;
    private static Cache<Object, Object> cache;
    private CacheSingletonUtil(){
        cache = Caffeine.newBuilder()
                //初始数量
                .initialCapacity(1000)
                //最大条数
                .maximumSize(10000)
                //expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准
                //最后一次写操作后经过指定时间过期
                .expireAfterWrite(3600*24, TimeUnit.SECONDS)
                //最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(3600*24, TimeUnit.SECONDS)
                //监听缓存被移除
                .removalListener((key, val, removalCause) -> { })
                //记录命中
                .recordStats()
                .build();
    }


    public static CacheSingletonUtil getInstance(){
        if(cache == null){
            synchronized (CacheSingletonUtil.class) {
                // 线程A或线程B获得该锁进行初始化
                if (cacheSingletonUtil == null) {
                    // 其中一个线程进入该分支，另外一个线程则不会进入该分支
                    cacheSingletonUtil = new CacheSingletonUtil();
                }
            }
        }
        return cacheSingletonUtil;
    }

    /**
     * 添加到内存
     */
    public void addCacheData(Object key,Object obj){
        cache.put(key,obj);
    }

    /**
     * 从内存中取出
     */
    public Object getCacheData(Object key){
        return cache.getIfPresent(key);
    }

    /**
     * 删除Key
     */
    public void removeCacheData(Object key){
        cache.invalidate(key);
    }

}
