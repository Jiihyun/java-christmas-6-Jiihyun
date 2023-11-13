package christmas.domain.constants;

import christmas.domain.discount.DiscountInfos;

import java.util.Comparator;
import java.util.List;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);
    private static final List<Badge> badgeCategory = List.of(values());
    private final String name;
    private final int minimumAmount;

    Badge(String name, int minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public static Badge getBadge(DiscountInfos discountInfos) {
        return badgeCategory.stream().filter(badge -> badge.minimumAmount <= Math.abs(discountInfos.getTotalBenefitAmount()))
                .max(Comparator.comparingInt(Badge::getMinimumAmount))
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }

    private int getMinimumAmount() {
        return minimumAmount;
    }
}
