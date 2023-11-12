package christmas.domain.discount;

public class DiscountInfo {
    private final DiscountCategory category;
    private final int amount;

    public DiscountInfo(DiscountCategory category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public boolean hasMinimumAmountForFreeGift() {
        return category == DiscountCategory.FREE_GIFT;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public int getAmount() {
        return amount;
    }
}
