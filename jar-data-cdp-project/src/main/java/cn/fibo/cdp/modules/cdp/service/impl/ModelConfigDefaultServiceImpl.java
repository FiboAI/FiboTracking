package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.modules.cdp.dao.ModelDataDao;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-17 23:55:41
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Service("modelConfigDefaultService")
public class ModelConfigDefaultServiceImpl extends ServiceImpl<ModelDataDao, ModelDataEntity> implements ModelConfigDataService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Autowired
    private ModelDataDao modelDataDao;

    @Override
    public boolean submitModelData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        return false;
    }

    @Override
    public SubmitModelAnalysisParam getModelDetail(Long id) {
        return modelDataDao.getModelEventDetail(id);
    }

    @Override
    public Map<String, Object> getCalculateData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        return null;
    }

    @Override
    public void exportExcel(SubmitModelAnalysisParam submitModelAnalysisParam, HttpServletResponse response,String fileName) {

    }
}
