package christmas.domain.discount;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountInfos {
    private final List<DiscountInfo> discountInfos;

    public DiscountInfos(List<DiscountInfo> discountInfos) {
        this.discountInfos = discountInfos;
    }

    public boolean hasFreeGift() {
        return discountInfos.stream()
                .anyMatch(DiscountInfo::hasMinimumAmountForFreeGift);
    }

    public int size() {
        return discountInfos.size();
    }

    public Map<String, Integer> getNameAndAmount() {
        return discountInfos.stream()
                .collect(Collectors.toMap(
                        DiscountInfo::getCategoryName,
                        DiscountInfo::getAmount));
    }

    public int getTotalBenefitAmount() {
        return discountInfos.stream()
                .mapToInt(DiscountInfo::getAmount)
                .sum();
    }

    public int getTotalBenefitAmountExceptGift() {
        return discountInfos.stream()
                .filter(discountInfo -> {
                    String freeGift = "증정 이벤트";
                    return !discountInfo.getCategoryName().equals(freeGift);
                })
                .mapToInt(DiscountInfo::getAmount)
                .sum();
    }
}
