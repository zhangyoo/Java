package com.nj.nfhy.util.basicUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 15-9-22.
 */
public class DateUtil
{
    /**
     * 默认日期格式, yyyy-MM-dd
     */
    public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 默认时间格式, yyyy-MM-dd hh24:mm:ss
     */
    public static String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 默认时间格式, yyyy-MM-dd hh24:mm:ss
     */
    public static String DEFAULT_TIME_PATTERN_14 = "yyyyMMddHHmmss";

    /**
     * 不可被实例化
     */
    private DateUtil()
    {
        //
    };

    /**
     * 获取今天的日期
     * 
     * @return
     */
    public static String getToday()
    {
        String datevalue = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null)
        {
            datevalue = sdf.format(date);
        }
        return datevalue;
    }
    
    /**
     * 获取今天的日期
     * 
     * @return
     */
    public static String getToday14()
    {
        String datevalue = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN_14);
        if (date != null)
        {
            datevalue = sdf.format(date);
        }
        return datevalue;
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @return yyyy-MM-dd格式, 中国时间({@link Locale}.PRC)
     */
    public static String date2Str(Date date)
    {
        // 使用默认日期格式
        return date2Str(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @param pattern 日期格式
     * @return 参数pattern指定日期格式, 中国时间({@link Locale}.PRC)
     */
    public static String date2Str(Date date,String pattern)
    {
        // 使用上海时间
        return date2Str(date, pattern, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @param timeZone 地区
     * @return yyyy-MM-dd格式, 参数locale指定地区的时间
     */
    public static String date2Str(Date date,TimeZone timeZone)
    {
        return date2Str(date, DEFAULT_DATE_PATTERN, timeZone);
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @param pattern 格式
     * @param timeZone 地区
     * @return pattern指定格式, locale指定区域的时间
     */
    public static String date2Str(Date date,String pattern,TimeZone timeZone)
    {
        String formatedDate = "";
        if (date != null)
        {
            DateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(timeZone);
            formatedDate = format.format(date);
        }
        return formatedDate;
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @retrn yyyy-MM-dd hh24:mm:ss 格式时间, 中国地区({@link Locale}.PRC)
     */
    public static String time2Str(Date date)
    {
        return date2Str(date, DEFAULT_TIME_PATTERN);
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @param pattern 时间格式
     * @retrn pattern指定格式时间, 中国地区({@link Locale}.PRC)
     */
    public static String time2Str(Date date,String pattern)
    {
        return date2Str(date, pattern, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @param timeZone 地区
     * @return yyyy-MM-dd hh24:mm:ss 格式时间, locale指定地区
     */
    public static String time2Str(Date date,TimeZone timeZone)
    {
        return date2Str(date, DEFAULT_TIME_PATTERN, timeZone);
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @param pattern 时间格式
     * @param timeZone 地区
     * @return yyyy-MM-dd hh24:mm:ss 格式时间, locale指定地区
     */
    public static String time2Str(Date date,String pattern,TimeZone timeZone)
    {
        return date2Str(date, pattern, timeZone);
    }

    /**
     * get date from string
     * @param dateStr date string
     * @param pattern date format pattern
     * @param locale locale
     * @return date object
     */
    public static Date getDate(String dateStr,String pattern,Locale locale)
    {
        Date result = null;
        if (dateStr == null || dateStr.trim().length() < 10)
        {
            return null;
        }
        else
        {
            if (dateStr.trim().length() >= pattern.length())
            {
                dateStr = dateStr.substring(0, pattern.length());
            }
            try
            {
                result = new SimpleDateFormat(pattern, locale).parse(dateStr);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * get date from string, parse date using default locale
     * @param dateStr date string
     * @param pattern date format pattern
     * @return date object
     */
    public static Date getDate(String dateStr,String pattern)
    {
        // Log.debug(DateUtil.class, "", "parse date using default locale: " + Locale.getDefault());
        return getDate(dateStr, pattern, Locale.getDefault());
    }

    /**
     * add by dkw 20071101
     * get date from string, parse date using default locale<br>
     * and default pattern 'yyyy-MM-dd'
     * @param dateStr date string
     * @return date object
     */
    public static Date getDate(String dateStr)
    {
        return getDate(dateStr, DEFAULT_DATE_PATTERN, Locale.getDefault());
    }

    /**
     * 一个月中的最后一天
     * @param year
     * @param month
     * @return
     */
    public static int getLastDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 一个月中的第一天
     */
    public static String getFirstDayOfMonth()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = date2Str(cal.getTime());
        return firstDay;
    }

    public static final String getDay(Date d,int day)
    {
        Calendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        gc.setTime(d);
        gc.add(5, day);
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
        return sf.format(gc.getTime());
    }

    public static final String getDay(int day)
    {
        Date d = new Date();
        Calendar gc = new GregorianCalendar();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        gc.setTime(d);
        gc.add(5, day);
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
        return sf.format(gc.getTime());
    }

    public static String getStartDay()
    {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(new Date());
        int week = aCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        return getDay(-week);
    }

    public static String getEndDay()
    {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(new Date());
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);
        return getDay(7 - week);
    }

    public static String getNextWeekDay(int day)
    {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(new Date());
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);
        String curEndDay = getDay(7 - week);
        Date nexWeekDate = getDate(curEndDay);
        return getDay(nexWeekDate, day);
    }

    public static String getWeekReportNo()
    {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(new Date());
        int week = aCalendar.get(Calendar.WEEK_OF_MONTH);
        return date2Str(new Date(), "yyyyMM") + week;
    }

    public static int YEAR = Calendar.YEAR;

    public static int MONTH = Calendar.MONTH;

    public static int DATE = Calendar.DATE;

    public static int HOUR = Calendar.HOUR_OF_DAY;

    public static int MINUTE = Calendar.MINUTE;

    public static int SECOND = Calendar.SECOND;

    public static int MILL_SEC = Calendar.MILLISECOND;

    /**
     * 获取差值时间
     *
     * @param date 当前时间
     * @param n    差值,可为正负
     * @param type 类型
     * @return
     */
    public static Date getDateAfter(Date date,int n,int type)
    {
        if (date == null)
        {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch(type)
        {
            case Calendar.YEAR:
            {
                int year = cal.get(Calendar.YEAR);
                year += n;
                cal.set(Calendar.YEAR, year);
                break;
            }
            case Calendar.MONTH:
            {
                int month = cal.get(Calendar.MONTH);
                month += n;
                cal.set(Calendar.MONTH, month);
                break;
            }
            case Calendar.DATE:
            {
                int nDate = cal.get(Calendar.DATE);
                nDate += n;
                cal.set(Calendar.DATE, nDate);
                break;
            }
            case Calendar.MINUTE:
            {
                int minute = cal.get(Calendar.MINUTE);
                minute += n;
                cal.set(Calendar.MINUTE, minute);
                break;
            }
            case Calendar.HOUR_OF_DAY:
            {
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                hour += n;
                cal.set(Calendar.HOUR_OF_DAY, hour);
                break;
            }
            case Calendar.SECOND:
            {
                int second = cal.get(Calendar.SECOND);
                second += n;
                cal.set(Calendar.SECOND, second);
                break;
            }
            case Calendar.MILLISECOND:
            {
                int mSecond = cal.get(Calendar.MILLISECOND);
                mSecond += n;
                cal.set(Calendar.MILLISECOND, mSecond);
                break;
            }
            default:
            {
                return null;
            }
        }
        date = cal.getTime();
        return date;
    }

    /**
     * 获取精确到秒的时间戳
     * @return 参数pattern指定日期格式, 中国时间({@link Locale}.PRC)
     */
    public static Integer getTimeStamp()
    {
        // 使用上海时间
        return Integer.valueOf((int) (System.currentTimeMillis() / 1000));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String Date2TimeStamp(String dateStr,String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取前一天
     *
     * @param date
     * @return
     */
    public static Date yesterdayDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取后一天
     *
     * @param date
     * @return
     */
    public static Date nextDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取日期unix时间戳
     *
     * @param date
     *
     * @return
     */
    public static Integer Date2UnixDate(Date date)
    {
        return Integer.valueOf(Date2TimeStamp(date2Str(date), DEFAULT_DATE_PATTERN));
    }

    /**
     * 获取时间unix时间戳
     *
     * @param date
     * @return
     */
    public static Integer Date2UnixTime(Date date)
    {
        return Integer.valueOf(Date2TimeStamp(time2Str(date), DEFAULT_TIME_PATTERN));
    }

    /**
    * 字符串转换成日期
    * @param str
    * @return date
    */
    public static Date str2Time(String str)
    {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        Date date = null;
        try
        {
            date = format.parse(str);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date str2Date(String str)
    {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Date date = null;
        try
        {
            date = format.parse(str);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间戳转换成日期
     * @param str
     * @return date
     */
    public static Date timeStampToDate(String str)
    {
        Long timestamp = Long.parseLong(str) * 1000;
        Date date = new Date(timestamp);
        return date;
    }
    
    /**
     * 时间戳转换成字符串日期
     * @param str
     * @return date
     */
    public static String timeStampToString(String str)
    {
        Long timestamp = Long.parseLong(str) * 1000;
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        String dateString = format.format(date);
        return dateString;
    }
    
    public static void main(String[] args)
    {
       String date = DateUtil.timeStampToString("1493107727");
       System.out.println(date);
    }
}
