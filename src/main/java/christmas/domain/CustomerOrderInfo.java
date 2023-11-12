package christmas.domain;

import christmas.domain.discount.DiscountManager;

import static christmas.domain.discount.DiscountAmountRule.MINIMUM_PURCHASE_AMOUNT_FOR_CHAMPAGNE_BENEFIT;
import static christmas.domain.discount.DiscountAmountRule.PRICE_OF_CHAMPAIGE;

public class CustomerOrderInfo {
    private final Day day;
    private final OrderItems orderItems;
    private final int totalPurchasedAmount;
    private int totalDiscountAmount = 0;
    private boolean hasChampagne = false;


    public CustomerOrderInfo(Day day, OrderItems orderItems) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPurchasedAmount = orderItems.getTotalPurchasedAmount();
    }

    public void applyDiscount() {
        DiscountManager discountManager = new DiscountManager(
                day,
                orderItems.getMenuItems(),
                totalPurchasedAmount
        );
        discountManager.discount();
        this.totalDiscountAmount = discountManager.getTotalDiscountAmount();
    }

    public int getTotalBenefitAmount() {
        int sum = totalDiscountAmount;
        if (hasChampagne) {
            sum += PRICE_OF_CHAMPAIGE.value;
        }
        return sum;
    }

    public boolean isHasChampagne() {
        boolean result = totalPurchasedAmount >= MINIMUM_PURCHASE_AMOUNT_FOR_CHAMPAGNE_BENEFIT.value;
        this.hasChampagne = result;
        return result;
    }

    public int getEstimatedPaymentAmount() {
        return totalPurchasedAmount - totalDiscountAmount;
    }

}
