package christmas.view;

import christmas.domain.Day;
import christmas.io.reader.Reader;
import christmas.io.writer.Writer;
import christmas.parser.Parser;

public class InputView {
    private static final String INPUT_VISIT_DAY = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

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
            int day = Parser.parseStrToInt(input);
            return new Day(day);
        } catch (IllegalArgumentException exception) {
            writer.writeln(exception.getMessage());
            return getVisitDay();
        }
    }
}
