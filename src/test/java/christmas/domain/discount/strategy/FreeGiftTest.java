package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.domain.discount.constants.DiscountAmountRule.PRICE_OF_CHAMPAGNE;
import static christmas.domain.discount.constants.DiscountCategory.FREE_GIFT;
import static org.assertj.core.api.Assertions.assertThat;

class FreeGiftTest {
    private OrderItems orderItems;
    private FreeGift freeGiftStrategy;

    @BeforeEach
    public void setUp() {
        orderItems = OrderItems.from(Map.of("타파스", 8, "레드와인", 2));
        freeGiftStrategy = new FreeGift();
    }

    @Test
    @DisplayName("총 구매금액이 12만원 이상이면 할인이 적용된다.")
    void canGetFreeGiftDiscount() {
        //given
        Day orderDate = new Day(28);
        // when
        boolean applicable = freeGiftStrategy.isApplicable(orderDate, orderItems);
        // then
        assertThat(applicable).isTrue();
    }

    @Test
    @DisplayName("총 구매금액이 12만원 미만이면 할인을 받을 수 없다.")
    void cannotGetFreeGiftDiscount() {
        //given
        Day orderDate = new Day(14);
        orderItems = OrderItems.from(Map.of("타파스", 8));
        // when
        boolean applicable = freeGiftStrategy.isApplicable(orderDate, orderItems);
        // then
        assertThat(applicable).isFalse();
    }

    @Test
    @DisplayName("-25_000원 할인이 적용된다.")
    void applyFreeGiftDiscount() {
        // given
        // when
        DiscountInfo discountInfo = freeGiftStrategy.applyDiscount(orderItems);
        // then
        assertThat(discountInfo.getCategoryName()).isEqualTo(FREE_GIFT.getName());
        assertThat(discountInfo.getAmount()).isEqualTo(-PRICE_OF_CHAMPAGNE.getValue());
    }
}