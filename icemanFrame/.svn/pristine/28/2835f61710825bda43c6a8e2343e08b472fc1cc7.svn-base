package com.iceman.yangtze.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtil {
	public static final String TIMEZONE_CN = "Asia/Shanghai";

	/**
	 * Calendar 中对应的年
	 */
	public final static int YEAR = Calendar.YEAR;
	/**
	 * Calendar 中对应的月
	 */
	public final static int MONTH = Calendar.MONTH;
	/**
	 * Calendar 中对应的日
	 */
	public final static int DAY = Calendar.DAY_OF_MONTH;
	/**
	 * Calendar 中对应的时
	 */
	public final static int HOUR = Calendar.HOUR_OF_DAY;
	/**
	 * Calendar 中对应的分
	 */
	public final static int MINUTE = Calendar.MINUTE;
	/**
	 * Calendar 中对应的秒
	 */
	public final static int SECOND = Calendar.SECOND;

	public final static int DATE = Calendar.DATE;

	/**
	 * 比较到年级别
	 */
	public final static int COMPARELEVEL_YEAR = 0;
	/**
	 * 比较到月钟级别
	 */
	public final static int COMPARELEVEL_MONTH = 1;
	/**
	 * 比较到天级别
	 */
	public final static int COMPARELEVEL_DAY = 2;
	/**
	 * 比较到小时级别
	 */
	public final static int COMPARELEVEL_HOUR = 3;
	/**
	 * 比较到分钟级别
	 */
	public final static int COMPARELEVEL_MINUTE = 4;
	/**
	 * 比较到秒级别
	 */
	public final static int COMPARELEVEL_SECOND = 5;

	/**
	 * 星期数组
	 */
	private final static String[] WEEKNAME_CHINESE = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	private final static String[] WEEKNAME_CHINESE2 = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

	// ================================SIMPLEFORMAT定义================
	/**
	 ************** SIMPLEFORMATTYPE对就的类型定义****************** ＜p＞相应格式类型＜br＞
	 */
	/**
	 * @see #SIMPLEFORMATTYPESTRING1
	 */
	public final static int SIMPLEFORMATTYPE1 = 0x01;
	/**
	 * @see #SIMPLEFORMATTYPESTRING2
	 */
	public final static int SIMPLEFORMATTYPE2 = 0x02;
	/**
	 * @see #SIMPLEFORMATTYPESTRING3
	 */
	public final static int SIMPLEFORMATTYPE3 = 0x03;
	/**
	 * @see #SIMPLEFORMATTYPESTRING4
	 */
	public final static int SIMPLEFORMATTYPE4 = 0x04;
	/**
	 * @see #SIMPLEFORMATTYPESTRING5
	 */
	public final static int SIMPLEFORMATTYPE5 = 0x05;
	/**
	 * @see #SIMPLEFORMATTYPESTRING6
	 */
	public final static int SIMPLEFORMATTYPE6 = 0x06;
	/**
	 * @see #SIMPLEFORMATTYPESTRING7
	 */
	public final static int SIMPLEFORMATTYPE7 = 0x07;
	/**
	 * @see #SIMPLEFORMATTYPESTRING8
	 */
	public final static int SIMPLEFORMATTYPE8 = 0x08;
	/**
	 * @see #SIMPLEFORMATTYPESTRING9
	 */
	public final static int SIMPLEFORMATTYPE9 = 0x09;
	/**
	 * @see #SIMPLEFORMATTYPESTRING10
	 */
	public final static int SIMPLEFORMATTYPE10 = 0x0a;
	/**
	 * @see #SIMPLEFORMATTYPESTRING11
	 */
	public final static int SIMPLEFORMATTYPE11 = 0x0b;
	/**
	 * @see #SIMPLEFORMATTYPESTRING12
	 */
	public final static int SIMPLEFORMATTYPE12 = 0x0c;
	/**
	 * @see #SIMPLEFORMATTYPESTRING13
	 */
	public final static int SIMPLEFORMATTYPE13 = 0x0d;
	/**
	 * @see #SIMPLEFORMATTYPESTRING14
	 */
	public final static int SIMPLEFORMATTYPE14 = 0x0e;
	/**
	 * @see #SIMPLEFORMATTYPESTRING15
	 */
	public final static int SIMPLEFORMATTYPE15 = 0x0f;

	/**
	 * @see #SIMPLEFORMATTYPESTRING16
	 */
	public final static int SIMPLEFORMATTYPE16 = 0x10;

	/**
	 * ********************SIMPLEFORMATTYPE对应的字串*********************
	 */
	/**
	 * SIMPLEFORMATTYPE1 对应类型：yyyyMMddHHmmss
	 * 
	 * @see #SIMPLEFORMATTYPE1
	 */
	public final static String SIMPLEFORMATTYPESTRING1 = "yyyyMMddHHmmss";

	/**
	 * SIMPLEFORMATTYPE2 对应的类型：yyyy-MM-dd HH:mm:ss
	 * 
	 * @see #SIMPLEFORMATTYPE2
	 */
	public final static String SIMPLEFORMATTYPESTRING2 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * SIMPLEFORMATTYPE3 对应的类型：yyyy-M-d HH:mm:ss
	 * 
	 * @see #SIMPLEFORMATTYPE3
	 */
	public final static String SIMPLEFORMATTYPESTRING3 = "yyyy-M-d HH:mm:ss";

	/**
	 * SIMPLEFORMATTYPE4对应的类型：yyyy-MM-dd HH:mm
	 * 
	 * @see #SIMPLEFORMATTYPE4
	 */
	public final static String SIMPLEFORMATTYPESTRING4 = "yyyy-MM-dd HH:mm";

	/**
	 * SIMPLEFORMATTYPE5 对应的类型：yyyy-M-d HH:mm
	 * 
	 * @see #SIMPLEFORMATTYPE5
	 */
	public final static String SIMPLEFORMATTYPESTRING5 = "yyyy-M-d HH:mm";

	/**
	 * SIMPLEFORMATTYPE6对应的类型：yyyyMMdd
	 * 
	 * @see #SIMPLEFORMATTYPE6
	 */
	public final static String SIMPLEFORMATTYPESTRING6 = "yyyyMMdd";

	/**
	 * SIMPLEFORMATTYPE7对应的类型：yyyy-MM-dd
	 * 
	 * @see #SIMPLEFORMATTYPE7
	 */
	public final static String SIMPLEFORMATTYPESTRING7 = "yyyy-MM-dd";

	/**
	 * SIMPLEFORMATTYPE8对应的类型： yyyy-M-d
	 * 
	 * @see #SIMPLEFORMATTYPE8
	 */
	public final static String SIMPLEFORMATTYPESTRING8 = "yyyy-M-d";

	/**
	 * SIMPLEFORMATTYPE9对应的类型：yyyy年MM月dd日
	 * 
	 * @see #SIMPLEFORMATTYPE9
	 */
	public final static String SIMPLEFORMATTYPESTRING9 = "yyyy年MM月dd日";

	/**
	 * SIMPLEFORMATTYPE10对应的类型：yyyy年M月d日
	 * 
	 * @see #SIMPLEFORMATTYPE10
	 */
	public final static String SIMPLEFORMATTYPESTRING10 = "yyyy年M月d日";

	/**
	 * SIMPLEFORMATTYPE11对应的类型：M月d日
	 * 
	 * @see #SIMPLEFORMATTYPE11
	 */
	public final static String SIMPLEFORMATTYPESTRING11 = "M月d日";

	/**
	 * SIMPLEFORMATTYPE12对应的类型：HH:mm:ss
	 * 
	 * @see #SIMPLEFORMATTYPE12
	 */
	public final static String SIMPLEFORMATTYPESTRING12 = "HH:mm:ss";

	/**
	 * SIMPLEFORMATTYPE13对应的类型：HH:mm
	 * 
	 * @see #SIMPLEFORMATTYPE13
	 */
	public final static String SIMPLEFORMATTYPESTRING13 = "HH:mm";
	/**
	 * SIMPLEFORMATTYPE7对应的类型：yyyy-MM-dd
	 * 
	 * @see #SIMPLEFORMATTYPE14
	 */
	public final static String SIMPLEFORMATTYPESTRING14 = "yyyy/MM/dd";

	/**
	 * SIMPLEFORMATTYPE15对应的类型：MM月dd日
	 * 
	 * @see #SIMPLEFORMATTYPE15
	 */
	public final static String SIMPLEFORMATTYPESTRING15 = "MM月dd日";

	/**
	 * SIMPLEFORMATTYPE17对应的类型：yyyy/MM/dd HH:mm:ss
	 * 
	 * @see #SIMPLEFORMATTYPE16
	 */
	public final static String SIMPLEFORMATTYPESTRING16 = "yyyy/MM/dd HH:mm:ss";

	// =====================================End===================================
	/**
	 * 农历
	 */
	final static long[] lunarInfo = new long[]{0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0,
			0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3,
			0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0,
			0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558,
			0x0b540, 0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0,
			0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0,
			0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
			0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};

	/**
	 * 今天 ，明天，后天，数组
	 */
	private final static String[] THREEDAYARR = new String[]{"今天", "明天", "后天"};//

	/**
	 * 获取当前日期 yyyyMMddHHmmss 14位
	 * 
	 * @return String
	 * @see #getCalendarStrBySimpleDateFormat
	 * @see #SIMPLEFORMATTYPESTRING1
	 */
	public static String getCurrentTime() {
		Calendar currentCalendar = Calendar.getInstance();
		return getCalendarStrBySimpleDateFormat(currentCalendar, SIMPLEFORMATTYPE1);
	}

	/**
	 * 获取当前日期 8位
	 * 
	 * @return String
	 * @see #getCalendarStrBySimpleDateFormat
	 * @see #SIMPLEFORMATTYPESTRING6
	 */
	public static String getCurrentDate() {
		Calendar currentCalendar = Calendar.getInstance();
		return getCalendarStrBySimpleDateFormat(currentCalendar, SIMPLEFORMATTYPE6);
	}

	/**
	 * 获取手机当前日期
	 * 
	 * @return Calendar
	 */
	public static Calendar getLocalCalendar() {
		Calendar localCalendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_CN));
		return localCalendar;
	}

	/**
	 * 将日期字串转为 Calendar ,dateStr需超过8位且不能为空,否则返回null
	 * 
	 * @param dateStr
	 * @return Calendar
	 */
	public static Calendar getCalendarByDateStr(String dateStr) {
		if (StringUtil.emptyOrNull(dateStr) || dateStr.length() < 8)
			return null;
		Calendar calendar = getLocalCalendar();
		int year = Integer.valueOf(dateStr.substring(0, 4));
		int month = Integer.valueOf(dateStr.substring(4, 6));
		int day = Integer.valueOf(dateStr.substring(6, 8));
		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 将时间字串转为 Calendar dateStr小于8时返回null，不足14位补0
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Calendar getCalendarByDateTimeStr(String dateStr) {
		if (StringUtil.emptyOrNull(dateStr) || dateStr.length() < 8)
			return null;
		while (dateStr.length() < 14) {
			dateStr += "0";
		}
		Calendar calendar = getLocalCalendar();
		// LogUtil.e("dateStr" + dateStr);
		int year = StringUtil.toInt(dateStr.substring(0, 4));
		int month = StringUtil.toInt(dateStr.substring(4, 6));
		int day = StringUtil.toInt(dateStr.substring(6, 8));
		int hour = StringUtil.toInt(dateStr.substring(8, 10));
		int min = StringUtil.toInt(dateStr.substring(10, 12));
		int second = 0;
		if (dateStr.length() >= 14)
			second = StringUtil.toInt(dateStr.substring(12, 14));
		calendar.set(year, month - 1, day, hour, min, second);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 根据日期返回对应的星期
	 * 
	 * @param calendar
	 *            if calendar 为null则返回空字串
	 * @return "星期*"
	 * @see #WEEKNAME_CHINESE
	 * @see #getWeek
	 */
	public static String getShowWeekByCalendar(Calendar calendar) {
		String weekString = "";
		if (calendar == null) {
			return weekString;
		}
		if (getWeek(calendar) != -1) {
			weekString = WEEKNAME_CHINESE[getWeek(calendar)];
		}
		return weekString;
	}

	/**
	 * 根据日期返回对应的周几
	 * 
	 * @param calendar
	 *            if calendar 为null则返回空字串
	 * @return "星期*"
	 * @see #WEEKNAME_CHINESE
	 * @see #getWeek
	 */
	public static String getShowWeekByCalendar2(Calendar calendar) {
		String weekString = "";
		if (calendar == null) {
			return weekString;
		}
		if (getWeek(calendar) != -1) {
			weekString = WEEKNAME_CHINESE2[getWeek(calendar)];
		}
		return weekString;
	}

	/**
	 * 
	 * 星期几, 数字表述. 从周日开始
	 * 
	 * @param calendar
	 *            if calendar 为null则返回-1
	 * @return 索引
	 * @see Calendar#DAY_OF_WEEK
	 */
	public static int getWeek(Calendar calendar) {
		int theResult = -1;
		if (calendar != null) {
			theResult = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return theResult;
	}

	/**
	 * 返回，今天/明天/后天
	 * 
	 * @param count
	 *            0，1，2 其他则返回空字串
	 * @return
	 * @see #THREEDAYARR
	 */
	public static String getThreeDayDes(int count) {
		if (count >= 0 && count <= 2) {
			return THREEDAYARR[count];
		}
		return "";
	}

	/**
	 * 获取calendar对应的年
	 * 
	 * @param calendar
	 * @return 年字段值
	 * @see #YEAR
	 */
	public static int getYear(Calendar calendar) {
		return calendar.get(YEAR);
	}

	/**
	 * 获取calendar对应的月份
	 * 
	 * @param calendar
	 * @return 月
	 * @see #MONTH
	 */
	public static int getMonth(Calendar calendar) {
		return calendar.get(MONTH) + 1;
	}

	/**
	 * 获取calendar对应的DAY
	 * 
	 * @param calendar
	 * @return DAY
	 * @see #DAY
	 */
	public static int getDay(Calendar calendar) {
		return calendar.get(DAY);
	}

	/**
	 * 获取calendar对应的小时
	 * 
	 * @param calendar
	 * @return HOUR_OF_DAY
	 * @see #HOUR
	 */
	public static int getHourOfDay(Calendar calendar) {
		return calendar.get(HOUR);
	}

	/**
	 * 获取calendar对应的分钟
	 * 
	 * @param calendar
	 * @return MINUTE
	 * @see #MINUTE
	 */
	public static int getMinute(Calendar calendar) {
		return calendar.get(MINUTE);
	}

	/**
	 * 获取calendar对应的秒
	 * 
	 * @param calendar
	 * @return SECOND
	 * @see #SECOND
	 */
	public static int getSecond(Calendar calendar) {
		return calendar.get(SECOND);
	}

	/**
	 * 计算日期字串 DAY字段
	 * 
	 * @param date
	 *            日期字串
	 * @param step
	 *            日期需要加减的值
	 * @return 如果date为空或者小于8位则返回""字串,如果step等于0则直接返回date
	 * @see #getCalendarByDateStr
	 * @see #calculateCalendar
	 * @see #SIMPLEFORMATTYPE6
	 * @see #SIMPLEFORMATTYPESTRING6
	 */
	public static String getDateByStep(String date, int step) {
		if (StringUtil.emptyOrNull(date) || date.length() < 8)
			return "";
		else if (step == 0)
			return date;
		else {
			return getCalendarStrBySimpleDateFormat(calculateCalendar(getCalendarByDateStr(date), DAY, step), SIMPLEFORMATTYPE6);
		}
	}

	/**
	 * 对14位的日期对应的字段进行加减计算 并返回yyyyMMddHHmmss
	 * 
	 * @param date
	 *            14位日期字串
	 * @param field
	 *            Calendar对应的年、月、日 属性
	 * @param step
	 * @return 计算过后的日期字串 yyyyMMddHHmmss
	 * @see #SIMPLEFORMATTYPE1
	 * @see #getCalendarByDateTimeStr
	 * @see #calculateCalendar
	 */
	public static String getTimeByStep(String date, int field, int step) {
		if (StringUtil.emptyOrNull(date) || date.length() < 14)
			return "";
		else if (step == 0)
			return date;
		else {
			return getCalendarStrBySimpleDateFormat(calculateCalendar(getCalendarByDateTimeStr(date), field, step), SIMPLEFORMATTYPE1);
		}
	}

	/**
	 * 日期加减
	 * 
	 * @param field
	 *            calendar对应的属性
	 * @param amount
	 *            需要加减的值
	 * @return Calendar
	 */
	public static Calendar calculateCalendar(Calendar newcalendar, int field, int amount) {
		Calendar calendar = (Calendar) newcalendar.clone();
		if (calendar == null) {
			return null;
		}
		switch (field) {
			case YEAR :
				calendar.add(YEAR, amount);
				break;
			case MONTH :
				calendar.add(MONTH, amount);
				break;
			case DAY :
				calendar.add(DAY, amount);
				break;
			case HOUR :
				calendar.add(HOUR, amount);
				break;
			case MINUTE :
				calendar.add(MINUTE, amount);
				break;
			case SECOND :
				calendar.add(SECOND, amount);
				break;
		}
		return calendar;
	}

	/**
	 * dateStr小于8时返回null，不足14位补0 将dateStr 转换成 SimpleDateFormatType对应格式的字串
	 * 
	 * @param dateStr
	 * @param SimpleDateFormatType
	 * @return
	 * @see #CalendarStrBySimpleDateFormat
	 */
	public static String CalendarStrBySimpleDateFormat(String dateStr, int SimpleDateFormatType) {
		return getCalendarStrBySimpleDateFormat(getCalendarByDateTimeStr(dateStr), SimpleDateFormatType);
	}

	/**
	 * 根据 SimpleDateFormatType类型将calendar转成对应的格式 如果calendar 为null则返回“”
	 * 
	 * @param calendar
	 *            日历
	 * @param SimpleDateFormatType
	 *            需要转换的格式类型
	 * @return SIMPLEFORMATTYPESTRING1对应的格式
	 * @see #SIMPLEFORMATTYPESTRING1
	 * @see #SIMPLEFORMATTYPE1
	 * 
	 */
	public static String getCalendarStrBySimpleDateFormat(Calendar calendar, int SimpleDateFormatType) {
		String str = "";
		String type = "";
		switch (SimpleDateFormatType) {
			case SIMPLEFORMATTYPE1 :
				type = SIMPLEFORMATTYPESTRING1;
				break;
			case SIMPLEFORMATTYPE2 :
				type = SIMPLEFORMATTYPESTRING2;
				break;
			case SIMPLEFORMATTYPE3 :
				type = SIMPLEFORMATTYPESTRING3;
				break;
			case SIMPLEFORMATTYPE4 :
				type = SIMPLEFORMATTYPESTRING4;
				break;
			case SIMPLEFORMATTYPE5 :
				type = SIMPLEFORMATTYPESTRING5;
				break;
			case SIMPLEFORMATTYPE6 :
				type = SIMPLEFORMATTYPESTRING6;
				break;
			case SIMPLEFORMATTYPE7 :
				type = SIMPLEFORMATTYPESTRING7;
				break;
			case SIMPLEFORMATTYPE8 :
				type = SIMPLEFORMATTYPESTRING8;
				break;
			case SIMPLEFORMATTYPE9 :
				type = SIMPLEFORMATTYPESTRING9;
				break;
			case SIMPLEFORMATTYPE10 :
				type = SIMPLEFORMATTYPESTRING10;
				break;
			case SIMPLEFORMATTYPE11 :
				type = SIMPLEFORMATTYPESTRING11;
				break;
			case SIMPLEFORMATTYPE12 :
				type = SIMPLEFORMATTYPESTRING12;
				break;
			case SIMPLEFORMATTYPE13 :
				type = SIMPLEFORMATTYPESTRING13;
				break;
			case SIMPLEFORMATTYPE14 :
				type = SIMPLEFORMATTYPESTRING14;
				break;
			case SIMPLEFORMATTYPE15 :
				type = SIMPLEFORMATTYPESTRING15;
				break;
			case SIMPLEFORMATTYPE16 :
				type = SIMPLEFORMATTYPESTRING16;
				break;
			default :
				type = SIMPLEFORMATTYPESTRING1;
				break;
		}
		if (!StringUtil.emptyOrNull(type) && calendar != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(type);
			dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
			str = (dateFormat).format(calendar.getTime());
		}
		return str;
	}

	/**
	 * 将yyyyMMdd字符串转成yyyy年M月d日 星期x格式
	 * 
	 * @param dateStr
	 * @return 如果 dateStr为空则返回“”
	 * @see #getCalendarByDateStr
	 * @see #getCalendarStrBySimpleDateFormat
	 * @see #getShowWeekByCalendar
	 */
	public static String getShowWeekByDate(String dateStr) {
		if (StringUtil.emptyOrNull(dateStr)) {
			return "";
		}
		Calendar currentDate = getCalendarByDateStr(dateStr);
		String date = getCalendarStrBySimpleDateFormat(currentDate, SIMPLEFORMATTYPE10);
		StringBuffer showStringBuffer = new StringBuffer(18);
		showStringBuffer.append(date);
		String weekString = getShowWeekByCalendar(currentDate);
		showStringBuffer.append("  " + weekString);
		return showStringBuffer.toString().trim();
	}

	/**
	 * 将yyyyMMdd字符串转成yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 * @see #getCalendarByDateStr
	 * @see #SIMPLEFORMATTYPESTRING7
	 * @see #getCalendarStrBySimpleDateFormat
	 */
	public static String getShowWeekByDate5(String dateStr) {
		String str = getCalendarStrBySimpleDateFormat(getCalendarByDateStr(dateStr), SIMPLEFORMATTYPE7);
		return str.toString().trim();
	}

	/**
	 * 将yyyyMMdd字符串转成yyyy年MM月dd日 格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getShowStrByDateStr(String dateStr) {
		Calendar currentDate = getCalendarByDateStr(dateStr);
		if (currentDate == null) {
			return "";
		}
		int year = currentDate.get(Calendar.YEAR);
		int month = currentDate.get(Calendar.MONTH) + 1;
		int day = currentDate.get(Calendar.DAY_OF_MONTH);
		StringBuffer showStringBuffer = new StringBuffer(18);
		showStringBuffer.append(year);
		showStringBuffer.append("年");
		showStringBuffer.append(month);
		showStringBuffer.append("月");
		showStringBuffer.append(day);
		showStringBuffer.append("日");
		return showStringBuffer.toString().trim();
	}

	/**
	 * 根据14位日期字符串返回00:00这样的时间字符串,空则返回""
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeStrFromDateString(String date) {
		Calendar temp = getCalendarByDateTimeStr(date);
		if (temp == null) {
			return "";
		}
		return getCalendarStrBySimpleDateFormat(temp, SIMPLEFORMATTYPE13);

	}

	/**
	 * 根据8位日期字符串返回x月x日这样的日期字符串,空则返回""
	 * 
	 * @param date
	 * @return
	 */
	public static String getSimpleDateStrFromDateString(String date) {
		Calendar temp = getCalendarByDateStr(date);
		if (temp == null) {
			return "";
		}
		return getCalendarStrBySimpleDateFormat(temp, SIMPLEFORMATTYPE11);

	}

}
