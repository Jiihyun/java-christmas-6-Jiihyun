package christmas.view.constants;

public enum PrintMessage {
    /*
    InputView
     */
    INPUT_START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_VISIT_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU_AND_QUANTITY("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),

    /*
    OutputView
     */
    OUTPUT_BENEFIT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    OUTPUT_ORDERED_MENU("\n<주문 메뉴>"),
    OUTPUT_TOTAL_AMOUNT_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>"),
    OUTPUT_GIFT_MENU("\n<증정 메뉴>"),
    OUTPUT_CHAMPAGNE("샴페인 1개"),
    OUTPUT_NONE("없음"),
    OUTPUT_BENEFIT_DETAILS("\n<혜택 내역>"),
    OUTPUT_TOTAL_BENEFIT_AMOUNT("\n<총혜택 금액>"),
    OUTPUT_EXPECTED_AMOUNT_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>"),
    OUTPUT_DECEMBER_EVENT_BADGE("\n<12월 이벤트 배지>");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
