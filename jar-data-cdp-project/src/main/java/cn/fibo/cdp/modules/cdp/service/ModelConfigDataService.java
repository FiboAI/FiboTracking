package cn.fibo.cdp.modules.cdp.service;

import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lisw
 * @email starsoy@163.com
 * @date 2022-05-13 15:19:42
 */
public interface ModelConfigDataService extends IService<ModelDataEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 模型提交或更新
     * @param submitModelAnalysisParam
     * @return
     */
    boolean submitModelData(SubmitModelAnalysisParam submitModelAnalysisParam);

    /**
     * 获取模型详情
     * @param id
     * @return
     */
    SubmitModelAnalysisParam getModelDetail(Long id);

    /**
     * 计算数据
     */
    Map<String, Object> getCalculateData(SubmitModelAnalysisParam submitModelAnalysisParam);

    /**
     * 数据导出
     */
    void exportExcel(SubmitModelAnalysisParam submitModelAnalysisParam, HttpServletResponse response,String fileName);


}

