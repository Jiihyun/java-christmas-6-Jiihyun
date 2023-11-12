package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.MenuCategory;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.domain.discount.DiscountAmountRule.WEEKEND_DISCOUNT;
import static christmas.domain.discount.DiscountCategory.WEEKEND;

public class Weekend implements DiscountStrategy {
    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {
        int totalWeekendDiscountAmount = 0;

        int weekendDiscountQuantity = orderItems.getMenuItems()
                .stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.MAIN))
                .sum();

        totalWeekendDiscountAmount -= WEEKEND_DISCOUNT.getValue() * weekendDiscountQuantity;
        return new DiscountInfo(WEEKEND, totalWeekendDiscountAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private DayOfWeek getDayOfWeek(Day day) {
        LocalDate localDate = day.toLocalDate();
        return localDate.getDayOfWeek();
    }
}
