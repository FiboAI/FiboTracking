package cn.fibo.cdp.modules.cdp.dao;

import cn.fibo.cdp.modules.tracking.dao.TrackingBaseDataDao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClickhouseBaseDao extends BaseMapper<ClickhouseBaseDao> {


    List<Map<String,Object>> execSql(@Param("sql") String  sql);

    BigDecimal getYearToYearValue(Map<String,Object> params);

    List<Map<String,Object>> execStandardSql(Map<String,Object> params);

    List<Map<String,Object>> getFunnelUserCount(Map<String,Object> params);

}
