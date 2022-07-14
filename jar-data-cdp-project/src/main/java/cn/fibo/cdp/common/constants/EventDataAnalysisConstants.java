package cn.fibo.cdp.common.constants;

/**
 * @author lisw
 * @program jar-data-analysis-all
 * @description
 * @createDate 2022-06-10 13:33:48
 * @slogan 长风破浪会有时，直挂云帆济沧海。
 **/
public class EventDataAnalysisConstants {

    public static final String mainTableAliasName = "tb1";

    public static final String mainTableDataAliasName = "tb";

    public static String sqlMainTemplate;

    public static String leftJoinTemplate;

    static{
        sqlMainTemplate = " select "+mainTableAliasName+".dates dates {tb1SelectSQL} from (" +
                " select formatDateTime("+mainTableDataAliasName+".`"+ClickhouseOptions.DATETIME_FIELD+"` ,'{format}') dates {selectSQL}" +
                " from events "+mainTableDataAliasName+" where "+mainTableDataAliasName+".project = '"+ClickhouseOptions.PROJECT+"'" +
                " {whereSQL}" +
                " group by formatDateTime("+mainTableDataAliasName+".`"+ClickhouseOptions.DATETIME_FIELD+"` ,'{format}') {groupSQL}" +
                " ) "+mainTableAliasName;

        leftJoinTemplate= " left join ( " +
                "select formatDateTime(`"+ClickhouseOptions.DATETIME_FIELD+"` ,'{format}') dates {selectSQL} from events {tempTable}" +
                " where {tempTable}.project = '"+ClickhouseOptions.PROJECT+"'" +
                " {whereSQL} " +
                " group by formatDateTime({tempTable}.`"+ClickhouseOptions.DATETIME_FIELD+"` ,'{format}') {groupSQL}" +
                ") {tbName} on {tbName}.dates = "+mainTableAliasName+".`dates` {joinSQL}";

    }
}
