package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;

public interface DiscountStrategy {
    DiscountInfo applyDiscount(OrderItems orderItems);

    boolean isApplicable(Day day, OrderItems orderItems);
}
