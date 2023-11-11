package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItem;

import java.util.List;

public class DiscountManager {
    private static final int MINIMUM_PURCHASE_AMOUNT = 10000;
    private final Day day;
    private final List<OrderItem> orderItems;
    private final int totalPurchaseAmount;
    private final DiscountAmount discountAmount = new DiscountAmount();


    public DiscountManager(Day day, List<OrderItem> orderItems, int totalPurchaseAmount) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public void discount() {
        if (totalPurchaseAmount < MINIMUM_PURCHASE_AMOUNT) {
        }
    }
}
