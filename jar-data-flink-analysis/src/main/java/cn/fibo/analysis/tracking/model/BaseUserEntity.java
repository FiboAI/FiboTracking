package cn.fibo.analysis.tracking.model;

import lombok.Data;

/**
 * @author lisw
 * @program jar-data-analysis
 * @description
 * @createDate 2022-05-06 19:49:00
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
@Data
public class BaseUserEntity {
    private String user_id;

    private String first_id;

    private String second_id;

    private String device_id_list;

    private String project;

    public BaseUserEntity(String user_id, String first_id, String second_id, String device_id_list, String project) {
        this.user_id = user_id;
        this.first_id = first_id;
        this.second_id = second_id;
        this.device_id_list = device_id_list;
        this.project = project;
    }
}
