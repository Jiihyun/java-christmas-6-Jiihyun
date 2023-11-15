package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.constants.MenuCategory;
import christmas.domain.discount.DiscountInfo;

import java.time.DayOfWeek;

import static christmas.domain.discount.constants.DiscountAmountRule.WEEKEND_DISCOUNT;
import static christmas.domain.discount.constants.DiscountCategory.WEEKEND;

public class Weekend implements DiscountStrategy {
    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {
        int totalWeekendDiscountAmount = 0;

        int weekendDiscountQuantity = orderItems.getOrderItems()
                .stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.MAIN))
                .sum();

        totalWeekendDiscountAmount -= WEEKEND_DISCOUNT.getValue() * weekendDiscountQuantity;
        return new DiscountInfo(WEEKEND, totalWeekendDiscountAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        boolean isWeekend = (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);
        boolean hasMain = orderItems.getOrderItems()
                .stream()
                .anyMatch(orderItem -> orderItem.getMenu().getMenuCategory() == MenuCategory.MAIN);
        return isWeekend && hasMain;
    }
}
