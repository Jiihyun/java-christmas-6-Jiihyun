package christmas.view;

import christmas.domain.OrderItem;
import christmas.domain.dto.output.CustomerOrderResponse;
import christmas.io.writer.Writer;

import java.util.Map;

import static christmas.view.constants.PrintFormat.*;
import static christmas.view.constants.PrintMessage.*;

public class OutputView {
    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    public void printCustomerOrder(CustomerOrderResponse customerOrderResponse) {
        getBenefitPreviewMsg(customerOrderResponse);
        getOrderedMenu(customerOrderResponse);
        getAmountBeforeDiscount(customerOrderResponse);
        getChampagneOrNot(customerOrderResponse);
        getBenefitDetails(customerOrderResponse);
        getBenefitAmount(customerOrderResponse);
        getExpectedAmount(customerOrderResponse);
        getBadge(customerOrderResponse);
    }

    private void getBenefitPreviewMsg(CustomerOrderResponse customerOrderResponse) {
        writer.writef(OUTPUT_BENEFIT_PREVIEW.getMessage(), customerOrderResponse.getVisitDay());
    }

    private void getOrderedMenu(CustomerOrderResponse customerOrderResponse) {
        writer.writeln(OUTPUT_ORDERED_MENU.getMessage());
        for (OrderItem orderItem : customerOrderResponse.getOrderItems()) {
            writer.writef(ORDERED_MENU_FORMAT.getFormat(),
                    orderItem.getMenu().getName(),
                    orderItem.getQuantity(),
                    COUNT_UNIT.getFormat());
        }
    }

    private void getAmountBeforeDiscount(CustomerOrderResponse customerOrderResponse) {
        writer.writeln(OUTPUT_TOTAL_AMOUNT_BEFORE_DISCOUNT.getMessage());
        writer.writef(MONEY_FORMAT.getFormat(), customerOrderResponse.getTotalPurchasedAmount());
    }

    private void getChampagneOrNot(CustomerOrderResponse customerOrderResponse) {
        boolean hasGift = customerOrderResponse.hasGift();

        writer.writeln(OUTPUT_GIFT_MENU.getMessage());
        if (hasGift) {
            writer.writeln(OUTPUT_CHAMPAGNE.getMessage());
        }
        if (!hasGift) {
            writer.writeln(OUTPUT_NONE.getMessage());
        }
    }

    private void getBenefitDetails(CustomerOrderResponse customerOrderResponse) {
        Map<String, Integer> benefitDetails = customerOrderResponse.getBenefitDetails();

        writer.writeln(OUTPUT_BENEFIT_DETAILS.getMessage());
        if (benefitDetails.size() == 0) {
            writer.writeln(OUTPUT_NONE.getMessage());
        }
        for (Map.Entry<String, Integer> eventNameAndDiscountAmount : benefitDetails.entrySet()) {
            writer.writef(BENEFIT_DETAIL_FORMAT.getFormat(), eventNameAndDiscountAmount.getKey());
            writer.writef(MONEY_FORMAT.getFormat(), eventNameAndDiscountAmount.getValue());
        }
    }

    private void getBenefitAmount(CustomerOrderResponse customerOrderResponse) {
        writer.writeln(OUTPUT_TOTAL_BENEFIT_AMOUNT.getMessage());
        writer.writef(MONEY_FORMAT.getFormat(), customerOrderResponse.getTotalBenefitAmount());
    }

    private void getExpectedAmount(CustomerOrderResponse customerOrderResponse) {
        writer.writeln(OUTPUT_EXPECTED_AMOUNT_AFTER_DISCOUNT.getMessage());
        int expectedAmountAfterDiscount = customerOrderResponse.getExpectedAmount();
        writer.writef(MONEY_FORMAT.getFormat(), expectedAmountAfterDiscount);
    }

    private void getBadge(CustomerOrderResponse customerOrderResponse) {
        writer.writeln(OUTPUT_DECEMBER_EVENT_BADGE.getMessage());
        writer.writeln(customerOrderResponse.getBadge().getName());
    }
}


