package com.javaee.javaee2022teamnine.enums;

import java.util.Random;

public enum TimesheetFrequency {
    WEEKLY,
    MONTHLY;

    public static TimesheetFrequency generateRandomTf() {
        TimesheetFrequency[] values = TimesheetFrequency.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }
}
