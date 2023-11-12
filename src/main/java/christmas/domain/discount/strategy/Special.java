package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountCategory;
import christmas.domain.discount.DiscountInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.domain.discount.DiscountAmountRule.SPECIAL_DISCOUNT;
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
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        int orderDate = day.getDay();
        return orderDate == CHRISTMAS_DAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private DayOfWeek getDayOfWeek(Day day) {
        LocalDate localDate = day.toLocalDate();
        return localDate.getDayOfWeek();
    }
}
