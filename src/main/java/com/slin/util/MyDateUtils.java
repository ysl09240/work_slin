package com.slin.util;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 
 * @author DSNA
 * @date 2010-6-7
 */
public class MyDateUtils {
	
	/**
	 * 静态常量
	 */
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
	
	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;
	
	/**
	 * 计算当前月份的最大天数
	 * @return
	 */
	public static int findMaxDayInMonth() {
		return findMaxDayInMonth(0, 0);
	}
	
	/**
	 * 计算指定日期月份的最大天数
	 * @param date
	 * @return
	 */
	public static int findMaxDayInMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return findMaxDayInMonth(date2Calendar(date));
	}
	
	/**
	 * 计算指定日历月份的最大天数
	 * @param calendar
	 * @return
	 */
	public static int findMaxDayInMonth(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 计算当前年某月份的最大天数
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int month) {
		return findMaxDayInMonth(0, month);
	}
	
	/**
	 * 计算某年某月份的最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		if (month > 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		
		return findMaxDayInMonth(calendar);
	}
	
	/**
	 * Calendar 转换为 Date
	 * @param calendar
	 * @return
	 */
	public static Date calendar2Date(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}
	
	/**
	 * Date 转换为 Calendar
	 * @param date
	 * @return
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * 拿到默认格式的SimpleDateFormat
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return getSimpleDateFormat(null);
	}
	
	/**
	 * 拿到指定输出格式的SimpleDateFormat
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else {
			sdf = new SimpleDateFormat(format);
		}
		
		return sdf;
	}
	
	/**
	 * 转换当前时间为默认格式
	 * @return
	 */
	public static String formatDate2Str() {
		return formatDate2Str(new Date());
	}
	
	/**
	 * 转换指定时间为默认格式
	 * @param date
	 * @return
	 */
	public  static String formatDate2Str(Date date) {
		return formatDate2Str(date, C_TIME_PATTON_DEFAULT);
	}
	
	/**
	 * 转换指定时间为指定格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 转换默认格式的时间为Date
	 * @param dateStr
	 * @return
	 */
	public static Date formatStr2Date(String dateStr) {
		return formatStr2Date(dateStr, null);
	}
	
	/**
	 * 转换指定格式的时间为Date
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.parse(dateStr, new ParsePosition(0));
	}
	
	/**
	 * 转换默认格式的时间为指定格式时间
	 * @param dateStr
	 * @param defineFormat
	 * @return
	 */
	public static String formatDefault2Define(String dateStr, String defineFormat) {
		return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
	}
	
	/**
	 * 转换源格式的时间为目标格式时间
	 * @param dateStr
	 * @param sourceFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatSource2Target(String dateStr, String sourceFormat, String targetFormat) {
		Date date = formatStr2Date(dateStr, sourceFormat);
		return formatDate2Str(date, targetFormat);
	}
	
	/**
	 * 计算当天是该年中的第几周
	 * @return
	 */
	public static int findWeeksNoInYear() {
		return findWeeksNoInYear(new Date());
	}
	
	/**
	 *  计算指定日期是周几
	 * @param date
	 * @return
	 */
	public static String findDayNoInWeek(Date date){
		Calendar calendar = date2Calendar(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;//
		String[] array = new String[] {"一", "二", "三", "四", "五",
			      "六", "七"};
		String tommorrowWeekNoStr = "周" + array[weekNumber-1];
		
		return tommorrowWeekNoStr;
	}
	/**
	 * 返回数字
	 *  计算指定日期是周几
	 * @param date
	 * @return
	 */
	public static Integer findDayNoInWeekIn(Date date){
		Calendar calendar = date2Calendar(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;//
		return weekNumber;
	}
	/**
	 * 计算指定日期是该年中的第几周
	 * @param date
	 * @return
	 */
	public static int findWeeksNoInYear(Date date) {
		if (date == null) {
			return 0;
		}
		return findWeeksNoInYear(date2Calendar(date));
	}
	
	/**
	 * 计算指定日历是该年中的第几周
	 * @param calendar
	 * @return
	 */
	public static int findWeeksNoInYear(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	 /**
     * 当月第一天
     * @return
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }
    
    /**
     * 当月最后一天
     * @return
     */
    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        String last = df.format(ca.getTime());
        StringBuffer str = new StringBuffer().append(last);
        return str.toString();

    }

	
	
	
	/**
	 * 计算一年中的第几星期是几号 
	 * @param year
	 * @param weekInYear
	 * @param dayInWeek
	 * @return
	 */
	public static Date findDateInWeekOnYear(int year, int weekInYear, int dayInWeek) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
		calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);
		
		return calendar.getTime();
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(int days) { 
		Date date = new Date();
		return dayBefore2Object(days, null, date);
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @return
	 */
	public static String dayBefore2Str(int days) {
		String string = formatDate2Str();
		return dayBefore2Object(days, null, string);
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T dayBefore2Object(int days, String format, T instance) {
		Calendar calendar = Calendar.getInstance();
		if (days == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date defineDayBefore2Date(Date date, int days) {
		Date dateInstance = new Date();
		return defineDayBefore2Object(date, days, null, dateInstance);
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param date
	 * @param days
	 * @return
	 */
	public static String defineDayBefore2Str(Date date, int days) {
		String stringInstance = formatDate2Str();
		return defineDayBefore2Object(date, days, null, stringInstance);
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param <T>
	 * @param date
	 * @param days
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineDayBefore2Object(Date date, 
			int days, String format, T instance) {
		if (date == null || days == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param months
	 * @return
	 */
	public static Date monthBefore2Date(int months) {
		Date date = new Date();
		return monthBefore2Object(months, null, date);
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param months
	 * @return
	 */
	public static String monthBefore2Str(int months) {
		String string = formatDate2Str();
		return monthBefore2Object(months, null, string);
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param <T>
	 * @param months
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T monthBefore2Object(int months, String format, T instance) {
		if (months == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date defineMonthBefore2Date(Date date, int months) {
		Date dateInstance = new Date();
		return defineMonthBefore2Object(date, months, null, dateInstance);
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static String defineMonthBefore2Str(Date date, int months) {
		String stringInstance = formatDate2Str();
		return defineMonthBefore2Object(date, months, null, stringInstance);
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param <T>
	 * @param date
	 * @param months
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineMonthBefore2Object(Date date,
			int months, String format, T instance) {
		if (months == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.MONTH, months);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	
	/**
	 * 计算两个日期直接差的天数
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int caculate2Days(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(secondDate);
		int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(dayNum1 - dayNum2);
	}
	
	/**
	 * 计算两个日期直接差的天数
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static int caculate2Days(Calendar firstCalendar, Calendar secondCalendar) {
		if (firstCalendar.after(secondCalendar)) {
			Calendar calendar = firstCalendar;
			firstCalendar = secondCalendar;
			secondCalendar = calendar;
		}
		
		long calendarNum1 = firstCalendar.getTimeInMillis();
		long calendarNum2 = secondCalendar.getTimeInMillis();
		return Math.abs((int)((calendarNum1 - calendarNum2)/C_ONE_DAY));
	}
	
	
	/**
	 * 取得当前小时
	 */
	public static int getCurrentHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 取得当前分钟
	 */
	public static int getCurrentMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}
	
	
	/**
	 * 得到某个时间间隔多少秒时间
	 * >0 之后多少秒 <0之前多少秒
	 * @param date
	 * @param mills
	 * @return
	 */
	public static Date getDayAfterSecond(Date date,int seconds){
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	/**
	 * 得到当前系统的毫秒数
	 * @param date
	 * @param mills
	 * @return
	 */
	public static int getSystemMillisecond(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("SSS");
		String number = simpleDateFormat.format(date);
		return Integer.valueOf(number);
	}
	
	
	
	public static void main(String[] args) {
	/*	Date nowDate = new Date();
		String flashBuyDateStr = MyDateUtils.formatDate2Str(nowDate, "yyyy-MM-dd");
		Date endDate = MyDateUtils.defineDayBefore2Date(MyDateUtils.formatStr2Date(flashBuyDateStr,"yyyy-MM-dd"),1);
		String endDateStr = MyDateUtils.formatDate2Str(endDate, "yyyy-MM-dd");*/
		String yesterday = MyDateUtils.dayBefore2Str(1);
		System.out.println("开始时间:" + yesterday);
		
		/*String tommorrow = MyDateUtils.formatDate2Str(MyDateUtils.dayBefore2Date(1),"MM月dd日");
		String tommorrowWeekNoStr = MyDateUtils.findDayNoInWeek(MyDateUtils.dayBefore2Date(1));
		System.out.println(tommorrow);
		System.out.println(tommorrowWeekNoStr);*/
		
//		System.out.println("当前月份的最大天数:" + findMaxDayInMonth(new Date()));
//		System.out.println("6月份的最大天数:" + findMaxDayInMonth(6));
//		System.out.println("1999-02月份的最大天数:" + findMaxDayInMonth(1999, 2));
//		System.out.println("2000-02月份的最大天数:" + findMaxDayInMonth(2000, 2));
		
//		System.out.println(formatSource2Target("2009-07-24 11:02:35", null, "yyyy/MM/dd"));
//		System.out.println(findWeeksNoInYear());
		
//		System.out.println("2003年的第30个星期一是那天:" + findDateInWeekOnYear(2003, 30, Calendar.MONDAY));
//		System.out.println("2009年的第30个星期一是那天:" + findDateInWeekOnYear(2009, 30, Calendar.FRIDAY));
		
//		System.out.println("【日期格式】当前日期的前7天是:" + dayBefore2Date(-7));
//		System.out.println("【字符格式】当前日期的前7天是:" + dayBefore2Str(-7));
//		System.out.println("【日期格式】当前日期的后7天是:" + dayBefore2Date(7));
//		System.out.println("【字符格式】当前日期的后7天是:" + dayBefore2Str(7));
		
//		System.out.println(formatStr2Date("2009-07-22", "yyyy-MM-dd"));
		
		System.out.println("【日期格式】2009-07-22的前7天是:" + 
				defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -7));
		System.out.println("【字符格式】2009-07-22的前7天是:" + 
				defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -7));
		System.out.println("【日期格式】2009-07-22的后7天是:" + 
				defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));
		System.out.println("【字符格式】2009-07-22的后7天是:" + 
				defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));
		
//		System.out.println("【日期格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
//		System.out.println("【字符格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
//		System.out.println("【日期格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));
//		System.out.println("【字符格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));
		
		System.out.println("【日期格式】2009-07-22的前2月日期是:" + 
				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -2));
		System.out.println("【字符格式】2009-07-22的前2月日期是:" +
				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -2));
		System.out.println("【日期格式】2009-07-22的后2月日期是:" + 
				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 2));
		System.out.println("【字符格式】2009-07-22的后2月日期是:" + 
				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 2));
		
//		Date firstDate = formatStr2Date("2009-07-22", "yyyy-MM-dd");
//		Date secondDate = formatStr2Date("2009-07-18", "yyyy-MM-dd");
//		System.out.println(caculate2Days(firstDate, secondDate));
		
		/*Calendar firstC = date2Calendar(formatStr2Date("2009-07-22", "yyyy-MM-dd"));
		Calendar secondC = date2Calendar(formatStr2Date("2009-07-18", "yyyy-MM-dd"));
		System.out.println(caculate2Days(firstC, secondC));
		
		String hhmm = MyDateUtils.formatDate2Str(new Date(),"HH:MM");
		System.out.println("dd" + hhmm);*/
		String d = MyDateUtils.formatDate2Str(MyDateUtils.defineDayBefore2Date(new Date(), 1), "yyyy-MM-dd");
		
		System.out.println("dd" + d);
	}
	
	
}