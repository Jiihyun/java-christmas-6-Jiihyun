package christmas.view;

import christmas.converter.Converter;
import christmas.domain.CustomerOrder;
import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.exception.ExceptionMessage;
import christmas.io.reader.Reader;
import christmas.io.writer.Writer;

import java.util.function.Function;

import static christmas.view.constants.PrintMessage.*;

public class InputView {
    private static final int MAX_RETRIES = 20;
    private final Writer writer;
    private final Reader reader;

    public InputView(Writer writer, Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public CustomerOrder readCustomerOrder() {
        writer.writeln(INPUT_START.getMessage());
        Day visitDay = readVisitDay();
        OrderItems orderItems = readMenuItems();
        reader.close();
        return new CustomerOrder(visitDay, orderItems);
    }

    private Day readVisitDay() {
        return readInput(
                INPUT_VISIT_DAY.getMessage(),
                input -> new Day(Converter.convertToInt(input, ExceptionMessage.INPUT_DAY_FORMAT)),
                MAX_RETRIES
        );
    }

    private OrderItems readMenuItems() {
        return readInput(
                INPUT_MENU_AND_QUANTITY.getMessage(),
                input -> OrderItems.from(Converter.convertToMap(input, ExceptionMessage.INPUT_ORDER_FORMAT)),
                MAX_RETRIES
        );
    }

    private <T> T readInput(String message, Function<String, T> converter, int retryCount) {
        validateRetryCount(retryCount);
        writer.writeln(message);
        try {
            String input = reader.readLine();
            return converter.apply(input);
        } catch (IllegalArgumentException exception) {
            writer.writeln(exception.getMessage());
            return readInput(message, converter, retryCount - 1);
        }
    }

    private void validateRetryCount(int retryCount) {
        if (retryCount < 0) {
            throw ExceptionMessage.INPUT_MAX_RETRIES.getException();
        }
    }
}
