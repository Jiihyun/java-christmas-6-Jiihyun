package christmas.domain.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static christmas.domain.discount.constants.DiscountCategory.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiscountInfosTest {
    static Stream<Arguments> provideDiscountInfosData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new DiscountInfo(WEEKDAY, 2_023),
                                new DiscountInfo(SPECIAL, 1_000)),
                        false, 2, Map.of("평일 할인", 2_023, "특별 할인", 1_000), 3_023, 3_023
                ),
                Arguments.of(
                        List.of(
                                new DiscountInfo(WEEKEND, 2_023),
                                new DiscountInfo(FREE_GIFT, 25_000)),
                        true, 2, Map.of("주말 할인", 2_023, "증정 이벤트", 25_000), 27_023, 2_023
                ),
                Arguments.of(
                        List.of(new DiscountInfo(CHRISTMAS_D_DAY, 1_100)),
                        false, 1, Map.of("크리스마스 디데이 할인", 1_100), 1_100, 1_100
                ),
                Arguments.of(
                        List.of(),
                        false, 0, Map.of(), 0, 0
                )
        );
    }

    @ParameterizedTest(name = "[{index}] 증정 메뉴 여부: {1}, 적용된 할인 이벤트: {3}, 총 혜택 금액: {4}원, 예상 결제 금액: {5}")
    @MethodSource("provideDiscountInfosData")
    @DisplayName("DiscountInfos 객체를 생성하면 증정메뉴여부, 적용된 할인 이벤트 및 금액, 총혜택금액, 예상결제금액을 구할 수 있다.")
    void createDiscountInfos(
            List<DiscountInfo> discountInfos,
            boolean expectedHasFreeGift,
            int expectedSize,
            Map<String, Integer> expectedNameAndAmount,
            int expectedTotalBenefitAmount,
            int expectedTotalBenefitAmountExceptGift
    ) {
        // given
        DiscountInfos discountInfosObject = new DiscountInfos(discountInfos);
        // then
        assertThat(discountInfosObject.hasFreeGift()).isEqualTo(expectedHasFreeGift);
        assertThat(discountInfosObject.size()).isEqualTo(expectedSize);
        assertThat(discountInfosObject.getNameAndAmount()).isEqualTo(expectedNameAndAmount);
        assertThat(discountInfosObject.getTotalBenefitAmount()).isEqualTo(expectedTotalBenefitAmount);
        assertThat(discountInfosObject.getTotalBenefitAmountExceptGift()).isEqualTo(expectedTotalBenefitAmountExceptGift);
    }
}





