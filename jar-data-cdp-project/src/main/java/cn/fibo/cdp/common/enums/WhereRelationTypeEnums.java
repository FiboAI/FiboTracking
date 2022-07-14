package cn.fibo.cdp.common.enums;

import cn.fibo.cdp.common.utils.StringUtils;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-07-01 13:13:40
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public enum WhereRelationTypeEnums {

    AND,OR;


    public static WhereRelationTypeEnums getObject(String name){
        if(StringUtils.isBlank(name)){
           return WhereRelationTypeEnums.AND;
        }
        WhereRelationTypeEnums whereRelationTypeEnums = WhereRelationTypeEnums.valueOf(name);
        return whereRelationTypeEnums;
    }

}
