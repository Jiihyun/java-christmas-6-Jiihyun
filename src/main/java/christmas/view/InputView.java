package christmas.view;

import christmas.converter.Converter;
import christmas.domain.CustomerOrder;
import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.exception.ExceptionMessage;
import christmas.io.reader.Reader;
import christmas.io.writer.Writer;

import java.util.Map;

import static christmas.view.constants.PrintMessage.*;

public class InputView {
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
        return new CustomerOrder(visitDay, orderItems);
    }

    public Day readVisitDay() {
        //TODO: 재귀에 시도 횟수 제한 걸어주기(스택오버플로우를 막자!!), 콘솔 다 쓰고 close 해주기
        writer.writeln(INPUT_VISIT_DAY.getMessage());
        try {
            String input = reader.readLine();
            int day = Converter.convertToInt(input, ExceptionMessage.INPUT_DAY_FORMAT);
            return new Day(day);
        } catch (IllegalArgumentException exception) {
            writer.writeln(exception.getMessage());
            return readVisitDay();
        }
    }

    public OrderItems readMenuItems() {
        writer.writeln(INPUT_MENU_AND_QUANTITY.getMessage());
        try {
            String input = reader.readLine();
            Map<String, Integer> menuNamesAndQuantities = Converter.convertToMap(input, ExceptionMessage.INPUT_ORDER_FORMAT);
            return OrderItems.from(menuNamesAndQuantities);
        } catch (IllegalArgumentException exception) {
            writer.writeln(exception.getMessage());
            return readMenuItems();
        }
    }
}
