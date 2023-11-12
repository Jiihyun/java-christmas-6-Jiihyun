package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.MenuCategory;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountCategory;
import christmas.domain.discount.DiscountInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.domain.discount.DiscountAmountRule.WEEKDAY_DISCOUNT;

public class Weekday implements DiscountStrategy {
    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {
        int totalWeekdayDiscountAmount = 0;

        int weekdayDiscountQuantity = orderItems.getMenuItems()
                .stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.DESSERT))
                .sum();
        totalWeekdayDiscountAmount -= WEEKDAY_DISCOUNT.getValue() * weekdayDiscountQuantity;

        return new DiscountInfo(DiscountCategory.WEEKDAY, totalWeekdayDiscountAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    private DayOfWeek getDayOfWeek(Day day) {
        LocalDate localDate = day.toLocalDate();
        return localDate.getDayOfWeek();
    }
}
