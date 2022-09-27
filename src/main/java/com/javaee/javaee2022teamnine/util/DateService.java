package com.javaee.javaee2022teamnine.util;

import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

    public static List<LocalDate> getDatesBetweenUsingJava8(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println(numOfDaysBetween);

        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(startDate::plusDays)
                .collect(Collectors.toList());
    }

    public static boolean isWeekend(LocalDate ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

    public List<List<LocalDate>> datesForStartAndEndOfWeek(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> localDateList = new ArrayList<>();
        for (LocalDate date :
                getDatesBetweenUsingJava8(startDate, endDate)) {
            if (!isWeekend(date))
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    localDateList.add(date);
                }
        }
        return Lists.partition(localDateList, 2);
    }
}
