package com.momo.common.core.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;


/**
 * 时间计算工具类
 */
@Slf4j
public class DateUtils {

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    /**
     * the milli second of a day
     */
    public static final long DAYMILLI = 24 * 60 * 60 * 1000;
    /**
     * the milli seconds of an hour
     */
    public static final long HOURMILLI = 60 * 60 * 1000;
    /**
     * the milli seconds of a minute
     */
    public static final long MINUTEMILLI = 60 * 1000;
    /**
     * the milli seconds of a second
     */
    public static final long SECONDMILLI = 1000;
    /**
     * added time
     */
    public static final String TIMETO = " 23:59:59";
    /**
     * flag before
     */
    public static final transient int BEFORE = 1;
    /**
     * flag after
     */
    public static final transient int AFTER = 2;
    /**
     * flag equal
     */
    public static final transient int EQUAL = 3;
    /**
     * date format dd/MMM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";
    /**
     * date format dd/MM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";
    /**
     * date format YYYY-MM-DD HH24:MI:SS
     */
    public static final String DB_TIME_PATTERN = "YYYY-MM-DD HH24:MI:SS";
    /**
     * date format YYYYMMDDHH24MISS
     */
    public static final String DB_TIME_PATTERN_1 = "YYYYMMDDHH24MISS";
    /**
     * date format dd/MM/yy HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";
    /**
     * date format dd/MM/yy HH24:mm
     */
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";
    /**
     * date format yyyy年MM月dd日 HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * date format yyyyMMddHHmmss
     */
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";
    /**
     * date format yyyyMMddHHmmssSSS
     */
    public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";
    /**
     * date format yyyyMMdd
     */
    public static final String DATE_FMT_0 = "yyyyMMdd";
    /**
     * date format yyyy/MM/dd
     */
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    /**
     * date format yyyy/MM/dd hh:mm:ss
     */
    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";
    /**
     * date format yyyy-MM-dd
     */
    public static final String DATE_FMT_3 = "yyyy-MM-dd";
    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";
    /**
     * date format yyyy-MM-dd HH
     */
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";
    /**
     * date format yyyy-MM
     */
    public static final String DATE_FMT_6 = "yyyy-MM";
    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_7 = "MM月dd日 HH:mm";
    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_8 = "HH:mm:ss";
    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_9 = "yyyy.MM.dd";
    public static final String DATE_FMT_10 = "HH:mm";
    public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";
    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_12 = "MM月dd日";
    public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";
    public static final String DATE_FMT_14 = "yyyyMM";
    public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";
    public static final String DATE_FMT_16 = "yyyyMMddHHmm";
    public static final String DATE_FMT_17 = "HHmmss";
    public static final String DATE_FMT_18 = "yyyy";
    /**
     * date format yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Date getDateTime() {
        return new Date();
    }

    public static Date getCurrentTime() {
        return new Date();
    }

    public static long getMinuteDifference(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
    /*    // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;*/
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long second = diff / ns;
        return second;
    }

    public static String SYSDATE(String str) {
        Format format = new SimpleDateFormat(str);
        String st = format.format(new Date());
        return st;
    }

    public static String SYSDATEFORMATE() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);// time就是当前时间
        time = date2TimeStamp(time, "yyyy-MM-dd HH:mm:ss");
        return time;
    }


    public static String dateToString2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 转换日期格式
     *
     * @param date_str ,String
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 返回当前天时间范围
    public static String getDateTo(String type, int day) {
        // 当前时间的前一个星期 开始时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        // c.add(Calendar.DAY_OF_MONTH, -6);
        c.add(Calendar.DAY_OF_MONTH, day);
        String start = "";
        if (type.equals("start")) {
            start = format.format(c.getTime()) + " 00:00:00";
        } else if (type.equals("end")) {
            start = format.format(c.getTime()) + " 23:58:00";
        } else {
            start = format.format(c.getTime());
        }
        return start;
    }

    // 根据时间格式返回时间date
    public static Date getStrToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return date;
    }

    public static Date getStrToDate2(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static boolean checkDate(String str) {
        boolean bl = true;
        SimpleDateFormat format = new SimpleDateFormat();
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
            bl = false;
        }
        return bl;
    }

    //得到毫秒
    public static String forMatYearTimeMinuteSSS() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(dt);
    }

    public static String forMatYearTimeMinute() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(dt);
    }

    public static String forbanYearMatTimeMinute() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(dt);
    }

    public static String forMatTime() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(dt);
    }

    public static String forMatTimeMinute() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(dt);
    }

    public static String forMatMounthDay() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        return sdf.format(dt);
    }

    // 字符串转日期
    public static Date stringToDate(String string, String forMat) {
        DateFormat fmt = new SimpleDateFormat(forMat);
        Date date;
        try {
            date = fmt.parse(string);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算两个日期之间有多少个周末周六
     *
     * @param format  较小的时间
     * @param endDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public static BigDecimal getSundayNum(String startDate, String endDate, String format) {
        List yearMonthDayList = new ArrayList();
        Date start = null, stop = null;
        try {
            start = new SimpleDateFormat(format).parse(startDate);
            stop = new SimpleDateFormat(format).parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (start.after(stop)) {
            Date tmp = start;
            start = stop;
            stop = tmp;
        }
        //将起止时间中的所有时间加到List中
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime() <= stop.getTime()) {
            yearMonthDayList.add(new SimpleDateFormat(format)
                    .format(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        Collections.sort(yearMonthDayList);
        int num = 0;//周六，周日的总天数
        int size = yearMonthDayList.size();
        int week = 0;
        for (int i = 0; i < size; i++) {
            String day = (String) yearMonthDayList.get(i);
            // System.out.println(day);
            week = getWeek(day, format);
            if (week == 6 || week == 0) {//周六，周日
                num++;
            }
        }
        return BigDecimal.valueOf(num);
    }

    public static int getWeek(String date, String format) {
        Calendar calendarTemp = Calendar.getInstance();
        try {
            calendarTemp.setTime(new SimpleDateFormat(format).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = calendarTemp.get(Calendar.DAY_OF_WEEK);
        int value = i - 1;//0-星期日
        //        System.out.println(value);
        return value;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(DateUtils.getDatePoor(getStrToDate("2018-12-04 14:23:13"), getStrToDate("2018-12-04 14:03:12")));
    }

    /**
     * 判断两个日期的大小
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static boolean compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            //第一个大于第二个的情况小返回false
            return false;
        } else if (dt1.getTime() < dt2.getTime()) {
            //第一个小于第二个的情况小返回ture
            return true;
        } else {//相等
            return false;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     * 规则: 一天算 8小时 转换成 1.0 天
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 你不管
     * @throws ParseException
     */
    public static BigDecimal daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date faOf1 = sdf.parse(smdate);
        Date faOf2 = sdf.parse(bdate);
        Date d1 = sdf.parse(smdate);
        Date d2 = sdf.parse(bdate);
        d1 = sdf.parse(sdf.format(d1));
        d2 = sdf.parse(sdf.format(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        BigDecimal js = new BigDecimal(1);
        BigDecimal a = new BigDecimal((time2 - time1));
        BigDecimal b = new BigDecimal((1000 * Math.pow(60, 2)));
        //扣除开始和结尾的天数再算中间差,再扣除的中差时间的休息小时
        BigDecimal J = ((a.divide(b, 2, RoundingMode.HALF_UP).subtract(start(smdate))).subtract(endDate(bdate))).divide(BigDecimal.valueOf(24), 1, RoundingMode.HALF_UP);
        BigDecimal workTime = startOne(smdate).add(endDateTow(bdate)).add(J.multiply(BigDecimal.valueOf(8)));
        BigDecimal reTime = js.divide(BigDecimal.valueOf(8), 3, RoundingMode.HALF_UP).multiply(workTime);
        reTime = reTime.setScale(1, BigDecimal.ROUND_HALF_UP);
        return reTime;
    }

    /**
     * 算开始时间距离凌晨 12点的 所有时间 （小时）
     *
     * @param smdate
     * @return BIGBANG
     * @throws ParseException
     */
    public static BigDecimal start(String smdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = sdf.parse(smdate);
        Date d2 = sdf.parse(smdate.split(" ")[0] + " 23:59");
        d1 = sdf.parse(sdf.format(d1));
        d2 = sdf.parse(sdf.format(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        BigDecimal a = new BigDecimal((time2 - time1));
        BigDecimal b = new BigDecimal((1000 * Math.pow(60, 2)));
        a = a.divide(b, 2, RoundingMode.HALF_UP);
        //System.err.println(a);
        return a;
    }

    /**
     * 算凌晨 12点的小时数 距离 结束时间点的 所有时间（小时）
     *
     * @param smdate
     * @return BIGBANG
     * @throws ParseException
     */
    public static BigDecimal endDate(String smdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = sdf.parse(smdate.split(" ")[0] + " 00:01");
        Date d2 = sdf.parse(smdate);
        d1 = sdf.parse(sdf.format(d1));
        d2 = sdf.parse(sdf.format(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        BigDecimal a = new BigDecimal((time2 - time1));
        BigDecimal b = new BigDecimal((1000 * Math.pow(60, 2)));
        a = a.divide(b, 2, RoundingMode.HALF_UP);
        return a;
    }

    /**
     * 算出开始时间的工作时间
     *
     * @param smdate
     * @return BIGBANG
     * @throws ParseException
     */
    public static BigDecimal startOne(String smdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = sdf.parse(smdate);
        Date d2 = sdf.parse(smdate.split(" ")[0] + " 18:30");
        d1 = sdf.parse(sdf.format(d1));
        d2 = sdf.parse(sdf.format(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        BigDecimal a = new BigDecimal((time2 - time1));
        BigDecimal b = new BigDecimal((1000 * Math.pow(60, 2)));
        a = a.divide(b, 3, RoundingMode.HALF_UP);
        boolean one = a.compareTo(BigDecimal.valueOf(6.45)) == 0;
        boolean two = a.compareTo(BigDecimal.valueOf(6.45)) == 1;
        boolean five = a.compareTo(BigDecimal.valueOf(5.45)) == 1;
        boolean seex = a.compareTo(BigDecimal.valueOf(6.45)) == -1;
        if (!two) {
            if (five && seex) {
                a = BigDecimal.valueOf(5.45);
            }
        } else if (one) {
            a = BigDecimal.valueOf(5.5);
        } else {
            a = a.subtract(BigDecimal.valueOf(1));
        }
        boolean three = a.compareTo(BigDecimal.valueOf(8.0)) == 0;
        boolean four = a.compareTo(BigDecimal.valueOf(8.0)) == 1;
        a = three || four ? BigDecimal.valueOf(8) : a;
        return a;
    }

    /**
     * 算出结束时间的工作时间
     *
     * @param smdate
     * @return BIGBANG
     * @throws ParseException
     */
    public static BigDecimal endDateTow(String smdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = sdf.parse(smdate.split(" ")[0] + " 09:30");
        Date d2 = sdf.parse(smdate);
        d1 = sdf.parse(sdf.format(d1));
        d2 = sdf.parse(sdf.format(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        BigDecimal a = new BigDecimal((time2 - time1));
        BigDecimal b = new BigDecimal((1000 * Math.pow(60, 2)));
        a = a.divide(b, 3, RoundingMode.HALF_UP);
        boolean one = a.compareTo(BigDecimal.valueOf(3.45)) == 0;
        boolean two = a.compareTo(BigDecimal.valueOf(3.45)) == 1;
        boolean five = a.compareTo(BigDecimal.valueOf(2.45)) == 1;
        boolean seex = a.compareTo(BigDecimal.valueOf(3.45)) == -1;
        if (!two) {
            if (five && seex) {
                a = BigDecimal.valueOf(2.45);
            }
        } else if (one) {
            a = BigDecimal.valueOf(2.45);
        } else {
            a = a.subtract(BigDecimal.valueOf(1));
        }
        boolean three = a.compareTo(BigDecimal.valueOf(8.0)) == 0;
        boolean four = a.compareTo(BigDecimal.valueOf(8.0)) == 1;
        a = three || four ? BigDecimal.valueOf(8) : a;
        return a;
    }

    /**
     * 分钟+n
     *
     * @param today
     * @return
     */
    public static Date minuteAdd(Date today, int addMinute) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MINUTE, addMinute);
        return c.getTime();
    }

    public static Date judgeThirtyTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = date.substring(10, 16);
        String hour = "00";//小时
        String minutes = "00";//分钟
        String outTime = "00:00";
        StringTokenizer st = new StringTokenizer(time, ":");
        List<String> inTime = new ArrayList<>();
        while (st.hasMoreElements()) {
            inTime.add(st.nextToken());
        }
        hour = inTime.get(0).toString();
        minutes = inTime.get(1).toString();
        if (Integer.parseInt(minutes) >= 30) {
            hour = String.valueOf(Integer.parseInt(hour.trim()) + 1);
            outTime = hour + ":00" + ":00";
        } else {
            outTime = hour + ":30" + ":00";
        }
        String successTime = date.substring(0, 11) + outTime.trim();
        Date date1 = DateUtils.getStrToDate(successTime);
        return date1;
    }

    /**
     * 小时数+n
     *
     * @param today
     * @return
     */
    public static Date hoursAdd(Date today, int addHours) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.HOUR_OF_DAY, addHours);
        return c.getTime();
    }

    /**
     * 天数+n
     *
     * @param today
     * @return
     */
    public static Date dayAdd(Date today, int addDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, addDays);
        return c.getTime();
    }


    /**
     * 获取某天时间的最早时间
     */
    public static Date getStartTime(Date date) {
        Calendar todayStart = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date start = todayStart.getTime();
        long a = todayStart.getTimeInMillis();
        return DateUtils.getStrToDate(dateFormat.format(new Date(a)));
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String formatDate2YYYYMMDDHHMISS(Date date) {
        if (date != null) {
            return formatDate(date, "yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
    }

    public static String formatDate(Date date, String reg) {
        if (date != null) {
            return new SimpleDateFormat(reg).format(date);
        } else {
            return null;
        }

    }

    /**
     * 将秒数装成天，小时
     */
    public static String secondToTime(long second) {
        long days = second / 86400;
        second = second % 86400;
        long hours = second / 3600;
        if (days > 0) {
            return days + "天" + hours + "小时";
        } else {
            return hours + "小时";
        }
    }

    /**
     * 将秒数装成天
     */
    public static String secondToDay(long second) {
        long days = second / 86400;
        second = second % 86400;
        long hours = second / 3600;
        if (days > 0) {
            return days + "天";
        } else {
            return "1天";
        }
    }

    public static String getYesterday() {
        Date dNow = new Date();   //当前时间

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return sdf.format(dNow) + " 00:00:00";    //格式化前一天
    }

    public static String getTomorrow() {
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();

        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, 1);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return sdf.format(dBefore) + " 00:00:00";    //格式化前一天
    }

    public static long getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
    /*    // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;*/
        // 计算差多少分钟
//        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        return diff / ns;
    }

    public static long getDatePoorMinit(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
    /*    // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;*/
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
//        long miao = diff / ns;
        return min;
    }

    /**
     * 获取当前时间的小时数
     */
    public static Integer getCurrentHour() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String time = sdf.format(today);
        return Integer.parseInt(time);
    }

    /**
     * 获取当天零点时间
     */
    public static String todayZeroTime() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return DateUtils.dateToString(zero);
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm
     *
     * @return
     */
    public static String formatDate2YYYYMMDD() {
        return formatDate(new Date(), "yyyy-MM-dd");

    }

    /**
     * 毫秒转时间 //分钟
     */
    public static String millToTime(long end, long begin) {
        long time = end - begin;
        long time1 = time / 1000;
        long m = time1 / 60;
        long m1 = time1 - m * 60;
        return m + "′" + m1 + "″";
    }

    /**
     * 毫秒转时间 //分钟
     */
    public static String millToaTime(long end, long begin) {
        long time = end - begin;
        long time1 = time / 1000;
        long n = time1 / 3600;
        long m = (time1 - n * 3600) / 60;
        long m1 = time1 - (n * 3600 + m * 60);
        return n + ":" + m + "′" + m1 + "″";

    }

    public static Date localDateTimeTOdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static Date stringToDate(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
            Date utilDate = dateFormat.parse(time);
            return utilDate;
        } catch (Exception e) {
            log.error("yyyy-MM-DD HH:mm:ss 时间转换错误：{}=={}", time, e.getMessage());
            return null;
        }
    }

    public static Long dateDiff(String startTime, String endTime,
                                String format, String str) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
           /* hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒*/
            return day;
            // 输出结果
            /*log.info("时间相差：" + day + "天" + (hour - day * 24) + "小时"
                    + (min - day * 24 * 60) + "分钟" + sec + "秒。");
            log.info("hour=" + hour + ",min=" + min);
            if (str.equalsIgnoreCase("h")) {
                return hour;
            } else {
                return min;
            }*/

        } catch (ParseException e) {
            e.printStackTrace();
            log.error("计算两个时间相差：{}=={}", e.getMessage());
        }
        if (str.equalsIgnoreCase("h")) {
            return hour;
        } else {
            return min;
        }
    }

    /**
     * 当前时间 大于beginTime  返回true
     *
     * @param endTime
     * @return
     */
    public static boolean timeDifference(Date endTime) {
        Instant instant = endTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

  /*      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
//        比较  现在的时间 比 设定的时间 之前  返回的类型是Boolean类型
      return   nowTime.isAfter(LocalDateTime.parse("2017-09-01 13:34:34", formatter));*/
        //获取当前时间
        LocalDateTime nowTime = LocalDateTime.now();
        boolean flag = nowTime.isAfter(localDateTime);
        return flag;
    }

    /**
     * localDateTime 转 自定义格式string
     *
     * @param localDateTime
     * @param format        例：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(formatter);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string 转 LocalDateTime
     *
     * @param dateStr 例："2017-08-11 01:00:00"
     * @param format  例："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据时间获取当月有多少天数
     *
     * @param date
     * @return
     */
    public static int getActualMaximum(Date date) {

        return dateToLocalDateTime(date).getMonth().length(dateToLocalDate(date).isLeapYear());
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return 1:星期一；2:星期二；3:星期三；4:星期四；5:星期五；6:星期六；7:星期日；
     */
    public static int getWeekOfDate(Date date) {
        return dateToLocalDateTime(date).getDayOfWeek().getValue();
    }


    /**
     * 计算两个日期LocalDate相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsDateDiffDay(LocalDate before, LocalDate after) {

        return Math.abs(Period.between(before, after).getDays());
    }

    /**
     * 计算两个时间LocalDateTime相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsTimeDiffDay(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getDays());
    }

    /**
     * 计算两个时间LocalDateTime相差的月数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsTimeDiffMonth(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getMonths());
    }

    /**
     * 计算两个时间LocalDateTime相差的年数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsTimeDiffYear(LocalDateTime before, LocalDateTime after) {

        return Math.abs(Period.between(before.toLocalDate(), after.toLocalDate()).getYears());
    }


    /**
     * 根据传入日期返回星期几
     *
     * @param date 日期
     * @return 1-7 1：星期天,2:星期一,3:星期二,4:星期三,5:星期四,6:星期五,7:星期六
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取指定日期的当月的月份数
     *
     * @param date
     * @return
     */
    public static int getLastMonth(Date date) {
        return dateToLocalDateTime(date).getMonth().getValue();

    }


    /**
     * 特定日期的当月第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisMonth(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    /**
     * 特定日期的当月最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate lastThisMonth(Date date) {
        int lastDay = getActualMaximum(date);
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), lastDay);
    }


    /**
     * 特定日期的当年第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisYear(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), 1, 1);

    }


    public static Timestamp getCurrentDateTime() {
        return new Timestamp(Instant.now().toEpochMilli());

    }

    /**
     * 获取当前时间
     *
     * @return LocalDateTime
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

    }


    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(Date date, String customTime) {
        String dateStr = dateToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 修改日期时间的时间部分
     *
     * @param date
     * @param customTime 必须为"hh:mm:ss"这种格式
     */
    public static LocalDateTime reserveDateCustomTime(Timestamp date, String customTime) {
        String dateStr = timestampToLocalDate(date).toString() + " " + customTime;
        return stringToLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return LocalDateTime
     */
    public static final LocalDateTime zerolizedTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);

    }


    /**
     * 把时间变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime zerolizedTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    /**
     * 把日期的时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getEndTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999 * 1000000);
    }

    /**
     * 把时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime getEndTime(Timestamp date) {
        LocalDateTime localDateTime = timestampToLocalDateTime(date);
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999 * 1000000);
    }

    /**
     * 计算特定时间到 当天 23.59.59.999 的秒数
     *
     * @return
     */
    public static int calculateToEndTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime end = getEndTime(date);
        return (int) (end.toEpochSecond(ZoneOffset.UTC) - localDateTime.toEpochSecond(ZoneOffset.UTC));
    }


    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param localDateTime 例：ChronoUnit.DAYS
     * @param chronoUnit
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime, ChronoUnit chronoUnit, int num) {
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param chronoUnit 例：ChronoUnit.DAYS
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(Date date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * 增加或减少年/月/周/天/小时/分/秒数
     *
     * @param chronoUnit 例：ChronoUnit.DAYS
     * @param num
     * @return LocalDateTime
     */
    public static LocalDateTime addTime(Timestamp date, ChronoUnit chronoUnit, int num) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
        return localDateTime.plus(num, chronoUnit);
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        long nanoOfSecond = (date.getTime() % 1000) * 1000000;
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));

        return localDateTime;
    }

    /**
     * Timestamp 转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime timestampToLocalDateTime(Timestamp date) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date.getTime() / 1000, date.getNanos(), ZoneOffset.of("+8"));

        return localDateTime;
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {

        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     * timestamp 转 LocalDateTime
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate timestampToLocalDate(Timestamp date) {

        return timestampToLocalDateTime(date).toLocalDate();
    }

    /**
     * 比较两个LocalDateTime是否同一天
     *
     * @param begin
     * @param end
     * @return
     */
    public static boolean isTheSameDay(LocalDateTime begin, LocalDateTime end) {
        return begin.toLocalDate().equals(end.toLocalDate());
    }


    /**
     * 比较两个时间LocalDateTime大小
     *
     * @param time1
     * @param time2
     * @return 1:第一个比第二个大；0：第一个与第二个相同；-1：第一个比第二个小
     */
    public static int compareTwoTime(LocalDateTime time1, LocalDateTime time2) {

        if (time1.isAfter(time2)) {
            return 1;
        } else if (time1.isBefore(time2)) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 比较time1,time2两个时间相差的秒数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffSecond(Timestamp time1, Timestamp time2) {
        long diff = timestampToLocalDateTime(time1).toEpochSecond(ZoneOffset.UTC) - timestampToLocalDateTime(time2).toEpochSecond(ZoneOffset.UTC);
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 比较time1,time2两个时间相差的分钟数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffMin(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 60;
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * 比较time1,time2两个时间相差的小时数，如果time1<=time2,返回0
     *
     * @param time1
     * @param time2
     */
    public static long getTwoTimeDiffHour(Timestamp time1, Timestamp time2) {
        long diff = getTwoTimeDiffSecond(time1, time2) / 3600;
        if (diff > 0) {
            return diff;
        } else {
            return 0;
        }
    }

    /**
     * description :获取当天的起始时间和结束时间
     *
     * @return
     */
    public static List<Date> getTodayStartAndEndTime() throws ParseException {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonth().getValue();
        int dayOfMonth = now.getDayOfMonth();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = format.parse(year + "-" + month + "-" + dayOfMonth + " 00:00:00");
        Date endTime = format.parse(year + "-" + month + "-" + (dayOfMonth + 1) + " 00:00:00");
        ArrayList<Date> dates = new ArrayList<>(2);
        dates.add(startTime);
        dates.add(endTime);
        return dates;
    }

    /**
     * 判断当前时间是否在时间范围内
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeInRange(Date startTime, Date endTime) throws Exception {
        LocalDateTime now = getCurrentLocalDateTime();
        LocalDateTime start = dateToLocalDateTime(startTime);
        LocalDateTime end = dateToLocalDateTime(endTime);
        return (start.isBefore(now) && end.isAfter(now)) || start.isEqual(now) || end.isEqual(now);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当前时间
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getDatePoorTwo(Date endDate, Date nowDate) {

        long nmh = 1000 * 24 * 60 * 60 * 30L;
        long nd = 1000 * 24 * 60 * 60L;
        long nh = 1000 * 60 * 60L;
        long nm = 1000 * 60L;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        long diffTwo = (endDate.getTime() - nowDate.getTime()) / 1000;
        // 计算差多少天
        long mh = diff / nmh;
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;

        if (mh != 0) {
            return mh + "月" + (day - mh * 30) + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (day != 0) {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (hour != 0) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (min != 0) {
            return min + "分钟" + sec + "秒";
        }
        if (sec != 0) {
            return sec + "秒";
        }
        return "";
    }

    public static String getDatePoorTwo_V2(Date endDate, Date nowDate) {

        long nymh = 1000 * 24 * 60 * 60 * 30 * 12L;
        long nmh = 1000 * 24 * 60 * 60 * 30L;
        long nd = 1000 * 24 * 60 * 60L;
        long nh = 1000 * 60 * 60L;
        long nm = 1000 * 60L;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        long diffTwo = (endDate.getTime() - nowDate.getTime()) / 1000;
        // 计算差多少天
        long mh = diff / nmh;
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;

        if (mh != 0) {
            return mh + "月" + (day - mh * 30) + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (day != 0) {
            return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (hour != 0) {
            return hour + "小时" + min + "分钟" + sec + "秒";
        }
        if (min != 0) {
            return min + "分钟" + sec + "秒";
        }
        if (sec != 0) {
            return sec + "秒";
        }
        return "";
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日期
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

}
