package cn.fibo.analysis.tracking.model;

import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis
 * @description
 * @createDate 2022-05-06 20:02:04
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
public class DataEntity {
    private String distinct_id;
    private BaseLibEntity lib;
    private BaseIdentitiesEntity identities;
    private BasePropertiesEntity properties;
    private String login_id;
    private String event;
    private Long time;
    private String project;
    private String anonymous_id;
    private String type;
    private String original_id;
    private String ip;
    private Long flume_time;
    private String _track_id;

}
