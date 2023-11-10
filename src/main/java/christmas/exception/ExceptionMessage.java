package christmas.exception;

import javax.swing.text.html.parser.Parser;

public enum ExceptionMessage {
    INPUT_BLANK("입력값이 빈 문자열 또는 공백입니다."),

    INPUT_DAY_FORMAT("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR}";
    private final String message;

    ExceptionMessage(String message) {
        this.message = String.format("%s %s", PREFIX, message);
    }

    public GameException getException() {
        return new GameException(this.message);
    }
}
