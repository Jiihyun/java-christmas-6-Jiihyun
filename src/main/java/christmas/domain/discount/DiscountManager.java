package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DiscountManager {
    private static final int MINIMUM_PURCHASE_AMOUNT = 10000;
    private static final int CHRISTMAS_DAY = 25;
    private final Day day;
    private final List<OrderItem> orderItems;
    private final int totalPurchaseAmount;
    private final DiscountAmount discountAmount = new DiscountAmount();


    public DiscountManager(Day day, List<OrderItem> orderItems, int totalPurchaseAmount) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public void discount() {
        if (totalPurchaseAmount < MINIMUM_PURCHASE_AMOUNT) {
            return;
        }
        christmasDDayEvent();
        weekdayEvent();
        weekendEvent();
        specialEvent();
    }

    private void christmasDDayEvent() {
        int orderDate = day.getDay();
        if (orderDate > CHRISTMAS_DAY) {
            return;
        }
        discountAmount.applyChristmasDDayDiscount(orderDate);
    }

    private void weekdayEvent() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return;
        }
        discountAmount.applyWeekdayDiscount(orderItems);
    }

    private void weekendEvent() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            return;
        }
        discountAmount.applyWeekendDiscount(orderItems);
    }

    private void specialEvent() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        int orderDate = day.getDay();
        if (orderDate != CHRISTMAS_DAY || dayOfWeek != DayOfWeek.SUNDAY) {
            return;
        }
        discountAmount.applySpecialDiscount();
    }

    private DayOfWeek getDayOfWeek() {
        LocalDate localDate = day.toLocalDate();
        return localDate.getDayOfWeek();
    }

    public int getTotalDiscountAmount() {
        return discountAmount.getTotalDiscountAmount();
    }
}
