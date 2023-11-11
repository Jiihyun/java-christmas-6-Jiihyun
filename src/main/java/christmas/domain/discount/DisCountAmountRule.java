package christmas.domain.discount;

public enum DisCountAmountRule {
    CHRISTMAS_MINIMUM_DISCOUNT(1000),
    WEEKDAY_DISCOUNT(2023),
    WEEKEND_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000);
    public final int value;

    DisCountAmountRule(int value) {
        this.value = value;
    }
}
