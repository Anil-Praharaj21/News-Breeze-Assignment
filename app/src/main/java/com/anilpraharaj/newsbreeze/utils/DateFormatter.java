package com.anilpraharaj.newsbreeze.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author anilpraharaj on 05/12/21
 */
public class DateFormatter {

    private static final String TYPE_DAY = "day", TYPE_MONTH = "month", TYPE_YEAR = "year", TYPE_HOUR = "hour", TYPE_MIN = "min", TYPE_SEC = "sec";
    private static final String FORMAT_TYPE_24_HRS = "EEE dd MMMM, yyyy HH:mm:ss zzzz";
    private static final String FORMAT_TYPE_12_HRS = "EEE dd MMMM, yyyy hh:mm:ss a zzzz";
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String FORMAT_TYPE_yyyyMMdd_HHmmss = "yyyyMMdd_HHmmss";
    public static final String STANDARD_DATE_FORMAT = "dd-MM-yy";

    private Date mDate;
    private SimpleDateFormat mDateFormat;

    public DateFormatter() {
        mDate = new Date();
        mDateFormat = new SimpleDateFormat(FORMAT_TYPE_24_HRS);
    }

    public DateFormatter(String dateStr, String formatStr) {
        mDateFormat = new SimpleDateFormat(formatStr);
        try {
            mDate = mDateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
    }

    public static DateFormatter getDate() {
        return new DateFormatter();
    }

    public DateFormatter setDate(Date date) {
        mDate = date;
        return this;
    }

    public Date getmDate() {
        return mDate;
    }

    /*******************************************************************************************************************
     * ####Note:- * format date should contains (case dependant) => y or yy or yyyy format for years * y or yyyy for 4
     * digits * yy for 3 digits => M or MMM or MMMM format for months * M for single digit value of month (ex. for
     * February it shows 2 and November it shows 11) * MM for 2 digit value of month (ex. for February it shows 02 and
     * November it shows 11) * MMM for starting 3 alphabets of the month * MMMM for full word of month => d or dd format
     * for days * d for single digit value of the day (ex. for 1-9 day of the month it shows single digit and 10 onwards
     * its shows 2 digit value) * dd for 2 digit value of the day (ex. for 1-9 day of the month it adds 0 in the
     * starting and other as is) => h or hh format for 12 hours convention * h for single digit value of the hour (ex.
     * for 1-9 hours it shows single digit and 10 onwards its shows 2 digit value) * hh for 2 digit value of the hour
     * (ex. for 1-9 hours it adds 0 in the starting and other as is) => H or HH format for 24 hours convention * H for
     * single digit value of the hour (ex. for 1-9 hours it shows single digit and 10 onwards its shows 2 digit value) *
     * HH for 2 digit value of the hour (ex. for 1-9 hours it adds 0 in the starting and other as is) => m or mm format
     * for minutes * m for single digit value of the minutes (ex. for 1-9 minutes it shows single digit and 10 onwards
     * its shows 2 digit value) * mm for 2 digit value of the minutes (ex. for 1-9 minutes it adds 0 in the starting and
     * other as is) => s or ss format for seconds * s for single digit value of the seconds (ex. for 1-9 minutes it
     * shows single digit and 10 onwards its shows 2 digit value) * ss for 2 digit value of the seconds (ex. for 1-9
     * minutes it adds 0 in the starting and other as is) => E or EEE or EEEE or EEEEE for week name * E or EE or EEE
     * for first 3 digit of the week name * EEEE for the full name of the week name * EEEEE for the starting first digit
     * of the week name => Z or ZZZ or ZZZZ for the Standard time zone in numbers * Z or ZZZ to show the only time (ex.
     * +0530) * ZZZZ to show unit with the time zone (ex. GMT+05:30) => z or zzz or zzzz for the Standard time zone in
     * Words * z or zzz to show the time zone in word in short form * zzzz to show the time zone in full words
     *******************************************************************************************************************/
    public String format(String formatType) {
        formatType = formatType.contains("a") ? formatType.replace("H", "h") : formatType;
        mDateFormat = new SimpleDateFormat(formatType);
        return mDateFormat.format(mDate);
    }

    public DateFormatter addDay(int num) {
        getDateString(num, TYPE_DAY);
        return this;
    }

    public DateFormatter addMonth(int num) {
        getDateString(num, TYPE_MONTH);
        return this;
    }

    public DateFormatter addYear(int num) {
        getDateString(num, TYPE_YEAR);
        return this;
    }

    public DateFormatter addHour(int num) {
        getDateString(num, TYPE_HOUR);
        return this;
    }

    public DateFormatter addMin(int num) {
        getDateString(num, TYPE_MIN);
        return this;
    }

    public DateFormatter addSec(int num) {
        getDateString(num, TYPE_SEC);
        return this;
    }

    public String getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.DAY_OF_MONTH) + "";
    }

    public String getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return (calendar.get(Calendar.MONTH) + 1) + "";
    }

    public String getFullMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        return format.format(mDate);
    }

    public String getThreeAlphOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MMM");
        return format.format(mDate);
    }

    public String getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.YEAR) + "";
    }

    public String getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.HOUR) + "";
    }

    public String getMin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.MINUTE) + "";
    }

    public String getSec() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.SECOND) + "";
    }

    public String getWeekName() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(mDate);
    }

    public String get3AlphaWeek() {
        SimpleDateFormat format = new SimpleDateFormat("EEE");
        return format.format(mDate);
    }

    public String getAlphaWeek() {
        SimpleDateFormat format = new SimpleDateFormat("E");
        return format.format(mDate).substring(0, 1);
    }

    public String getTime12HrsFormat() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        return format.format(mDate);
    }

    public String getTime24HrsFormat() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(mDate);
    }

    public String getTime12HrsFormatWithSec() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
        return format.format(mDate);
    }

    public String getTime24HrsFormatWithSec() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(mDate);
    }

    public String getLastSeen() {
        // milliseconds
        long diff = Math.abs(new Date().getTime() - mDate.getTime());
        int numofYears = 0;
        long numOfMonths = diff / (2592000000l);
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60));
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        if (numofYears > 0) {
            return (numofYears + 1) + " Year(s) ago";
        } else if (numOfMonths > 0) {
            if (numOfDays % numOfMonths > 15) {
                return (numOfMonths + 1) + " Month(s) ago";
            }
            return numOfMonths + " Month(s) ago";
        } else if (numOfDays > 0) {
            if (hours % 24 > 12) {
                return (numOfDays + 1) + " Day(s) ago";
            }
            return numOfDays + " Day(s) ago";
        } else if (hours > 0) {
            if (minutes % 60 > 30) {
                return (hours + 1) + " Hour(s) ago";
            }
            return hours + " Hour(s) ago";
        } else if (minutes > 0) {
            if (seconds % 60 > 30) {
                return (minutes + 1) + " Mins. ago";
            }
            return minutes + " Mins. ago";
        } else {
            return "Just now";
        }
    }

    public String getLastSeen(Date previous) {
        long diff = Math.abs(new Date().getTime() - previous.getTime());
        int numofYears = 0;
        long numOfMonths = diff / (2592000000l);
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60));
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        if (numofYears > 0) {
            return "For " + (numofYears + 1) + " Year";
        } else if (numOfMonths > 0) {
            if (numOfDays % numOfMonths > 15) {
                return "For " + (numOfMonths + 1) + " Month";
            }
            return "For " + numOfMonths + " Month";
        } else if (numOfDays > 0) {
            if (hours % 24 > 12) {
                return "For " + (numOfDays + 1) + " Day";
            }
            return "For " + numOfDays + " Day";
        } else if (hours > 0) {
            if (minutes % 60 > 30) {
                return "For " + (hours + 1) + " Hours";
            }
            return "For " + hours + " Hours";
        } else if (minutes > 0) {
            if (seconds % 60 > 30) {
                return "For " + (minutes + 1) + " Mins.";
            }
            return "For " + minutes + " Mins.";
        } else {
            return "Just now";
        }
    }

    public String getLastSeen(Date now, Date previous) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        int numofYears = 0;
        long numOfMonths = diff / (2592000000l);
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60));
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        if (numofYears > 0) {
            return "For " + (numofYears + 1) + " Year";
        } else if (numOfMonths > 0) {
            if (numOfDays % numOfMonths > 15) {
                return "For " + (numOfMonths + 1) + " Month";
            }
            return "For " + numOfMonths + " Month";
        } else if (numOfDays > 0) {
            if (hours % 24 > 12) {
                return "For " + (numOfDays + 1) + " Day";
            }
            return "For " + numOfDays + " Day";
        } else if (hours > 0) {
            if (minutes % 60 > 30) {
                return "For " + (hours + 1) + " Hours";
            }
            return "For " + hours + " Hours";
        } else if (minutes > 0) {
            if (seconds % 60 > 30) {
                return "For " + (minutes + 1) + " Mins.";
            }
            return "For " + minutes + " Mins.";
        } else {
            return "Just now";
        }
    }

    public int getDiffYear(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff / 31556952000L);
    }

    public int getDiffMonth(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff / 2592000000L);
    }

    public int getDiffDay(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    public int getDiffHours(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff / (60 * 60 * 1000));
    }

    public int getDiffMinutes(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff / (60 * 1000));
    }

    public long getDiffSeconds(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff / 1000);
    }

    public long getDiffMilliseconds(Date previous, Date now) {
        long diff = Math.abs(now.getTime() - previous.getTime());
        return (int) (diff);
    }

    private void set12HorsFormat(boolean containsAmOrPm) {
        if (containsAmOrPm) {
            mDateFormat = new SimpleDateFormat(FORMAT_TYPE_12_HRS);
        }
    }

    private void getDateString(int num, String dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        if (dateType.equals(TYPE_DAY)) {
            calendar.add(Calendar.DAY_OF_MONTH, num);
        } else if (dateType.equals(TYPE_MONTH)) {
            calendar.add(Calendar.MONTH, num);
        } else if (dateType.equals(TYPE_YEAR)) {
            calendar.add(Calendar.YEAR, num);
        } else if (dateType.equals(TYPE_HOUR)) {
            calendar.add(Calendar.HOUR, num);
        } else if (dateType.equals(TYPE_MIN)) {
            calendar.add(Calendar.MINUTE, num);
        } else {
            calendar.add(Calendar.SECOND, num);
        }
        mDate = new Date(calendar.getTimeInMillis());
    }

    public String getString() {
        return mDateFormat.format(mDate);
    }
}
