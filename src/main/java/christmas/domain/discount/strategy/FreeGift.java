package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;
import christmas.domain.discount.constants.DiscountCategory;

import static christmas.domain.discount.constants.DiscountAmountRule.MINIMUM_PURCHASE_AMOUNT_FOR_CHAMPAGNE_BENEFIT;
import static christmas.domain.discount.constants.DiscountAmountRule.PRICE_OF_CHAMPAGNE;

public class FreeGift implements DiscountStrategy {
    @Override
    public DiscountInfo applyDiscount(OrderItems orderItems) {

        int totalFreeGiftAmount = -PRICE_OF_CHAMPAGNE.getValue();
        return new DiscountInfo(DiscountCategory.FREE_GIFT, totalFreeGiftAmount);
    }

    @Override
    public boolean isApplicable(Day day, OrderItems orderItems) {
        return (orderItems.getTotalPurchasedAmount() >= MINIMUM_PURCHASE_AMOUNT_FOR_CHAMPAGNE_BENEFIT.getValue());
    }

}
