package com.javaee.javaee2022teamnine.util;

import com.google.common.collect.Lists;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateService {
    public java.sql.Date add2YearsToDate() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // add 2 years to current date
        c.add(Calendar.YEAR, 2);

        return java.sql.Date.valueOf(dateFormat.format(c.getTime()));
    }

    public java.sql.Date dateToday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        dateFormat.format(currentDate);
        return java.sql.Date.valueOf(dateFormat.format(currentDate));
    }

    public Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public double countMonthsBetweenDates(Calendar beginDate, Calendar endDate) {
        int yearsInBetween = beginDate.get(Calendar.YEAR) - endDate.get(Calendar.YEAR);
        int monthsDiff = beginDate.get(Calendar.MONTH) - endDate.get(Calendar.MONTH);
        return yearsInBetween * 12 + monthsDiff;
    }

    public int getHolidays(Calendar beginDate, Calendar endDate) {
        return getDaysBetween(beginDate, endDate)
                - getWorkingDay(beginDate, endDate);
    }

    public int getDaysBetween(Calendar beginDate, Calendar endDate) {
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int days = endDate.get(Calendar.DAY_OF_YEAR) - beginDate.get(Calendar.DAY_OF_YEAR) + 1;

        int year = endDate.get(Calendar.YEAR);

        if (beginDate.get(Calendar.YEAR) != year) {
            beginDate = (Calendar) beginDate.clone();

            do {
                days += beginDate.getActualMaximum(Calendar.DAY_OF_YEAR);

                beginDate.add(Calendar.YEAR, 1);

            } while (beginDate.get(Calendar.YEAR) != year);
        }
        return days;
    }

    public int getWorkingDay(Calendar beginDate, Calendar endDate) {
        int result = -1;
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int charge_start_date = 0;
        int charge_end_date = 0;
        int stmp;
        int etmp;
        stmp = 7 - beginDate.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - endDate.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {
            charge_end_date = etmp - 1;
        }
        result = (getDaysBetween(getNextMonday(beginDate),
                getNextMonday(endDate)) / 7)
                * 5
                + charge_start_date
                - charge_end_date;
        return result;
    }

    public Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            String result = dateFormat.format(calendar.getTime());
            dates.add(java.sql.Date.valueOf(result));

            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
    public List<Date> getDaysBetweenDatesForTimeSheet(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            String result = dateFormat.format(calendar.getTime());
            dates.add(java.sql.Date.valueOf(result));

            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static boolean isWeekendSql(java.sql.Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);

        return (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public static boolean isMondaySQL(java.sql.Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);

        return (c1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY);
    }

    public static boolean isFridaySQL(java.sql.Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);

        return (c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
    }

    public static List<List<java.sql.Date>> datesForStartAndEndOfWeek(java.sql.Date start, java.sql.Date end) {
        List<java.sql.Date> localToSQLDate = new ArrayList<>();
        List<java.sql.Date> dateSQLList = new ArrayList<>();

        for (Date date : getDaysBetweenDates(start, end)) {
            localToSQLDate.add(java.sql.Date.valueOf(String.valueOf(date)));

        }
        System.out.println("--sql dates---" + localToSQLDate);

        for (java.sql.Date d : localToSQLDate) {
            if (!isWeekendSql(d))
                if (isMondaySQL(d) || isFridaySQL(d)) {
                    dateSQLList.add(d);
                }
            if(isFridaySQL(dateSQLList.get(0)))
                dateSQLList.remove(0);

        }
        return Lists.partition(dateSQLList, 2);
    }

}
