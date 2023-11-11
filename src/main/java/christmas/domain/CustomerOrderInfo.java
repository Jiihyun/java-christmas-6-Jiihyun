package christmas.domain;

import christmas.domain.discount.DiscountManager;

import static christmas.domain.discount.DisCountAmountRule.PRICE_OF_CHAMPAIGE;

public class CustomerOrderInfo {
    private static final int MINIMUM_TOTAL_PURCHASED_AMOUNT = 120_000;
    private final Day day;
    private final OrderItems orderItems;
    private final boolean hasChampagne = isTotalPurchasedAmountAboveMinimum();
    private final int totalBenefitAmount = getTotalBenefitAmount();
    private final int totalDiscountAmount = getTotalDiscountAmount();


    public CustomerOrderInfo(Day day, OrderItems orderItems) {
        this.day = day;
        this.orderItems = orderItems;
    }

    public boolean isTotalPurchasedAmountAboveMinimum() {
        return orderItems.getTotalPurchasedAmount() >= MINIMUM_TOTAL_PURCHASED_AMOUNT;
    }

    public int getTotalDiscountAmount() {
        DiscountManager discountManager = new DiscountManager(
                day,
                orderItems.getMenuItems(),
                orderItems.getTotalPurchasedAmount()
        );
        discountManager.discount();
        return discountManager.getTotalDiscountAmount();
    }

    public int getTotalBenefitAmount() {
        int sum = getTotalDiscountAmount();
        if (hasChampagne) {
            sum += PRICE_OF_CHAMPAIGE.value;
        }
        return sum;
    }
}
