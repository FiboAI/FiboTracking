package cn.fibo.cdp.modules.cdp.service.impl;

import cn.fibo.cdp.common.constants.ClickhouseOptions;
import cn.fibo.cdp.common.constants.RedisKeys;
import cn.fibo.cdp.common.enums.ChartTypeEnums;
import cn.fibo.cdp.common.enums.ModelTypeEnums;
import cn.fibo.cdp.common.enums.RtnCode;
import cn.fibo.cdp.common.exception.CustomExceptionException;
import cn.fibo.cdp.common.utils.PageUtils;
import cn.fibo.cdp.common.utils.RedisUtils;
import cn.fibo.cdp.common.utils.StringUtils;
import cn.fibo.cdp.datasource.annotation.DataSource;
import cn.fibo.cdp.modules.cdp.dao.ClickhouseBaseDao;
import cn.fibo.cdp.modules.cdp.dao.MetadataCustomsqlDataDao;
import cn.fibo.cdp.modules.cdp.dao.ModelDataDao;
import cn.fibo.cdp.modules.cdp.entity.MetadataCustomsqlDataEntity;
import cn.fibo.cdp.modules.cdp.entity.ModelDataEntity;
import cn.fibo.cdp.modules.cdp.entity.param.SubmitModelAnalysisParam;
import cn.fibo.cdp.modules.cdp.service.ModelConfigDataService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description 自定义分析
 * @createDate 2022-06-24 10:02:11
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Service("modelConfigCustomDataService")
public class ModelConfigCustomDataServiceImpl extends ServiceImpl<ModelDataDao, ModelDataEntity> implements ModelConfigDataService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Autowired
    private MetadataCustomsqlDataDao metadataCustomsqlDataDao;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ModelDataDao modelDataDao;

    @Autowired
    private ClickhouseBaseDao clickhouseBaseDao;

    @Override
    public boolean submitModelData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        //chartType定死
        submitModelAnalysisParam.setChartType(ChartTypeEnums.chart_customsql.getCode());
        //基本信息
        ModelDataEntity modelDataEntity = new ModelDataEntity();
        BeanUtils.copyProperties(submitModelAnalysisParam, modelDataEntity);
        if (modelDataEntity.getId() != null) {
            //如果是修改，删除分路相关表，在进行插入。
            modelDataDao.deleteEventModelData(modelDataEntity.getId());
            String  redisKey = RedisKeys.CDP_DATA_ANALYSIS_CALC.replace("{type}",submitModelAnalysisParam.getType().toString()).replace("{id}",submitModelAnalysisParam.getId().toString());
            redisUtils.delete(redisKey);
        }
        this.saveOrUpdate(modelDataEntity);
        MetadataCustomsqlDataEntity metadataCustomsqlData = submitModelAnalysisParam.getMetadataCustomsqlData();
        metadataCustomsqlData.setModelId(modelDataEntity.getId());
        metadataCustomsqlDataDao.insert(metadataCustomsqlData);
        return true;
    }

    @Override
    public SubmitModelAnalysisParam getModelDetail(Long id) {
        return null;
    }

    @Override
    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public Map<String, Object> getCalculateData(SubmitModelAnalysisParam submitModelAnalysisParam) {
        Map<String,Object> rtnMap = new HashMap<>();
        List<Map<String, Object>> queryDatas = queryDatas(submitModelAnalysisParam);
        List<Map<String,String>> columns = new ArrayList<>();
        if(!CollectionUtils.isEmpty(queryDatas)){
            Set<String> titleSets = queryDatas.get(0).keySet();
            for (String titleSet : titleSets) {
                Map<String,String> object =  new LinkedHashMap<>();
                object.put("dataIndex",titleSet);
                object.put("title",titleSet);
                columns.add(object);
            }
        }
        Map<String,Object> dataMaps = new HashMap<>();
        dataMaps.put("columns",columns);
        dataMaps.put("data",queryDatas);
        rtnMap.put("datas",dataMaps);
        return rtnMap;
    }

    private List<Map<String, Object>> queryDatas(SubmitModelAnalysisParam submitModelAnalysisParam){
        MetadataCustomsqlDataEntity metadataCustomsqlData = submitModelAnalysisParam.getMetadataCustomsqlData();
        String startTime = submitModelAnalysisParam.getStartTime();
        String endTime = submitModelAnalysisParam.getEndTime();
        String querySql = metadataCustomsqlData.getQuerySql();
        if(StringUtils.isNotBlank(startTime)){
            querySql = querySql.replace("{{startTime}}",startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            querySql = querySql.replace("{{endTime}}",endTime);
        }
        List<Map<String, Object>> queryDatas;
        try{
            queryDatas = clickhouseBaseDao.execSql(querySql);
        }catch (Exception e){
            throw new CustomExceptionException(RtnCode.CDP_CUSTOMSQL_ERROR.getMsg(), RtnCode.CDP_CUSTOMSQL_ERROR.getCode());
        }

        return queryDatas;
    }

    @Override
    @DataSource(ClickhouseOptions.ANALYSIS_CLICKHOUSE)
    public void exportExcel(SubmitModelAnalysisParam submitModelAnalysisParam, HttpServletResponse response,String fileName) {
        List<Map<String, Object>> queryDatas = queryDatas(submitModelAnalysisParam);
        if(!CollectionUtils.isEmpty(queryDatas)){
            Set<String> titleSets = queryDatas.get(0).keySet();

            List<List<String>> header = new ArrayList<>();
            for (String titleSet : titleSets) {
                List<String> firstHeader = new ArrayList<>();
                firstHeader.add(titleSet);
                header.add(firstHeader);
            }
            List<Collection<Object>> datas = new ArrayList<>();
            queryDatas.forEach(item->{
                Collection<Object> values = item.values();
                datas.add(item.values());
            });

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            try {
                fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
                EasyExcel.write(response.getOutputStream()).sheet("data").head(header).doWrite(datas);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
