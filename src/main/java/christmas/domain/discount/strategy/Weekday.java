package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.constants.MenuCategory;
import christmas.domain.discount.DiscountInfo;
import christmas.domain.discount.constants.DiscountCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.domain.discount.constants.DiscountAmountRule.WEEKDAY_DISCOUNT;

public class Weekday implements DiscountStrategy {
    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {
        int totalWeekdayDiscountAmount = 0;

        int weekdayDiscountQuantity = orderItems.getOrderItems()
                .stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.DESSERT))
                .sum();
        totalWeekdayDiscountAmount -= WEEKDAY_DISCOUNT.getValue() * weekdayDiscountQuantity;

        return new DiscountInfo(DiscountCategory.WEEKDAY, totalWeekdayDiscountAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        boolean isWeekday = (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY);
        boolean hasDessert = orderItems.getOrderItems()
                .stream()
                .anyMatch(orderItem -> orderItem.getMenu().getMenuCategory() == MenuCategory.DESSERT);
        return isWeekday && hasDessert;
    }

    private DayOfWeek getDayOfWeek(Day day) {
        LocalDate localDate = day.toLocalDate();
        return localDate.getDayOfWeek();
    }
}
