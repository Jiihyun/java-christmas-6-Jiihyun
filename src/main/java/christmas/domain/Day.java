package christmas.domain;

import christmas.validator.DayValidator;

public class Day {
    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 31;
    private final int day;

    public Day(int day) {
        DayValidator.validateDayRange(day);
        this.day = day;
    }
}
