package christmas.domain.discount;

import christmas.domain.discount.constants.DiscountCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.domain.discount.constants.DiscountCategory.*;
import static org.assertj.core.api.Assertions.assertThat;

class DiscountInfoTest {

    static Stream<Arguments> provideDiscountInfoData() {
        return Stream.of(
                Arguments.of(CHRISTMAS_D_DAY, 1_700, false, "크리스마스 디데이 할인", 1_700),
                Arguments.of(WEEKDAY, 2_023, false, "평일 할인", 2_023),
                Arguments.of(WEEKEND, 4_046, false, "주말 할인", 4_046),
                Arguments.of(SPECIAL, 1_000, false, "특별 할인", 1_000),
                Arguments.of(FREE_GIFT, 25_000, true, "증정 이벤트", 25_000)
        );
    }

    @ParameterizedTest(name = "[{index}] 카테고리가 {0}고 총 할인 금액이 {1}원인 DiscountCategory객체를 생성한다.")
    @MethodSource("provideDiscountInfoData")
    @DisplayName("올바른 메뉴카테고리와 금액 입력시, DiscountInfo 객체를 생성한다.")
    void createDiscountInfo(
            DiscountCategory category, int amount, boolean expectedFreeGift,
            String expectedCategoryName, int expectedAmount) {
        // given
        DiscountInfo discountInfo = new DiscountInfo(category, amount);
        // then
        assertThat(discountInfo.getCategoryName()).isEqualTo(expectedCategoryName);
        assertThat(discountInfo.getAmount()).isEqualTo(expectedAmount);
    }

    @ParameterizedTest(name = "[{index}] {0}와 FREE_GIFT의 일치여부: {2}.")
    @MethodSource("provideDiscountInfoData")
    @DisplayName("DiscountInfo객체가 freeGift카테고리를 가지고 있으면 true를 반환한다.")
    void createDiscountInfo(DiscountCategory category, int amount, boolean expectedFreeGift) {
        // given
        DiscountInfo discountInfo = new DiscountInfo(category, amount);
        // then
        assertThat(discountInfo.hasFreeGiftCategory()).isEqualTo(expectedFreeGift);
    }
}