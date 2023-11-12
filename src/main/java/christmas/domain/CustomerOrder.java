package christmas.domain;

import christmas.domain.discount.DiscountInfos;
import christmas.domain.discount.strategy.DiscountStrategy;

import java.util.List;

import static christmas.domain.discount.DiscountAmountRule.MINIMUM_TOTAL_PURCHASED_AMOUNT;

public class CustomerOrder {
    private final Day day;
    private final OrderItems orderItems;
    private final int totalPurchasedAmount;
    private final List<DiscountStrategy> discountStrategies;

    public CustomerOrder(Day day, OrderItems orderItems, List<DiscountStrategy> discountStrategies) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPurchasedAmount = orderItems.getTotalPurchasedAmount();
        this.discountStrategies = discountStrategies;
    }

    public DiscountInfos applyDiscount() {
        if (totalPurchasedAmount < MINIMUM_TOTAL_PURCHASED_AMOUNT.getValue()) {
            return new DiscountInfos(List.of());
        }
        return new DiscountInfos((discountStrategies.stream()
                .filter(strategy -> strategy.isApplicable(day, orderItems))
                .map(strategy -> strategy.applyDiscount(orderItems))
                .toList()));
    }

    public int getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }
}
