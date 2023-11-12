package christmas.domain.discount;

public enum DiscountAmountRule {
    MINIMUM_TOTAL_PURCHASED_AMOUNT(10_000),
    MINIMUM_PURCHASE_AMOUNT_FOR_CHAMPAGNE_BENEFIT(120_000),
    CHRISTMAS_MINIMUM_DISCOUNT(1000),
    WEEKDAY_DISCOUNT(2023),
    WEEKEND_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    PRICE_OF_CHAMPAIGE(25_000);

    public final int value;

    DiscountAmountRule(int value) {
        this.value = value;
    }
}
