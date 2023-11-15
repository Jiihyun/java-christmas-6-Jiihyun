package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static christmas.domain.discount.constants.DiscountAmountRule.CHRISTMAS_MINIMUM_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDDayTest {
    private static final int INCREASED_AMOUNT_PER_DAY = 100;

    private OrderItems orderItems;
    private ChristmasDDay christmasDDayStrategy;

    @BeforeEach
    public void setUp() {
        orderItems = OrderItems.from(Map.of("아이스크림", 2));
        christmasDDayStrategy = new ChristmasDDay();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 24, 25})
    @DisplayName("25일 이내에 주문하면 할인을 받을 수 있다.")
    void canGetDiscountWithinChristmasDDayRange(int day) {
        //given
        Day orderDate = new Day(day);
        // when
        boolean applicable = christmasDDayStrategy.isApplicable(orderDate, orderItems);
        // then
        assertThat(applicable).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @DisplayName("25일 지나고 주문하면 할인을 받을 수 없다.")
    void cannotGetDiscountAboveChristmasDDayRange(int day) {
        //given
        Day orderDate = new Day(day);
        // when
        boolean applicable = christmasDDayStrategy.isApplicable(orderDate, orderItems);
        // then
        assertThat(applicable).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 25})
    @DisplayName("12월 1일부터 25일까지 1000원부터 100원씩 누적되어 할인이 적용된다.")
    void applyAccumulatedDiscountUntilChristmasDDay(int day) {
        // given
        Day orderDate = new Day(day);
        christmasDDayStrategy.isApplicable(orderDate, orderItems);
        // when
        DiscountInfo discountInfo = christmasDDayStrategy.applyDiscount(orderItems);
        // then
        assertThat(discountInfo.getCategoryName()).isEqualTo("크리스마스 디데이 할인");
        int expected = -(CHRISTMAS_MINIMUM_DISCOUNT.getValue() + (INCREASED_AMOUNT_PER_DAY * (orderDate.toInt() - 1)));
        assertThat(discountInfo.getAmount()).isEqualTo(expected);
    }
}