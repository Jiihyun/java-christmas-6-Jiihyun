package christmas.domain.dto.output;

import christmas.domain.CustomerOrder;
import christmas.domain.OrderItem;
import christmas.domain.constants.Badge;
import christmas.domain.discount.DiscountInfos;

import java.util.List;
import java.util.Map;

public class CustomerOrderResponse {
    private final int visitDay;
    private final List<OrderItem> orderItems;
    private final int totalPurchasedAmount;
    private final boolean hasGift;
    private final Map<String, Integer> benefitDetails;
    private final int totalBenefitAmount;
    private final int expectedAmount;
    private final Badge badge;


    public CustomerOrderResponse(int visitDay, List<OrderItem> orderItems, int totalPurchasedAmount, DiscountInfos discountInfos) {
        this.visitDay = visitDay;
        this.orderItems = orderItems;
        this.totalPurchasedAmount = totalPurchasedAmount;
        this.hasGift = discountInfos.hasFreeGift();
        this.benefitDetails = discountInfos.getNameAndAmount();
        this.totalBenefitAmount = discountInfos.getTotalBenefitAmount();
        this.expectedAmount = totalPurchasedAmount + discountInfos.getTotalBenefitAmountExceptGift();
        this.badge = Badge.getBadge(discountInfos);
    }

    public static CustomerOrderResponse of(CustomerOrder customerOrder) {
        return new CustomerOrderResponse(customerOrder.getDay().toInt(),
                customerOrder.getOrderItems().getOrderItems(),
                customerOrder.getTotalPurchasedAmount(),
                customerOrder.getDiscountInfos()
        );
    }

    public int getVisitDay() {
        return visitDay;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public boolean hasGift() {
        return hasGift;
    }

    public Map<String, Integer> getBenefitDetails() {
        return benefitDetails;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getExpectedAmount() {
        return expectedAmount;
    }

    public Badge getBadge() {
        return badge;
    }
}
