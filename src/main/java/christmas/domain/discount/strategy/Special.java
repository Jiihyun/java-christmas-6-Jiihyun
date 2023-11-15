package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;
import christmas.domain.discount.constants.DiscountCategory;

import java.time.DayOfWeek;

import static christmas.domain.discount.constants.DiscountAmountRule.SPECIAL_DISCOUNT;
import static christmas.domain.discount.strategy.ChristmasDDay.CHRISTMAS_DAY;

public class Special implements DiscountStrategy {
    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {
        int totalSpecialDiscountAmount = 0;

        totalSpecialDiscountAmount -= SPECIAL_DISCOUNT.getValue();
        return new DiscountInfo(DiscountCategory.SPECIAL, totalSpecialDiscountAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        int orderDate = day.toInt();
        return orderDate == CHRISTMAS_DAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
