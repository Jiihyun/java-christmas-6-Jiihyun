package christmas.domain;

import christmas.domain.discount.DiscountManager;

public class CustomerOrderInfo {
    private final Day day;
    private final OrderItems orderItems;

    public CustomerOrderInfo(Day day, OrderItems menusItems) {
        this.day = day;
        this.orderItems = menusItems;
    }

    public void applyDiscountEvent() {
        DiscountManager discountManager = new DiscountManager(
                day,
                orderItems.getMenuItems(),
                orderItems.getTotalPurchasedAmount()
        );
        discountManager.discount();
    }
}
