package cc.mrbird.febs.eventhandle.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {

    public static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 字符串转换时间戳
     *
     * @param str
     * @return
     */
    public static Timestamp str2Timestamp(String str) {
        Date date = str2Date(str, DATETIMEFORMAT);
        return new Timestamp(date.getTime());
    }

    /**
     * 换时间戳转字符串
     *
     * @param timestamp
     * @return
     */
    public static String timestampToStr2(Long timestamp) {
        Timestamp t = new Timestamp(timestamp);
        Date date = new Date(t.getTime());
        return DATETIMEFORMAT.format(date);
    }


    /**
     * 字符串转换成日期
     *
     * @param str
     * @param sdf
     * @return
     */
    public static Date str2Date(String str, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 假分页
     *
     * @param list
     * @param pageSize
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {

        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;

        List<List<T>> listArray = new ArrayList<List<T>>();
        for (int i = 0; i < page; i++) {
            List<T> subList = new ArrayList<T>();
            for (int j = 0; j < listSize; j++) {
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
                if (pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if ((j + 1) == ((j + 1) * pageSize)) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getNowDate() {

        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取从今天起n个月前后的时间 （2017/11/15-2017/08/15）
     *
     * @return
     */
    public static String getMonth(int n) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, n);// 得到前3个月
        Date formNow3Month = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(formNow3Month);
    }


	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 * calendar 对日期进行时间操作
	 * getTimeInMillis() 获取日期的毫秒显示形式
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days));
	}


	/**
     * 转中文格式
     *
     * @param str
     * @return
     */
    public static String toChineseFormat(String str) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月d日  HH时mm分");
        return dateFormat.format(date);
    }

    public static int checkDate(int nowState, String startTime, String endTime) {

        Date nowDate = new Date();
        if (!"".equals(startTime) && !"".equals(endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date startDate;
            Date endDate;
            try {
                startDate = sdf.parse(startTime);
                endDate = sdf.parse(endTime);
                if (nowState == 0 && nowDate.getTime() > startDate.getTime()) {
                    nowState = 1;
                } else if (nowState == 1 && nowDate.getTime() > endDate.getTime()) {
                    nowState = 2;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return nowState;
    }

    public static String afterHour(String time, Integer hour) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = "";
        try {
            Date start = sdf.parse(time);
            long end = start.getTime() + hour * 1000 * 3600;
            Date date = new Date(end);
            endStr = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endStr;
    }

    public static String compareTime(String firsrtime, String secondtime) {
        String mintime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date first = sdf.parse(firsrtime);
            Date second = sdf.parse(secondtime);
            if (first.getTime() < second.getTime()) {
                mintime = firsrtime;
            } else {
                mintime = secondtime;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mintime;
    }
}
