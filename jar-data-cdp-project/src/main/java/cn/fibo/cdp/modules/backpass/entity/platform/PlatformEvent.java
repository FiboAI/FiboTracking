package cn.fibo.cdp.modules.backpass.entity.platform;

import lombok.Data;

import java.util.List;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description 媒体侧事件基础资料
 * @createDate 2022-07-06 15:42:27
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
public class PlatformEvent {
    private String en;

    private String cn;

    private String remarks;

    private List<PlatformProperty> propertyList;
}
