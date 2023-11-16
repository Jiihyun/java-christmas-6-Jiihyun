package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static christmas.domain.discount.constants.DiscountAmountRule.SPECIAL_DISCOUNT;
import static christmas.domain.discount.constants.DiscountCategory.SPECIAL;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialTest {
    private OrderItems orderItems;
    private Special specialStrategy;

    @BeforeEach
    public void setUp() {
        orderItems = OrderItems.from(Map.of("바비큐립", 2));
        specialStrategy = new Special();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @DisplayName("스페셜데이에 주문하면 할인을 받을 수 있다.")
    void canGetSpecialDiscount(int day) {
        //given
        Day orderDate = new Day(day);
        // when
        boolean applicable = specialStrategy.isApplicable(orderDate, orderItems);
        // then
        assertThat(applicable).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 15, 18, 26})
    @DisplayName("스페셜데이날짜가 아니면 스페열데이 할인을 받을 수 없다.")
    void cannotGetSpecialDiscount(int day) {
        //given
        Day orderDate = new Day(day);
        // when
        boolean applicable = specialStrategy.isApplicable(orderDate, orderItems);
        // then
        assertThat(applicable).isFalse();
    }

    @Test
    @DisplayName("1000원 할인이 적용된다.")
    void applySpecialDiscount() {
        // given
        // when
        DiscountInfo discountInfo = specialStrategy.applyDiscount(orderItems);
        // then
        assertThat(discountInfo.getCategoryName()).isEqualTo(SPECIAL.getName());
        assertThat(discountInfo.getAmount()).isEqualTo(-SPECIAL_DISCOUNT.getValue());
    }
}