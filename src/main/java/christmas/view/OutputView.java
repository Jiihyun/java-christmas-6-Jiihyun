package christmas.view;

import christmas.domain.Badge;
import christmas.domain.CustomerOrder;
import christmas.domain.Day;
import christmas.domain.discount.DiscountInfos;
import christmas.domain.dto.output.OrderItemResponse;
import christmas.domain.dto.output.OrderItemsResponse;
import christmas.io.writer.Writer;

import java.util.Map;

public class OutputView {
    //TODO: 리팩토링 필요 (상수 정리 및 문자 포맷 형식 지정, 메서드 다듬기)
    private static final int DECEMBER = 12;
    private static final String COUNT_UNIT = "개";
    private static final String WHITE_SPACE = System.lineSeparator();
    private static final String MONEY_FORMAT = "%,d원" + WHITE_SPACE;
    private static final String BENEFIT_DETAIL_FORMAT = "%s: " + MONEY_FORMAT;
    private static final String BENEFIT_PREVIEW_MSG = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + WHITE_SPACE;
    private static final String ORDERED_MENU_MSG = "<주문 메뉴>" + WHITE_SPACE;
    private static final String TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MSG = WHITE_SPACE + "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_MSG = "<혜택 내역>" + WHITE_SPACE;
    private static final String TOTAL_BENEFIT_AMOUNT_MSG = "<총혜택 금액>";
    private static final String EXPECTED_AMOUNT_AFTER_DISCOUNT_MSG = WHITE_SPACE + "<할인 후 예상 결제 금액>";
    private static final String DECEMBER_EVENT_BADGE_MSG = WHITE_SPACE + "<12월 이벤트 배지>" + WHITE_SPACE;
    private static final String ORDERED_MENU_FORMAT = "%s %d%s" + WHITE_SPACE;
    private static final String CHAMPAIGE = "샴페인 1개" + WHITE_SPACE;
    private static final String NONE = "없음" + WHITE_SPACE;
    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    public void printBenefitPreviewMsg(Day day) {
        writer.writef(BENEFIT_PREVIEW_MSG, DECEMBER, day.getDay());
    }

    public void printOrderedMenu(OrderItemsResponse orderItemsResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append(WHITE_SPACE)
                .append(ORDERED_MENU_MSG);
        for (OrderItemResponse orderItemResponse : orderItemsResponse.orderItemsResponse()) {
            sb.append(String.format(ORDERED_MENU_FORMAT, orderItemResponse.menuName(), orderItemResponse.quantity(), COUNT_UNIT));
        }
        writer.writeln(sb.toString());
    }

    public void printAmountBeforeDiscount(CustomerOrder customerOrder) {
        writer.writeln(TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG);
        writer.writef(MONEY_FORMAT, customerOrder.getTotalPurchasedAmount());
    }

    public void printFreeGift(DiscountInfos discountInfos) {
        boolean hasFreeGift = discountInfos.hasFreeGift();
        writer.writeln(GIFT_MENU_MSG);

        if (hasFreeGift) {
            writer.writeln(CHAMPAIGE);
        }
        if (!hasFreeGift) {
            writer.writeln(NONE);
        }
    }

    public void printBenefitDetails(DiscountInfos discountInfos) {
        StringBuilder sb = new StringBuilder();
        sb.append(BENEFIT_DETAILS_MSG);

        if (discountInfos.size() == 0) {
            sb.append(NONE);
        }
        Map<String, Integer> nameAndAmount = discountInfos.getNameAndAmount();
        for (Map.Entry<String, Integer> entry : nameAndAmount.entrySet()) {
            sb.append(String.format(BENEFIT_DETAIL_FORMAT, entry.getKey(), entry.getValue()));
        }
        writer.writeln(sb.toString());
    }

    public void printBenefitAmount(DiscountInfos discountInfos) {
        writer.writeln(TOTAL_BENEFIT_AMOUNT_MSG);
        writer.writef(MONEY_FORMAT, discountInfos.getTotalBenefitAmount());
    }

    public void printExpectedAmount(CustomerOrder customerOrder, DiscountInfos discountInfos) {
        int expectedAmountAfterDiscount = customerOrder.getTotalPurchasedAmount() + discountInfos.getTotalBenefitAmountExceptGift();
        //getTotalBenefitAmountExceptGift는 마이너스라 더해줘야함
        writer.writeln(EXPECTED_AMOUNT_AFTER_DISCOUNT_MSG);
        writer.writef(MONEY_FORMAT, expectedAmountAfterDiscount);
    }

    public void printBadge(DiscountInfos discountInfos) {
        writer.writeln(DECEMBER_EVENT_BADGE_MSG + Badge.getBadge(discountInfos).getName());
    }
}


