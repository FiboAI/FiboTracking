package cn.fibo.analysis.tracking.utils;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-17 12:31:53
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class IPUtils {

    /**
     * 根据IP地址获取城市
     * @param ip
     * @return
     */
    public  static final String REGION = "内网IP|内网IP";
    public static String getCityInfo(String ip) throws IOException {
        DbSearcher searcher = null;
        try {
            String name = "ip2region.db";
            DbConfig config = new DbConfig();
//            URL resource = IPUtils.class.getResource(name);
            searcher = new DbSearcher(config, "/opt/"+name);
            Method method;
            method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String address = dataBlock.getRegion();
            return address.equals(REGION)?"内网IP":address;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(searcher!=null){
                try {
                    searcher.close();
                } catch (IOException ignored) {
                }
            }

        }
        return "";
    }
}
