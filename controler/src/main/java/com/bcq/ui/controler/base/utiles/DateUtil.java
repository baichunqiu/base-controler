package com.bcq.ui.controler.base.utiles;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author: BaiCQ
 * @ClassName: DateUtil
 * @date: 2017/11/8
 * @Description: 时间处理工具类
 */
public class DateUtil {
    // 12(月)
    public static final String FORMAT_MM = "MM";
    // 19(日)
    public static final String FORMAT_DD = "dd";
    // 0930  (09月30日)
    public static final String FORMAT_MMDD = "MMdd";
    // 2016(年)
    public static final String FORMAT_YYYY = "yyyy";
    // 20160927194515
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    // 20160927194515558
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    // 20160927
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    // 201609
    public static final String FORMAT_YYYYMM = "yyyyMM";
    // 12/12
    public static final String FORMAT_MM_SLASH_DD = "MM/dd";
    // 2016/09/27
    public static final String FORMAT_YYYY_SLASH_MM_SLASH_DD = "yyyy/MM/dd";
    // 2016/09/27 05:11
    public static final String FORMAT_YYYY_SLASH_MM_SLASH_DD_HH_MM = "yyyy/MM/dd HH:mm";
    // 09/27 05:11
    public static final String FORMAT_MM_SLASH_DD_HH_MM = "MM/dd HH:mm";
    // 09-27 05:11
    public static final String FORMAT_MM_LINE_DD_HH_MM = "MM-dd HH:mm";
    // 15:03:34
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    // 15:03
    public static final String FORMAT_HH_MM = "HH:mm";
    // 03:34
    public static final String FORMAT_MM_SS = "mm:ss";
    // 2016-09-27 15:03:34
    public static final String FORMAT_YYYY_LINE_MM_LINE_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    // 2016-09-27 15:03:34.876
    public static final String FORMAT_YYYY_LINE_MM_LINE_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    // 12-12
    public static final String FORMAT_MM_LINE_DD = "MM-dd";
    // 2016-09
    public static final String FORMAT_YYYY_LINE_MM = "yyyy-MM";
    // 2016-09-27
    public static final String FORMAT_YYYY_LINE_MM_LINE_DD = "yyyy-MM-dd";
    // 12月12日
    public static final String FORMAT_MM_DD = "MM月dd日";
    // 2016年09月
    public static final String FORMAT_YYYY_MM = "yyyy年MM月";
    // 2016年09月27日
    public static final String FORMAT_YYYY_MM_DD = "yyyy年MM月dd日";
    // 2016-09-27 12时19分
    public static final String FORMAT_YYYY_LINE_MM_LINE_DD_HH_MM = "yyyy-MM-dd HH时mm分";
    // yyyy年MM月dd日 HH时mm分ss秒
    public static final String FORMAT_YYYY_MM_DDHH_MM_SS_WORD_ZH = "yyyy年MM月dd日HH时mm分ss秒";
    // yyyy年MM月dd日 HH时mm分ss秒
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_WORD_ZH = "yyyy年MM月dd日 HH时mm分ss秒";
    // 2016.09.28
    public static final String FORMAT_YYYY_DOT_MM_DOT_DD = "yyyy.MM.dd";
    // 2016.09
    public static final String FORMAT_YYYY_DOT_MM = "yyyy.MM";
    // 09.28
    public static final String FORMAT_MM_DOT_DD = "MM.dd";
    // 今天
    public static final String TAG_TODAY = "今天";
    // 昨天
    public static final String TAG_YESTERDAY = "昨天";
    // 一周之内的时间差
    public static final long TAG_TIME_DIFFERENCE = 1000 * 60 * 60 * 24 * 6;
    // 2016-10-16 星期日
    public static final String FORMAT_YYYY_LINE_MM_LINE_DD_WEEK="yyyy-MM-dd EEEE";
    //周日,周一,周二,周三,周四
    public static final String FORMAT_WEEK_E="E";




    /**
     * 如果给定格式的日期是今天或者昨天，则转换成今天昨天字样，否则返回 null
     * @param time  给定时间的字符串
     * @param format 指定时间的格式
     * @return 返回 null 、"昨天" 或者 "今天"
     */
    public static String formatDate2TodayOrYesterday(String time, String format) {
        // 用来接收结果的临时字符串
        String resultTime = null;

        if (!TextUtils.isEmpty(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = null;
            Calendar calendar1 = null;
            Calendar calendar2 = null;
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                if(date != null) {
                    calendar1 = Calendar.getInstance();
                    calendar1.setTime(new Date(System.currentTimeMillis()));
                    calendar2 = Calendar.getInstance();
                    calendar2.setTime(date);
                    if (isTheSameDay(calendar1,calendar2)) {
                        resultTime = TAG_TODAY;
                    }else {
                        // 判断是否为昨天
                        calendar1.add(Calendar.DAY_OF_MONTH, -1);
                        if (isTheSameDay(calendar1,calendar2)) {
                            resultTime = TAG_YESTERDAY;
                        }
                    }
                }
            }
        }

        return resultTime;
    }

    /**
     * 判断给定格式的时间字符串是否为同一天
     * @param timeStr1 时间字符串1
     * @param format1  时间字符串1的格式1
     * @param timeStr2 时间字符串2
     * @param format2 时间字符串2的格式2
     * @return 同一天返回true 否则返回false
     */
    public static boolean isTheSameDay(String timeStr1, String format1, String timeStr2, String format2) {
        // 比较的两个时间字符串默认不是同一天
        boolean isTheSameDay = false;
        if (!TextUtils.isEmpty(timeStr1) && !TextUtils.isEmpty(timeStr2) && !TextUtils.isEmpty(format1) && !TextUtils.isEmpty(format2)) {
            // 源时间格式化器
            SimpleDateFormat sdf1 = null;
            SimpleDateFormat sdf2 = null;
            // 日期对象
            Date d1 = null;
            Date d2 = null;
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();

            if (format1.equals(format2)) {
                sdf1 = new SimpleDateFormat(format1);
                sdf2 = sdf1;
            } else{
                sdf1 = new SimpleDateFormat(format1);
                sdf2 = new SimpleDateFormat(format2);
            }

            try {
                d1 = sdf1.parse(timeStr1);
                d2 = sdf2.parse(timeStr2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (d1 != null && d2 != null) {
                    calendar1.setTime(d1);
                    calendar2.setTime(d2);
                    isTheSameDay = isTheSameDay(calendar1,calendar2);
                }
            }
        }

        return isTheSameDay;
    }

    /**
     * 判断两个日历对象所指的日期是否为同一天
     * @param calendar1 日期对象一
     * @param calendar2 日期对象二
     * @return 同一天 true 否则 false
     */
    public static boolean isTheSameDay(Calendar calendar1, Calendar calendar2) {
        // 比较的两个时间字符串默认不是同一天
        boolean isTheSameDay = false;

        if (calendar1 != null && calendar2 != null) {
            isTheSameDay = ((calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
                        && (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
                        && (calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)));
        }

        return isTheSameDay;
    }

    /**
     * 将指定的时间对象转换成指定格式的时间字符串
     * @param dateStr 原日期字符串
     * @param fromFormat 原日期时间格式
     * @param toFormat 转换后的时间格式
     * @return 转换后的时间字符串、null
     */
    public static String getNewFormatDateString(String dateStr, String fromFormat, String toFormat) {
        // 接收待返回的时间字符串
        String resultTimeStr = null;

        if (!TextUtils.isEmpty(dateStr) && !TextUtils.isEmpty(fromFormat) && !TextUtils.isEmpty(toFormat)) {
            // 1、将原始日期字符串转换成Date对象
            Date date = formatString2Date(dateStr, fromFormat);
            // 2、将Date对象转换成目标样式字符串
            resultTimeStr = formatDate2String(date, toFormat);
        }

        return resultTimeStr;
    }

    /**
     * 指定格式的时间串转换成Date对象
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return 日期对象或者null
     */
    public static Date formatString2Date(String dateStr, String format) {
        // 接收待返回的时间日期对象
        Date resultDate = null;

        if (!TextUtils.isEmpty(dateStr) && !TextUtils.isEmpty(format)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                resultDate = sdf.parse(dateStr);
            } catch (Exception e) {}
        }

        return resultDate;
    }

    /**
     * 将日期转换成指定格式的字符串
     * @param date 待转换的日期
     * @param format 时间格式
     * @return 返回时间字符串或者null
     */
    public static String formatDate2String(Date date, String format) {
        // 接收待返回的时间字符串
        String resultTimeStr = null;

        if(date != null && !TextUtils.isEmpty(format)) {
            try {
                SimpleDateFormat formatPattern = new SimpleDateFormat(format);
                resultTimeStr = formatPattern.format(date);
            } catch (Exception e) {}
        }

        return resultTimeStr;
    }

    /**
     * 判断当前时间字符串是否为今年
     * @param timeStr 带比较的字符串
     * @param format 当前时间字符串的时间格式
     * @return 如果给定时间字符串是当年返回true，否则返回false
     */
    public static boolean isThisYear(String timeStr, String format) {
        // 当前时间字符串默认不是今年
        boolean isThisYear = false;
        // 待比较的日期对象
        Calendar calendar = Calendar.getInstance();
        // 今年日期对象
        Calendar thisYearCalendar = Calendar.getInstance();

        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {
            calendar.setTime(formatString2Date(timeStr,format));
            thisYearCalendar.setTime(new Date());
            isThisYear = (calendar.get(Calendar.YEAR) == thisYearCalendar.get(Calendar.YEAR));
        }

        return isThisYear;
    }

    /**
     * 仿照微信朋友圈时间格式展示时间
     * @param timeStr 时间字符串
     * @param format 时间字符串对应的而时间字符串格式
     *
     * @return 返回时间字符串的格式
     *                      今天 HH:mm
     *                      昨天 昨天
     *                      今年的其他日子  MM/dd
     *                      不是今年的 yyyy/MM/dd
     *                      ""
     */
    public static String imitateWeChatCircleDateFormat (String timeStr, String format) {
        // 存储结果指定格式的字符串
        StringBuffer sBuffer = new StringBuffer();

        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {

            if (TAG_TODAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 今天
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_HH_MM));
            } else if (TAG_YESTERDAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 昨天
                sBuffer.append(TAG_YESTERDAY);
            } else if (isThisYear(timeStr,format)) {
                //今年的其他日子
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_MM_SLASH_DD));
            } else {
                // 跨年
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_YYYY_SLASH_MM_SLASH_DD));
            }
        }
        return sBuffer.toString();
    }

    /**
     * 门诊模块确认就诊时间格式展示时间
     * @param timeStr 时间字符串
     * @param format 时间字符串对应的而时间字符串格式
     * @return 返回时间字符串的格式
     *                      今天 今天
     *                      昨天 昨天
     *                      今年的其他日子  MM/dd
     *                      不是今年的 yyyy/MM/dd
     *                      ""
     */
    public static String ConfirmDiagnosisDateFormat (String timeStr, String format) {
        // 存储结果指定格式的字符串
        StringBuffer sBuffer = new StringBuffer();

        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {

            if (TAG_TODAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 今天
                sBuffer.append(TAG_TODAY);
            } else if (TAG_YESTERDAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 昨天
                sBuffer.append(TAG_YESTERDAY);
            } else if (isThisYear(timeStr,format)) {
                //今年的其他日子
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_MM_SLASH_DD));
            } else {
                // 跨年
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_YYYY_SLASH_MM_SLASH_DD));
            }
        }
        return sBuffer.toString();
    }

    /**
     * 获取参考首页时间格式展示
     * @param timeStr  源时间字符串
     * @param format   源时间字符格式
     * @return  返回字符串格式返回结果集可能情况
     *          今天时间 HH:mm
     *          今年时间 MM/dd HH:mm
     *          跨年时间 yyyy/MM/dd HH:mm
     *          ""
     */
    public static String getTimeForReference(String timeStr, String format) {


        // 存储结果指定格式的字符串
        StringBuffer sBuffer = new StringBuffer();

        if (!TextUtils.isEmpty(timeStr) && !"null".equalsIgnoreCase(timeStr) && !TextUtils.isEmpty(format)) {

            if (TAG_TODAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 今天
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_HH_MM));
            } else if (isThisYear(timeStr,format)) {
                //今年的其他日子
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_MM_SLASH_DD_HH_MM));
            } else {
                // 跨年
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_YYYY_SLASH_MM_SLASH_DD_HH_MM));
            }
        }
        return sBuffer.toString();
    }

    /**
     * 环信会话聊天格式化时间
     * @param timeStr 时间字符串
     * @param format  时间字符串的格式
     * @return 格式化的时间字符串 返回结果可能是以下几种
     *          今天      具体时间 (12:10)
     *          昨天      昨天
     *          一周之内  星期几
     *          一年之内  09/29
     *          跨年     2016/09/29
     *          无效时间  ""
     */
    public static String formatTimeForHX(String timeStr, String format){
        // 存储结果指定格式的字符串
        StringBuffer sBuffer = new StringBuffer();

        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {

            if (TAG_TODAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 今天 HH:mm
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_HH_MM));
            } else if(TAG_YESTERDAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 昨天 昨天
                sBuffer.append(TAG_YESTERDAY);
            } else if(isWithinAWeek(timeStr,format)) {
                // 一周内的数据 星期几
                sBuffer.append(getWeekOfDate(timeStr,format));
            }else if (isThisYear(timeStr,format)) {
                //今年（除了一周内）的其他日子 MM/dd
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_MM_SLASH_DD));
            } else {
                // 跨年 yyyy/MM/dd
                sBuffer.append(getNewFormatDateString(timeStr, format, DateUtil.FORMAT_YYYY_SLASH_MM_SLASH_DD));
            }
        }
        return sBuffer.toString();
    }

    /**
     * 环信会话聊天格式化时间(重载方法)
     * @param timeStamp 时间戳
     * @return 格式化的时间字符串 返回结果可能是以下几种
     *          今天      具体时间 (12:10)
     *          昨天      昨天
     *          一周之内  星期几
     *          一年之内  09/29
     *          跨年     2016/09/29
     *          无效时间  ""
     */
    public static String formatTimeForHX(long timeStamp){
        // 存储结果指定格式的字符串
        String resultTime = "";
        if (timeStamp >= 0) {
            // 时间戳对应日期对象
            Date d = new Date(timeStamp);
            // 日期对应的时间字符串
            String timeStr = DateUtil.formatDate2String(d, DateUtil.FORMAT_YYYY_MM_DDHH_MM_SS_WORD_ZH);

            resultTime = formatTimeForHX(timeStr, DateUtil.FORMAT_YYYY_MM_DDHH_MM_SS_WORD_ZH);
        }
        return resultTime;
    }

    /**
     * 判断给定格式的时间字符串是否在一周(从今天向前推一周)之内
     * @param timeStr 待测时间字符串
     * @param format  待测时间字符串格式
     * @return 如果给定时间在一周之内返回true,否则返回false
     */
    public static boolean isWithinAWeek(String timeStr, String format) {
        // 设置默认时间不在从今天向前推一周之内的范围
        boolean isWithinAWeek = false;
        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {
            // 当前日期
            Date d1 = new Date();
            // 一周前的时间戳
            long timeStamp = d1.getTime() - TAG_TIME_DIFFERENCE;
            // 被测时间日期对象
            Date d2 = formatString2Date(timeStr,format);
            // 被测时间的时间戳
            long currentTimeStamp = d2.getTime();

            if (currentTimeStamp >= timeStamp) {
                isWithinAWeek = true;
            }
        }
        return isWithinAWeek;
    }

    /**
     * 获取指定格式时间字符串对应的星期
     * @param timeStr 时间字符串
     * @param format 时间字符串格式
     * @return  返回值的结果可能{ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"} ,null
     */
    public static String getWeekOfDate(String timeStr, String format) {
        // 存储结果指定星期的字符串
        String weekDay = null;

        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {
            String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Date date = formatString2Date(timeStr,format);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            weekDay = weekDaysName[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        }

        return weekDay;
    }

    /**
     * 仿微信朋友圈个人空间格式化指定格式的时间字符串
     * @param timeStr 原时间字符串
     * @param format 指定的时间字符串格式
     * @return 返回的结果格式
     *                  13*4 (13号4月)
     *                  今天
     *                  昨天
     *                  ""
     */
    public static String formatTimeForWeChatPersonal(String timeStr, String format) {
        // 存储结果指定格式的字符串
        StringBuffer sBuffer = new StringBuffer();

        if (!TextUtils.isEmpty(timeStr) && !TextUtils.isEmpty(format)) {

            if (TAG_TODAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 今天 今天
                sBuffer.append(TAG_TODAY);
            } else if(TAG_YESTERDAY.equals(formatDate2TodayOrYesterday(timeStr,format))) {
                // 昨天 昨天
                sBuffer.append(TAG_YESTERDAY);
            } else {
                Date d = formatString2Date(timeStr,format);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                if (day / 10 == 0) {
                    sBuffer.append("0");
                }
                sBuffer.append(day);
                // 特殊符号用来分隔月份和天
                sBuffer.append("*");
                sBuffer.append(1 + calendar.get(Calendar.MONTH));
            }
        }
        return sBuffer.toString();
    }

    /**
     * 该方法要废弃(此方法中用于患教部分，后期患教里面的内容重构)
     * @param timeStr
     * @return
     */
    @Deprecated
    public static String formatTimeForPatientSearch(String timeStr, String format) {
        String resultTime = null;
        // 今天日期2016/09/30
        String today = formatDate2String(new Date(),FORMAT_YYYYMMDD);
        //昨天日期格式2012/02/19
        String yesterday = formatDate2String(new Date(new Date().getTime() - 1000 * 60 * 60 * 24), DateUtil.FORMAT_YYYYMMDD);
        //先转换格式：1902 2月19
        String timeForma = getNewFormatDateString(timeStr,format, DateUtil.FORMAT_MMDD);

        if (today.contains(timeForma)) {
            resultTime = "今天";
        } else if (yesterday.contains(timeForma)) {
            resultTime = "昨天";
        } else { // 0219
            String a = timeForma.substring(0, 2);
            if ("10".equals(a) || "11".equals(a) || "12".equals(a)) {
                resultTime = timeForma.substring(2, 4) + a + "月";
            } else {
                resultTime = timeForma.substring(2, 4) + timeForma.substring(1, 2) + "月";
            }
        }
        return resultTime;
    }

    /**
     * 获取当前时间几天后的时间
     * @param day 时间间隔
     * @return
     */
    public static Date getDateAfter(int day) {
        Calendar caclender = Calendar.getInstance();
        caclender.setTime(new Date());
        caclender.set(Calendar.DATE, caclender.get(Calendar.DATE) + day);
        return caclender.getTime();
    }

    /**
     * 获取当前时间几天后的时间串
     * @param day 时间间隔
     * @param format  时间对应格式
     * @return
     */
    public static String getDateAfter(int day, String format) {
        Calendar caclender = Calendar.getInstance();
        caclender.setTime(new Date());
        caclender.set(Calendar.DATE, caclender.get(Calendar.DATE) + day);
        return formatDate2String(caclender.getTime(),format);
    }

    /**
     * 获取本月最后一天的日期
     * @param style 输出的样式
     * @return
     */
    public static String getLastDateForThisMonth(String style){
        SimpleDateFormat format = new SimpleDateFormat(style);
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(ca.getTime());
    }

    /**
     * 获取上月最后一天的日期
     * @param style 输出样式
     * @return
     */
    public static String getLastDateForLastMonth(String style){
        SimpleDateFormat format = new SimpleDateFormat(style);
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        return format.format(cale.getTime());
    }

    /**
     * 获取这个时间在本周的位置，星期日是0，依次向后
     * @param date
     * @return
     */
    public static int getPositionForThisDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    /*
    * 将时间转换为时间戳
    */
    public static String dateStrToCurrentMs(String dataStr, String format){
        String res = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(dataStr);
            long ts = date.getTime();
            res = String.valueOf(ts);
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }
}
