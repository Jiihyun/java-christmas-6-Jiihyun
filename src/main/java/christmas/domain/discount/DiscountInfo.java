package christmas.domain.discount;

import christmas.domain.discount.constants.DiscountCategory;

public class DiscountInfo {
    private final DiscountCategory category;
    private final int amount;

    public DiscountInfo(DiscountCategory category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public boolean hasFreeGiftCategory() {
        return category == DiscountCategory.FREE_GIFT;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public int getAmount() {
        return amount;
    }
}
