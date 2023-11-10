package christmas.view;

import christmas.domain.Day;
import christmas.exception.ExceptionMessage;
import christmas.io.reader.Reader;
import christmas.io.writer.Writer;
import christmas.converter.Converter;

import java.util.Map;

public class InputView {
    private static final String INPUT_VISIT_DAY = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU_AND_QUANTITY = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final Writer writer;
    private final Reader reader;

    public InputView(Writer writer, Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public Day getVisitDay() {
        //TODO: 재귀에 시도 횟수 제한 걸어주기(스택오버플로우를 막자!!)
        writer.writeln(INPUT_VISIT_DAY);
        try {
            String input = reader.readLine();
            int day = Converter.convertToInt(input, ExceptionMessage.INPUT_DAY_FORMAT);
            return new Day(day);
        } catch (IllegalArgumentException exception) {
            writer.writeln(exception.getMessage());
            return getVisitDay();
        }
    }

    public Map<String, Integer> getMenuAndQuantity() {
        writer.writeln(INPUT_MENU_AND_QUANTITY);
        try {
            String input = reader.readLine();
            return Converter.convertToMap(input, ExceptionMessage.INPUT_ORDER_FORMAT);
        } catch (IllegalArgumentException exception) {
            writer.writeln(exception.getMessage());
            return getMenuAndQuantity();
        }
    }
}
