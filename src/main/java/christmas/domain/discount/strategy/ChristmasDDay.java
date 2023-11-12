package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;

import static christmas.domain.discount.DiscountAmountRule.CHRISTMAS_MINIMUM_DISCOUNT;
import static christmas.domain.discount.DiscountCategory.CHRISTMAS_D_DAY;

public class ChristmasDDay implements DiscountStrategy {
    public static final int CHRISTMAS_DAY = 25;
    private static final int INCREASED_AMOUNT_PER_DAY = 100;
    private int orderDate;

    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {
        int totalChristmasDDayDiscountAmount = 0;

        int christmasDDayDiscountAmount = CHRISTMAS_MINIMUM_DISCOUNT.getValue()
                + (INCREASED_AMOUNT_PER_DAY * (orderDate - 1)); //첫 날부터 이벤트 적용하므로
        totalChristmasDDayDiscountAmount -= christmasDDayDiscountAmount;
        return new DiscountInfo(CHRISTMAS_D_DAY, totalChristmasDDayDiscountAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        orderDate = day.getDay();
        return orderDate <= CHRISTMAS_DAY;
    }
}
