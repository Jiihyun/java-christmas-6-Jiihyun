package christmas.domain;

import christmas.domain.discount.DiscountInfos;
import christmas.domain.discount.Discounter;

public class CustomerOrder {
    private final Day day;
    private final OrderItems orderItems;
    private final int totalPurchasedAmount;
    private DiscountInfos discountInfos;

    public CustomerOrder(Day day, OrderItems orderItems) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPurchasedAmount = orderItems.getTotalPurchasedAmount();
    }

    public void discount() {
        Discounter discounter = new Discounter(day, orderItems, totalPurchasedAmount);
        this.discountInfos = discounter.applyDiscount();
    }

    public Day getDay() {
        return day;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public int getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public DiscountInfos getDiscountInfos() {
        return discountInfos;
    }
}
