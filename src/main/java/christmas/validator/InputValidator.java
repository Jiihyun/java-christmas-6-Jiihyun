package christmas.validator;

import christmas.exception.ExceptionMessage;

public class InputValidator {
    private static final String INPUT_MENU_AND_QUANTITY_FORMAT = "[\\uAC00-\\uD7A3]+-\\d+(,[\\uAC00-\\uD7A3]+-\\d+)*";

    private InputValidator() {
        throw new AssertionError();
    }

    public static void validateBlank(String input, ExceptionMessage exceptionMessage) {
        if (input == null || input.isBlank()) {
            throw exceptionMessage.getException();
        }
    }

    public static void validateMenuAndQuantityFormat(String input) {
        if (!input.matches(INPUT_MENU_AND_QUANTITY_FORMAT)) {
            throw ExceptionMessage.INPUT_ORDER_FORMAT.getException();
        }
    }
}
