package christmas.domain;

import christmas.domain.discount.DiscountManager;

public class CustomerOrderInfo {
    private static final int MINIMUM_TOTAL_PURCHASED_AMOUNT = 120_000;
    private final Day day;
    private final OrderItems orderItems;
    private final boolean hasChampagne = isTotalPurchasedAmountAboveMinimum();
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
}
