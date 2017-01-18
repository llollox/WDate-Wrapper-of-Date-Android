package com.llollox.androidprojects.wdatelibrary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by lorenzorigato on 11/01/2017.
 */

public class WDate {

    private Calendar calendar;


    public WDate() {
        this.calendar = Calendar.getInstance();
    }

    public WDate(Date date) {
        this();
        calendar.setTime(date);
    }

    public WDate(long milliseconds) {
        this(new Date(milliseconds));
    }

    public WDate setDate(Date date) {
        calendar.setTime(date);
        return this;
    }








    // **********************************************
    // COMPARATORS
    // **********************************************

    public int getNumDaysBetween(Date date) {
        return Math.abs((int) TimeUnit.DAYS.convert(getDifference(date), TimeUnit.MILLISECONDS));
    }

    public int getNumMonthsBetween(Date date) {
        return getNumYearsBetween(date) * 12 + Math.abs(getMonth() - new WDate(date).getMonth());
    }

    public int getNumYearsBetween(Date date) {
        return Math.abs(getYear() - new WDate(date).getYear());
    }

    public long getDifference(Date date) {
        return calendar.getTime().getTime() - date.getTime();
    }

    public boolean isInRange(Date start, Date end) {
        return start.before(calendar.getTime()) && end.after(calendar.getTime());
    }








    // **********************************************
    // CONVERTERS
    // **********************************************

    public Date getWithoutTime () {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(calendar.getTime());
        dateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dateCalendar.set(Calendar.MINUTE, 0);
        dateCalendar.set(Calendar.SECOND, 0);
        dateCalendar.set(Calendar.MILLISECOND, 0);
        return dateCalendar.getTime();
    }








    // **********************************************
    // DAYS CHECKS
    // **********************************************


    public boolean isToday() {
        return isSameDay(new WDate());
    }

    public boolean isTomorrow() {
        Calendar tomorrowCalendar = Calendar.getInstance();
        tomorrowCalendar.add(Calendar.DATE, 1);
        return isSameDay(new WDate(tomorrowCalendar.getTime()));
    }

    public boolean isYesterday() {
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DATE, -1);
        return isSameDay(new WDate(yesterdayCalendar.getTime()));
    }

    private boolean isSameDay(WDate wDate) {
        return getDay() == wDate.getDay()
                && getMonth() == wDate.getMonth()
                && getYear() == wDate.getYear();
    }







    // **********************************************
    // DATE GETTERS
    // **********************************************


    public int getDay () {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth () {
        return calendar.get(Calendar.MONTH);
    }

    public int getYear () {
        return calendar.get(Calendar.YEAR);
    }








    // **********************************************
    // FORMATTERS
    // **********************************************

    public String format (String sdfPattern) {
        return new SimpleDateFormat(sdfPattern).format(calendar.getTime());
    }










    // **********************************************
    // TIME GETTERS
    // **********************************************

    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMillisecond() {
        return calendar.get(Calendar.MILLISECOND);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }




}
