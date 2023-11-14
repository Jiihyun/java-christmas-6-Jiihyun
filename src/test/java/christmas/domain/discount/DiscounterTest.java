package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiscounterTest {
    static Stream<Arguments> provideOrderItemsAboveMinimumAmount() {
        OrderItems orderItems1 = OrderItems.from(Map.of(
                "바비큐립", 1,
                "레드와인", 2,
                "아이스크림", 1));
        OrderItems orderItems2 = OrderItems.from(Map.of(
                "아이스크림", 2));
        return Stream.of(
                Arguments.of(orderItems1, 179_000, 3),
                Arguments.of(orderItems2, 10_000, 1)
        );
    }

    static Stream<Arguments> provideOrderItemsBelowMinimumAmount() {
        OrderItems orderItems1 = OrderItems.from(Map.of(
                "아이스크림", 1));
        OrderItems orderItems2 = OrderItems.from(Map.of(
                "타파스", 1,
                "제로콜라", 1));
        return Stream.of(
                Arguments.of(orderItems1, 5_000),
                Arguments.of(orderItems2, 8_500)
        );
    }

    @ParameterizedTest(name = "[{index}] 주문한 메뉴가 {0}면 총 금액은 {1}원이며 {2}개의 할인이 적용된다.")
    @MethodSource("provideOrderItemsAboveMinimumAmount")
    @DisplayName("총 구매금액이 할인 최소 금액 이상인 경우 할인이 적용된다.")
    void applyDiscountAboveMinimumAmount(OrderItems orderItems, int totalPurchasedAmount, int size) {
        // given
        Day day = new Day(8);
        // when
        Discounter discounter = new Discounter(day, orderItems, totalPurchasedAmount);
        DiscountInfos discountInfos = discounter.applyDiscount();
        // then
        assertThat(discountInfos.size()).isEqualTo(size);
    }

    @ParameterizedTest(name = "[{index}] 주문한 메뉴가 {0}면 총 금액은 {1}원이며 할인이 적용되지 않아 size가 0이다.")
    @MethodSource("provideOrderItemsBelowMinimumAmount")
    @DisplayName("총 구매금액이 할인 최소 금액 미만인 경우 할인이 적용되지 않는다.")
    void cannotApplyDiscountBelowMinimumAmount(OrderItems orderItems, int totalPurchasedAmount) {
        // given
        Day day = new Day(10);
        // when
        Discounter discounter = new Discounter(day, orderItems, totalPurchasedAmount);
        DiscountInfos discountInfos = discounter.applyDiscount();
        // then
        assertThat(discountInfos.size()).isEqualTo(0);
    }
}