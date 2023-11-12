package christmas.domain.discount;

import christmas.domain.MenuCategory;
import christmas.domain.OrderItem;

import java.util.List;

import static christmas.domain.discount.DiscountAmountRule.*;

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
        int weekdayDiscountQuantity = orderItems.stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.DESSERT))
                .sum();
        totalWeekdayDiscountAmount += WEEKDAY_DISCOUNT.value * weekdayDiscountQuantity;
    }

    public void applyWeekendDiscount(List<OrderItem> orderItems) {
        int weekendDiscountQuantity = orderItems.stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.MAIN))
                .sum();
        totalWeekendDiscountAmount += WEEKEND_DISCOUNT.value * weekendDiscountQuantity;
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
