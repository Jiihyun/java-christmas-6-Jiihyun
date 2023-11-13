package christmas.domain.discount.constants;

public enum DiscountAmountRule {
    MINIMUM_TOTAL_PURCHASED_AMOUNT(10_000),
    MINIMUM_PURCHASE_AMOUNT_FOR_CHAMPAGNE_BENEFIT(120_000),
    CHRISTMAS_MINIMUM_DISCOUNT(1000),
    WEEKDAY_DISCOUNT(2023),
    WEEKEND_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    PRICE_OF_CHAMPAGNE(25_000);

    private final int value;

    DiscountAmountRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
