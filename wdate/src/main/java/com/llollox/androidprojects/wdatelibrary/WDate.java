package com.llollox.androidprojects.wdatelibrary;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by lorenzorigato on 11/01/2017.
 */

public class WDate {

    public enum WeekStart {
        SUNDAY, MONDAY
    }

    private Calendar calendar;
    private WeekStart weekStart = WeekStart.MONDAY;


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

    public Date getDate() {
        return calendar.getTime();
    }

    public WDate setWeekStart (WeekStart weekStart) {
        this.weekStart = weekStart;
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
        return Math.abs((int) TimeUnit.DAYS.convert(getMilliSecondsDifference(date), TimeUnit.MILLISECONDS));
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

    public long getMilliSecondsDifference(Date date) {
        return Math.abs(calendar.getTime().getTime() - date.getTime());
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

    public boolean isFuture() {
        return calendar.getTime().getTime() > new Date().getTime();
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
    // DATE MODIFIERS
    // **********************************************


    public WDate addDays (int numDays) {
        calendar.add(Calendar.DAY_OF_MONTH, numDays);
        return this;
    }

    public WDate addMonths (int numMonths) {
        calendar.add(Calendar.MONTH, numMonths);
        return this;
    }

    public WDate addYears (int numYears) {
        calendar.add(Calendar.YEAR, numYears);
        return this;
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
    // DATE SETTERS
    // **********************************************


    public WDate setDay (int day) {
        if (inRangeLimitsIncluded(1, 31, day)) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }
        return this;
    }

    public WDate setMonth (int month) {
        if (inRangeLimitsIncluded(0, 11, month)) {
            calendar.set(Calendar.MONTH, month);
        }
        return this;
    }

    public WDate setYear (int year) {
        if (year > 0) {
            calendar.set(Calendar.YEAR, year);
        }
        return this;
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

    public String format (String sdfPattern, Locale locale) {
        return new SimpleDateFormat(sdfPattern, locale).format(calendar.getTime());
    }

    public String getRelativeTimeSpan () {
        return getRelativeTimeSpan(new Date());
    }

    public String getRelativeTimeSpan (Date dueDate) {
        return (String) DateUtils.getRelativeTimeSpanString(
                getDate().getTime(),
                dueDate.getTime(),
                0L,
                DateUtils.FORMAT_ABBREV_ALL);
    }






    // **********************************************
    // PERIOD GETTERS
    // **********************************************



    public Date getFirstDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());
        cal.set(Calendar.DAY_OF_WEEK, 1);
        if (weekStart == WeekStart.MONDAY) {
            cal.add(Calendar.DATE, 1);
        }

        return cal.getTime();
    }

    public Date getLastDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());
        cal.set(Calendar.DAY_OF_WEEK, 7);

        if (weekStart == WeekStart.MONDAY) {
            cal.add(Calendar.DATE, 1);
        }

        return cal.getTime();
    }

    public Date getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public Date getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getFirstDayOfMonth());
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public Date getFirstDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return cal.getTime();
    }

    public Date getLastDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getFirstDayOfYear());
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }






    // **********************************************
    // RANDOM
    // **********************************************

    public Date getSample() {
        int diffDays = ((int)(Math.random() * 10000)) - 5000;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, diffDays);
        return cal.getTime();
    }

    public Date getSample(Date date, int numDaysBefore, int numDaysAfter) {
        int diffDays = ((int)(Math.random() * numDaysAfter + numDaysBefore)) - numDaysBefore;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, diffDays);
        return cal.getTime();
    }






    // **********************************************
    // TIME GETTERS
    // **********************************************

    public int getHours() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMilliSeconds() {
        return calendar.get(Calendar.MILLISECOND);
    }

    public int getMinutes() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSeconds() {
        return calendar.get(Calendar.SECOND);
    }

    public long getMilliSecondsFromEpoch() {
        return calendar.getTime().getTime();
    }






    // **********************************************
    // TIME MODIFIERS
    // **********************************************


    public WDate addHours (int numHours) {
        calendar.add(Calendar.HOUR_OF_DAY, numHours);
        return this;
    }

    public WDate addMinutes (int numMinutes) {
        calendar.add(Calendar.MINUTE, numMinutes);
        return this;
    }

    public WDate addSeconds (int numSeconds) {
        calendar.add(Calendar.SECOND, numSeconds);
        return this;
    }

    public WDate addMilliSeconds (int numMilliSeconds) {
        calendar.add(Calendar.MILLISECOND, numMilliSeconds);
        return this;
    }








    // **********************************************
    // TIME SETTERS
    // **********************************************

    public WDate setHours(int hours) {
        if (inRangeLimitsIncluded(0, 23, hours)) {
            calendar.set(Calendar.HOUR_OF_DAY, hours);
        }
        return this;
    }

    public WDate setMilliSeconds(int milliSeconds) {
        if (inRangeLimitsIncluded(0, 999, milliSeconds)) {
            calendar.set(Calendar.MILLISECOND, milliSeconds);
        }
        return this;
    }

    public WDate setMinutes(int minutes) {
        if (inRangeLimitsIncluded(0, 59, minutes)) {
            calendar.set(Calendar.MINUTE, minutes);
        }
        return this;
    }

    public WDate setSeconds(int seconds) {
        if (inRangeLimitsIncluded(0, 59, seconds)) {
            calendar.set(Calendar.SECOND, seconds);
        }
        return this;
    }







    // **********************************************
    // PRIVATE METHODS
    // **********************************************


    private boolean inRangeLimitsIncluded(int min, int max, int value) {
        return value >= min && value <= max;
    }


}
