package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.strategy.*;

import java.util.List;

import static christmas.domain.discount.constants.DiscountAmountRule.MINIMUM_TOTAL_PURCHASED_AMOUNT;

public class Discounter {
    private final Day day;
    private final OrderItems orderItems;
    private final int totalPurchasedAmount;
    private final List<DiscountStrategy> discountStrategies = createDiscountStrategies();

    public Discounter(Day day, OrderItems orderItems, int totalPurchasedAmount) {
        this.day = day;
        this.orderItems = orderItems;
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public DiscountInfos applyDiscount() {
        if (totalPurchasedAmount < MINIMUM_TOTAL_PURCHASED_AMOUNT.getValue()) {
            return new DiscountInfos(List.of());
        }
        return new DiscountInfos((discountStrategies.stream()
                .filter(strategy -> strategy.isApplicable(day, orderItems))
                .map(strategy -> strategy.applyDiscount(orderItems))
                .toList()));
    }

    private List<DiscountStrategy> createDiscountStrategies() {
        return List.of(
                new ChristmasDDay(),
                new Weekday(),
                new Weekend(),
                new Special(),
                new FreeGift()
        );
    }
}
