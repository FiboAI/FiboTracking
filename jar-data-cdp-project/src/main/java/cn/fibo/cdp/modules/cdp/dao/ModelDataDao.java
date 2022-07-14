package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
@Mapper
public interface ModelDataDao extends BaseMapper<ModelDataEntity> {

    int deleteEventModelData(@Param("modelId") Long modelId);

    /**
     * 获取事件分析详情
     * @param modelId
     * @return
     */
    SubmitModelAnalysisParam getModelEventDetail(@Param("modelId") Long modelId);

    /**
     * 获取漏斗分析详情
     * @param modelId
     * @return
     */
    SubmitModelAnalysisParam getModelFunnelDetail(@Param("modelId") Long modelId);

    /**
     * 获取留存分析详情
     * @param modelId
     * @return
     */
    SubmitModelAnalysisParam getModelKeepDetail(@Param("modelId") Long modelId);
}
