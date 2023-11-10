package christmas.validator;

import static christmas.domain.Day.END_INCLUSIVE;
import static christmas.domain.Day.START_INCLUSIVE;
import static christmas.exception.ExceptionMessage.INPUT_DAY_FORMAT;

public class DayValidator {

    private DayValidator() {
        throw new AssertionError();
    }

    public static void validateDayRange(int day) {
        if (isOutOfRange(day)) {
            throw INPUT_DAY_FORMAT.getException();
        }
    }

    private static boolean isOutOfRange(int day) {
        return day < START_INCLUSIVE || day > END_INCLUSIVE;
    }
}
