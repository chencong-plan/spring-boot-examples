package cc.ccoder.springbootexcel.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ccoder.cc
 * @since 2019-1-7 14:48:46
 */
public class DateUtil {

    public final static String DATETIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期类型转字符串类型
     *
     * @param date       date
     * @param dateFormat 格式
     * @return 返回dateFormat格式字符串
     */
    public static String dateToString(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        try {
            return new SimpleDateFormat(dateFormat).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 返回固定格式时间
     *
     * @param date date
     * @return 返回默认时间格式
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        try {
            return new SimpleDateFormat(DATETIME_FORMAT_YYYYMMDDHHMMSS).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * date to string
     *
     * @param date        date
     * @param datePattern 格式
     * @return string
     */
    public static String date2String(Date date, String datePattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.format(date);
    }
}
