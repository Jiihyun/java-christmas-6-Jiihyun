package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class DiscounterTest {
    static Stream<Arguments> provideOrderItems() {
        OrderItems orderItemsOverMinimumAmount1 = OrderItems.from(Map.of(
                "바비큐립", 1,
                "레드와인", 2));
        OrderItems orderItemsOverMinimumAmount2 = OrderItems.from(Map.of(
                "아이스크림", 2));
        OrderItems orderItemsUnderMinimumAmount1 = OrderItems.from(Map.of(
                "아이스크림", 1));
        OrderItems orderItemsUnderMinimumAmount2 = OrderItems.from(Map.of(
                "타파스", 1,
                "제로콜라", 1));

        return Stream.of(
                Arguments.of(orderItemsOverMinimumAmount1, 174_000, 2, true, -26_400, -1_400),
                Arguments.of(orderItemsOverMinimumAmount2, 10_000, 2, false, -5_446, -5_446),
                Arguments.of(orderItemsUnderMinimumAmount1, 5_000, 0, false, 0, 0),
                Arguments.of(orderItemsUnderMinimumAmount2, 8_500, 0, false, 0, 0)
        );
    }

    @ParameterizedTest(name = "[{index}] 주문한 메뉴가 {0}면 총 금액은 {1}원이며, 적용된 할인 이벤트는 {2}개,증정 메뉴 여부: {3}," +
            "총 혜택 금액: {4}원, 총 할인받은 금액: {5}원 이다.")
    @MethodSource("provideOrderItems")
    @DisplayName("총 구매금액에 따라 적용된 이벤트 및 금액을 지닌 리스트를 생성한다.")
    void createDiscountInfosBasedOnTotalPurchasedAmount(
            OrderItems orderItems, int totalAmount, int expectedSize,
            boolean expectedHasFreeGift, int expectedTotalBenefit, int expectedTotalBenefitExceptGift) {
        //given
        Discounter discounter = new Discounter(new Day(5), orderItems, totalAmount);
        //when
        DiscountInfos discountInfos = discounter.applyDiscount();
        //then
        Assertions.assertThat(discountInfos.size()).isEqualTo(expectedSize);
        Assertions.assertThat(discountInfos.hasFreeGift()).isEqualTo(expectedHasFreeGift);
        Assertions.assertThat(discountInfos.getTotalBenefitAmount()).isEqualTo(expectedTotalBenefit);
        Assertions.assertThat(discountInfos.getTotalBenefitAmountExceptGift()).isEqualTo(expectedTotalBenefitExceptGift);
    }
}