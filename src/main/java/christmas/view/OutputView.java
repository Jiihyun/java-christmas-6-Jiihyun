package christmas.view;

import christmas.domain.Badge;
import christmas.domain.CustomerOrderInfo;
import christmas.domain.Day;
import christmas.domain.dto.output.OrderItemResponse;
import christmas.domain.dto.output.OrderItemsResponse;
import christmas.io.writer.Writer;

import java.util.Map;

public class OutputView {
    private static final int DECEMBER = 12;
    private static final String COUNT_UNIT = "개";
    private static final String WHITE_SPACE = System.lineSeparator();
    private static final String MONEY_FORMAT = "%,d원" + WHITE_SPACE;
    private static final String BENEFIT_DETAIL_FORMAT = "%s: -" + MONEY_FORMAT;
    private static final String BENEFIT_PREVIEW_MSG = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + WHITE_SPACE;
    private static final String ORDERED_MENU_MSG = "<주문 메뉴>" + WHITE_SPACE;
    private static final String TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG = "<할인 전 총주문 금액>" + WHITE_SPACE;
    private static final String GIFT_MENU_MSG = "<증정 메뉴>" + WHITE_SPACE;
    private static final String BENEFIT_DETAILS_MSG = "<혜택 내역>" + WHITE_SPACE;
    private static final String TOTAL_BENEFIT_AMOUNT_MSG = "<총혜택 금액>" + WHITE_SPACE;
    private static final String EXPECTED_AMOUNT_AFTER_DISCOUNT_MSG = "<할인 후 예상 결제 금액>" + WHITE_SPACE;
    private static final String DECEMBER_EVENT_BADGE_MSG = "<12월 이벤트 배지>" + WHITE_SPACE;
    private static final String ORDERED_MENU_FORMAT = "%s %d%s" + WHITE_SPACE;
    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    public void printBenefitPreviewMsg(Day day) {
        writer.writef(BENEFIT_PREVIEW_MSG, DECEMBER, day.getDay());
    }

    public void printOrderedMenu(OrderItemsResponse orderItemsResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append(ORDERED_MENU_MSG);
        for (OrderItemResponse orderItemResponse : orderItemsResponse.orderItemsResponse()) {
            sb.append(String.format(ORDERED_MENU_FORMAT, orderItemResponse.menuName(), orderItemResponse.quantity(), COUNT_UNIT));
        }
        writer.writeln(sb.toString());
    }

    public void printBeforeAndAfterPriceWithDetail(CustomerOrderInfo customerOrderInfo) {
        writer.writeln(TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG + String.format(MONEY_FORMAT, customerOrderInfo.getTotalPurchasedAmount()));
        writer.writeln(GIFT_MENU_MSG + getGift(customerOrderInfo));
        writer.writeln(BENEFIT_DETAILS_MSG + getBenefitDetails(customerOrderInfo));
        writer.writeln(TOTAL_BENEFIT_AMOUNT_MSG + String.format(MONEY_FORMAT, -customerOrderInfo.getTotalBenefitAmount()));
        writer.writeln(EXPECTED_AMOUNT_AFTER_DISCOUNT_MSG + String.format(MONEY_FORMAT, customerOrderInfo.getExpectedPayAmount()));
        writer.writeln(DECEMBER_EVENT_BADGE_MSG + Badge.getBadge(customerOrderInfo).getName());
    }

    private String getGift(CustomerOrderInfo customerOrderInfo) {
        if (customerOrderInfo.isHasChampagne()) {
            customerOrderInfo.getDiscountAppliedEvent().put("증정이벤트", 25000);
            return "샴페인 1개\n";
        }
        return "없음\n";
    }

    private String getBenefitDetails(CustomerOrderInfo customerOrderInfo) {
        StringBuilder sb = new StringBuilder();
        if (customerOrderInfo.getTotalBenefitAmount() == 0) {
            return "없음\n";
        }
        Map<String, Integer> discountAppliedEvent = customerOrderInfo.getDiscountAppliedEvent();
        for (Map.Entry<String, Integer> eventAndDiscountAmount : discountAppliedEvent.entrySet()) {
            sb.append(String.format(BENEFIT_DETAIL_FORMAT, eventAndDiscountAmount.getKey(), eventAndDiscountAmount.getValue()));
        }
        return sb.toString();
    }
}

