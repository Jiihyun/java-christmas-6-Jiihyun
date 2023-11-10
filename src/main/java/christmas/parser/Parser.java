package christmas.parser;

import christmas.exception.ExceptionMessage;

public class Parser {
    public static int parseStrToInt(String input) {
        try {
            validateBlank(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw ExceptionMessage.INPUT_DAY_FORMAT.getException();
        }
    }

    private static void validateBlank(String inputValue) {
        if (inputValue == null || inputValue.isBlank()) {
            throw ExceptionMessage.INPUT_BLANK.getException();
        }
    }
}
