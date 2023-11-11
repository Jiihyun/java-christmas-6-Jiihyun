package christmas.domain.discount;

import christmas.domain.MenuCategory;
import christmas.domain.OrderItem;

import java.util.List;

import static christmas.domain.discount.DisCountAmountRule.*;

public class DiscountAmount {
    private int totalChristmasDDayDiscountAmount = 0;
    private int totalWeekdayDiscountAmount = 0;
    private int totalWeekendDiscountAmount = 0;
    private int totalSpecialDiscountPriceAmount = 0;

    public void applyChristmasDDayDiscount(int orderDate) {
        int increasedAmountPerDay = 100;
        int ChristmasDDayDiscountAmount = CHRISTMAS_MINIMUM_DISCOUNT.value
                + (increasedAmountPerDay * (orderDate - 1));
        totalChristmasDDayDiscountAmount += ChristmasDDayDiscountAmount;
    }

    public void applyWeekdayDiscount(List<OrderItem> orderItems) {
        long weekdayDiscountAmount = orderItems.stream()
                .filter(orderItem -> orderItem.isSameCategoryAs(MenuCategory.DESSERT))
                .count();
        totalWeekdayDiscountAmount += WEEKDAY_DISCOUNT.value * weekdayDiscountAmount;
    }

    public void applyWeekendDiscount(List<OrderItem> orderItems) {
        long weekendDiscountAmount = orderItems.stream()
                .filter(orderItem -> orderItem.isSameCategoryAs(MenuCategory.MAIN))
                .count();
        totalWeekendDiscountAmount += WEEKEND_DISCOUNT.value * weekendDiscountAmount;
    }

    public void applySpecialDiscount() {
        totalSpecialDiscountPriceAmount += SPECIAL_DISCOUNT.value;
    }

    public int getTotalDiscountAmount() {
        return totalChristmasDDayDiscountAmount
                + totalWeekdayDiscountAmount
                + totalWeekendDiscountAmount
                + totalSpecialDiscountPriceAmount;
    }
}
