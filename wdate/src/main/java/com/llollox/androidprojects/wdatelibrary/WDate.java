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

    public WDate setDate(Date date) {
        calendar.setTime(date);
        return this;
    }








    // **********************************************
    // COMPARATORS
    // **********************************************

    /**
     *
     * @param date The date used for the comparison
     * @return the number of days between
     * this wdate object's date and the one passed
     * as argument.
     *
     * The value returned is an absolute value, therefore
     * even if the date to be compared is in the past
     * the value returned is always positive.
     *
     * The wdate object's date is included, the
     * compared one not.
     *
     */
    public int getNumDaysBetween(Date date) {
        return Math.abs((int) TimeUnit.DAYS.convert(getDifference(date), TimeUnit.MILLISECONDS));
    }

    /**
     *
     * @param date The date used for the comparison
     * @return the number of months between
     * this wdate object's date and the one passed
     * as argument.
     *
     * The value returned is an absolute value, therefore
     * even if the date to be compared is in the past
     * the value returned is always positive.
     *
     * The wdate object's date is included, the
     * compared one not.
     *
     */
    public int getNumMonthsBetween(Date date) {
        return getNumYearsBetween(date) * 12 + Math.abs(getMonth() - new WDate(date).getMonth());
    }

    /**
     *
     * @param date The date used for the comparison
     * @return the number of years between
     * this wdate object's date and the one passed
     * as argument.
     *
     * The value returned is an absolute value, therefore
     * even if the date to be compared is in the past
     * the value returned is always positive.
     *
     * The wdate object's date is included, the
     * compared one not.
     *
     */

    public int getNumYearsBetween(Date date) {
        return Math.abs(getYear() - new WDate(date).getYear());
    }

    /**
     *
     * @param date The date used for the comparison
     * @return the number milliseconds between
     * this wdate object's date and the one passed
     * as argument.
     *
     */

    public long getDifference(Date date) {
        return calendar.getTime().getTime() - date.getTime();
    }


    /**
     *
     * @param start The start date of the range
     * @param end The end date of the range
     * @return if the wdate object's date is strictly contained
     * in the period between the start and end dates.
     * Therefore both start and end limits are excluded!
     *
     */

    public boolean isInRange(Date start, Date end) {
        return start.before(calendar.getTime()) && end.after(calendar.getTime());
    }








    // **********************************************
    // CONVERTERS
    // **********************************************

    /**
     *
     * @return the date setting its time to 00:00:00.000
     *
     */

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
        return isSameDay(new Date());
    }

    public boolean isTomorrow() {
        Calendar tomorrowCalendar = Calendar.getInstance();
        tomorrowCalendar.add(Calendar.DATE, 1);
        return isSameDay(tomorrowCalendar.getTime());
    }

    public boolean isYesterday() {
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DATE, -1);
        return isSameDay(yesterdayCalendar.getTime());
    }

    /**
     *
     * @param date The date used for the comparison
     * @return if the wdate's date and the one passed as argument
     * have the same day, month and year.
     *
     */

    private boolean isSameDay(Date date) {
        WDate wDate = new WDate(date);
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

    /**
     *
     * @param sdfPattern The pattern used by SimpleDateFormat
     * @return a string that represent the date formatted
     *
     */

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
