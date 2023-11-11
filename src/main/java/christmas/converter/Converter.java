package christmas.converter;

import christmas.exception.ExceptionMessage;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static christmas.validator.InputValidator.validateBlank;
import static christmas.validator.InputValidator.validateMenuAndQuantityFormat;

public class Converter {
    private static final String REGEX = "[,\\-]"; // `,`와 `-`를 기준으로 split 해준다
    private static final int EVEN_LENGTH = 2;

    public static int convertToInt(String input, ExceptionMessage exceptionMessage) {
        try {
            validateBlank(input, exceptionMessage);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw exceptionMessage.getException();
        }
    }

    public static Map<String, Integer> convertToMap(String input, ExceptionMessage exceptionMessage) {
        validateBlank(input, exceptionMessage);
        validateMenuAndQuantityFormat(input);

        String[] menuQuantityPairs = input.split(REGEX);
        try {
            return getMenuNamesAndQuantities(menuQuantityPairs);
        } catch (IllegalStateException e) {
            throw exceptionMessage.getException();
        }
    }

    private static Map<String, Integer> getMenuNamesAndQuantities(String[] menuQuantityPairs) {
        return IntStream.range(0, menuQuantityPairs.length / EVEN_LENGTH)
                .boxed()
                .collect(Collectors.toMap(
                        getKeyMapper(menuQuantityPairs),
                        getValueMapper(menuQuantityPairs)));
    }

    private static Function<Integer, String> getKeyMapper(String[] menuQuantityPairs) {
        return i -> {
            int indexOfMenu = i * 2;
            return menuQuantityPairs[indexOfMenu];
        };
    }

    private static Function<Integer, Integer> getValueMapper(String[] menuQuantityPairs) {
        return i -> {
            int indexOfQuantity = i * 2 + 1;
            return convertToInt(menuQuantityPairs[indexOfQuantity], ExceptionMessage.INPUT_ORDER_FORMAT);
        };
    }

}
