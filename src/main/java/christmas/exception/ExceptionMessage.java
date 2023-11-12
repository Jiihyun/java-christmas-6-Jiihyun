package christmas.exception;

public enum ExceptionMessage {
    INPUT_DAY_FORMAT("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INPUT_ORDER_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR]";
    private final String message;

    ExceptionMessage(String message) {
        this.message = String.format("%s %s", PREFIX, message);
    }

    public ProgramException getException() {
        return new ProgramException(this.message);
    }
}
