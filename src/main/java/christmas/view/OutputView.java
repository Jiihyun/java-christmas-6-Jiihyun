package christmas.view;

import christmas.domain.Day;
import christmas.domain.dto.output.OrderItemResponse;
import christmas.domain.dto.output.OrderItemsResponse;
import christmas.io.writer.Writer;

public class OutputView {
    private static final int DECEMBER = 12;
    private static final String COUNT_UNIT = "개";
    private static final String WHITE_SPACE = System.lineSeparator();
    private static final String BENEFIT_PREVIEW_MSG = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + WHITE_SPACE;
    private static final String ORDERED_MENU_MSG = "<주문 메뉴>" + WHITE_SPACE;
    private static final String ORDERED_MENU_FORMAT = "%s %d%s" + WHITE_SPACE;
    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    public void printBenefitPreviewMsg(Day day) {
        writer.writeln(String.format(BENEFIT_PREVIEW_MSG, DECEMBER, day.getDay()));
    }

    public void printOrderedMenu(OrderItemsResponse orderItemsResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append(ORDERED_MENU_MSG);
        for (OrderItemResponse orderItemResponse : orderItemsResponse.orderItemsResponse()) {
            sb.append(String.format(ORDERED_MENU_FORMAT,
                    orderItemResponse.menuName(),
                    orderItemResponse.quantity(),
                    COUNT_UNIT));
        }
        writer.writeln(sb.toString());
    }
}

