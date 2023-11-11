package christmas.domain;

import christmas.validator.DayValidator;

import java.time.LocalDate;

public class Day {
    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 31;
    private final int day;

    public Day(int day) {
        DayValidator.validateDayRange(day);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public LocalDate toLocalDate() {
        return LocalDate.of(2023, 12, day);
    }
}
