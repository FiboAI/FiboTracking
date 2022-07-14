package cn.fibo.cdp.modules.cdp.dao.clickhouse.keep;

import cn.fibo.cdp.modules.cdp.dao.ClickhouseBaseDao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ClickhouseKeepDataDao extends BaseMapper<ClickhouseBaseDao> {


    /**
     * 按日期查询每日留存
     * @param params
     * @return
     */
    List<Map<String,Object>> getKeepDataUserCount(Map<String,Object> params);

    /**
     * 不分组时，查询访问过起始行为的去重总人数
     * @param params
     * @return
     */
    Integer getKeepDataTotalUserCount(Map<String,Object> params);

    /**
     * 分组查询每个分组的访问起始行为的去重总人数,用户若在不同分组，则归类到第一次触发起始行为的分组
     * @param params
     * @return
     */
    List<Map<String,Object>> getKeepDataTotalUserCountByGroup(Map<String,Object> params);

    /**
     * 分组查询每个分组的未来日期留存人数,用户若在不同分组，则归类到第一次触发起始行为的分组
     * @param params
     * @return
     */
    List<Map<String,Object>> getKeepDataRetentionUserCountGroup(Map<String,Object> params);
}
