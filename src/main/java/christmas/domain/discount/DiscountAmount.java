package christmas.domain.discount;

import christmas.domain.MenuCategory;
import christmas.domain.OrderItem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.discount.DiscountAmountRule.*;

public class DiscountAmount {
    private int totalChristmasDDayDiscountAmount = 0;
    private int totalWeekdayDiscountAmount = 0;
    private int totalWeekendDiscountAmount = 0;
    private int totalSpecialDiscountPriceAmount = 0;

    public void applyChristmasDDayDiscount(int orderDate) {
        int increasedAmountPerDay = 100;
        int ChristmasDDayDiscountAmount = CHRISTMAS_MINIMUM_DISCOUNT.getValue()
                + (increasedAmountPerDay * (orderDate - 1));
        totalChristmasDDayDiscountAmount += ChristmasDDayDiscountAmount;
    }

    public void applyWeekdayDiscount(List<OrderItem> orderItems) {
        int weekdayDiscountQuantity = orderItems.stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.DESSERT))
                .sum();
        totalWeekdayDiscountAmount += WEEKDAY_DISCOUNT.getValue() * weekdayDiscountQuantity;
    }

    public void applyWeekendDiscount(List<OrderItem> orderItems) {
        int weekendDiscountQuantity = orderItems.stream()
                .mapToInt(orderItem -> orderItem.getQuantityInSameCategory(MenuCategory.MAIN))
                .sum();
        totalWeekendDiscountAmount += WEEKEND_DISCOUNT.getValue() * weekendDiscountQuantity;
    }

    public void applySpecialDiscount() {
        totalSpecialDiscountPriceAmount += SPECIAL_DISCOUNT.getValue();
    }

    public int getTotalDiscountAmount() {
        return totalChristmasDDayDiscountAmount
                + totalWeekdayDiscountAmount
                + totalWeekendDiscountAmount
                + totalSpecialDiscountPriceAmount;
    }

    public Map<String, Integer> getDiscountAppliedEvent() {
        Map<String, Integer> appliedEvents = new LinkedHashMap<>();

        putToEvents(appliedEvents, "크리스마스 디데이 할인", totalChristmasDDayDiscountAmount);
        putToEvents(appliedEvents, "평일 할인", totalWeekdayDiscountAmount);
        putToEvents(appliedEvents, "주말 할인", totalWeekendDiscountAmount);
        putToEvents(appliedEvents, "특별 할인", totalSpecialDiscountPriceAmount);

        return appliedEvents;
    }

    private void putToEvents(Map<String, Integer> appliedEvents, String eventName, int totalAmount) {
        if (totalAmount > 0) {
            appliedEvents.put(eventName, totalAmount);
        }
    }
}
