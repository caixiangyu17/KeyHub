package com.bladesort.core.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by leocx on 2016/10/19.
 */

public class DateManager {
    public static Date getDateFromString(String dateString, String format) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
        DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static String getStringFromDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        if (date == null) {
            return null;
        }
        return df.format(date);
    }

    public static int get(Date date, int tag) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(tag);
    }

    public static int getDayOfYear(Date date) {
        return get(date, Calendar.DAY_OF_YEAR);
    }

    public static int getYear(Date date) {
        return get(date, Calendar.YEAR);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return getYear(date1) == getYear(date2) && getDayOfYear(date1) == getDayOfYear(date2);
    }

    public static int compareByDay(Date date1, Date date2) {
        if (getYear(date1) < getYear(date2)) {
            return -1;
        } else if (getYear(date1) > getYear(date2)) {
            return 1;
        } else {
            if (getDayOfYear(date1) < getDayOfYear(date2)) {
                return -1;
            } else if (getDayOfYear(date1) > getDayOfYear(date2)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static int compare(Date date1, Date date2) {
        if (date1.equals(date2)) {
            return 0;
        } else if (date1.before(date2)) {
            return -1;
        } else {
            return 1;
        }
    }

    public static float getDiffByHour(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return Integer.MIN_VALUE;
        } else {
            return (date1.getTime() - date2.getTime()) / (60 * 60 * 1000f);
        }
    }


}
