package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static christmas.domain.constants.Menu.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderItemsTest {

    private static Stream<Arguments> provideOrderItemsAndExpectedTotalPurchasedAmount() {
        return Stream.of(
                Arguments.of(
                        Map.of("해산물파스타", 2, "샴페인", 1),
                        (SEAFOOD_PASTA.getPrice() * 2) + (CHAMPAGNE.getPrice())),
                Arguments.of(
                        Map.of("티본스테이크", 5, "제로콜라", 3, "아이스크림", 1),
                        (T_BONE_STEAK.getPrice() * 5) + (ZERO_COLA.getPrice() * 3) + (ICE_CREAM.getPrice())),
                Arguments.of(
                        Map.of("타파스", 10, "초코케이크", 2),
                        (TAPAS.getPrice() * 10) + (CHOCO_CAKE.getPrice() * 2))
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrderItemsAndExpectedTotalPurchasedAmount")
    @DisplayName("모든 아이템 금액의 총합을 구할 수 있다.")
    void getTotalPurchasedAmount(Map<String, Integer> orderItemsMap, int expectedTotalPurchasedAmount) {
        // given
        OrderItems orderItems = OrderItems.from(orderItemsMap);
        // when
        int totalPurchasedAmount = orderItems.getTotalPurchasedAmount();
        // then
        assertThat(totalPurchasedAmount).isEqualTo(expectedTotalPurchasedAmount);
    }

    @Test
    @DisplayName("주문한 메뉴카테고리가 음료로만 구성되어 있지 않으면, OrderItems를 생성한다.")
    void createOrderItemsWithCategories() {

        assertThat(OrderItems.from(Map.of("티본스테이크", 3, "제로콜라", 2)))
                .isInstanceOf(OrderItems.class);
    }

    @Test
    @DisplayName("주문한 메뉴카테고리가 음료로만 구성되어 있으면 예외를 생성한다.")
    void createOrderItemsWithOnlyDrinkCategory() {

        assertThatThrownBy(() -> OrderItems.from(Map.of("제로콜라", 6, "샴페인", 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문한 메뉴들의 수량이 20개 이하일시, OrderItems를 생성한다.")
    void createOrderItemsBelowTotalQuantity() {

        assertThat(OrderItems.from(Map.of("시저샐러드", 18, "제로콜라", 2)))
                .isInstanceOf(OrderItems.class);
    }


    @Test
    @DisplayName("주문한 메뉴들의 수량이 20개 초과시, 예외를 생성한다.")
    void createOrderItemsAboveTotalQuantity() {

        assertThatThrownBy(() -> OrderItems.from(Map.of("시저샐러드", 18, "제로콜라", 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}