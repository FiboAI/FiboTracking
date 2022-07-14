

package cn.fibo.cdp.common.utils;

import cn.fibo.cdp.common.enums.TimeGranularityEnums;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 *
 * @author lisw
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String DATE_TIME_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String[] parsePatterns = {"yyyy-MM-dd","yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss:SSS","yyyy-MM-dd HH:mm:ss.SSS"};


    public static void main(String[] args) {
        String date = "2022-05-23 19:00";
        Date date1 = addDateHours(stringToDate(date, "yyyy-MM-dd HH:mm"), -1);
        System.out.println(format(date1, TimeGranularityEnums.hour.getJavaFormat()));
    }

    /**
     * 计算两个时间中所有的月份
     * @param date1 开始时间
     * @param date2 结束时间
     * @return
     * @throws ParseException
     */
    public static List<String> getMonths(String date1, String date2,boolean isEnd) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(date1);
        Date parse2 = sdf.parse(date2);
        if(isEnd && parse2.compareTo(new Date())==1){
            parse2 = new Date();
        }
        List<String> dateList = new ArrayList<>();
        Calendar c1=Calendar.getInstance();
        c1.setTime(parse);
        //转为周一
        int year = c1.get(Calendar.YEAR);
        int month = c1.get(Calendar.MONTH);
        c1.set(year, month, 1, 0, 0, 0);
        Calendar c2=Calendar.getInstance();
        c2.setTime(parse2);
        int weekYear2 = c2.get(Calendar.YEAR);
        int weekOfYear2 = c2.get(Calendar.WEEK_OF_YEAR);
        c2.setWeekDate(weekYear2, weekOfYear2, Calendar.SUNDAY);
        while (true) {
            int tempMonth = c1.get(Calendar.MONTH);
            String date = c1.getWeekYear() + "-" + ((tempMonth + 1) <= 9 ? "0" + (tempMonth + 1) : tempMonth + 1);
            System.out.println(date);

            dateList.add(date);
            //下一个月<结束日期
            c1.set(Calendar.MONTH, tempMonth +1);
            if(c1.getTimeInMillis()>=c2.getTimeInMillis()){
                break;
            }
        }

        return dateList;
    }

    /**
     * 计算两个时间内所有日期
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static List<String> getDays(String date1, String date2,boolean isEnd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long startTime = sdf.parse(date1).getTime();
        Long endTime = sdf.parse(date2).getTime();
        Long currentTimeMillis = System.currentTimeMillis();
        if(isEnd && endTime>currentTimeMillis){
            endTime = currentTimeMillis;
        }
        List<String> dateList = new ArrayList<String>();
        Long oneDay = 1000 * 60 * 60 * 24L;

        Long time = startTime;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(d);
            dateList.add(date);
            time += oneDay;
        }
        return dateList;
    }

    /**
     * 计算两个时间内所有小时
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static List<String> getHours(String date1, String date2,boolean isEnd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long startTime = sdf.parse(date1).getTime();
        Long endTime = sdf.parse(date2).getTime();
        Long currentTimeMillis = System.currentTimeMillis();
        if(isEnd && endTime>currentTimeMillis){
            endTime = currentTimeMillis;
        }
        List<String> dateList = new ArrayList<String>();
        Long oneHour = 1000 * 60 * 60L;

        Long time = startTime;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00");
            String date = df.format(d);
            dateList.add(date);
            time += oneHour;
        }
        return dateList;
    }

    /**
     * 计算两个时间内所有分钟
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static List<String> getMinutes(String date1, String date2,boolean isEnd) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long startTime = sdf.parse(date1).getTime();
        Long endTime = sdf.parse(date2).getTime();
        Long currentTimeMillis = System.currentTimeMillis();
        if(isEnd && endTime>currentTimeMillis){
            endTime = currentTimeMillis;
        }
        List<String> dateList = new ArrayList<String>();
        Long oneHour = 1000 * 60L;

        Long time = startTime;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = df.format(d);
            dateList.add(date);
            time += oneHour;
        }
        return dateList;
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     *  获取两个日期之间的所有日期 (年月日)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDate(String startTime, String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime()<=endDate.getTime()){
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate=calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
