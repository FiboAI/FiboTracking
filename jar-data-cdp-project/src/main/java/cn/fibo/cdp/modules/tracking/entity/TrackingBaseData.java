package cn.fibo.cdp.modules.tracking.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-05-13 15:57:17
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
@TableName("events")
public class TrackingBaseData {

    private String user_id;
}
