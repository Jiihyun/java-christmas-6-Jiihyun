package christmas.domain;

import christmas.domain.constants.Menu;
import christmas.domain.constants.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderItemTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -2})
    @DisplayName("유효하지 않은 수량으로 OrderItem 생성시 예외를 반환한다.")
    void createOrderItemWithInvalidQuantity(int quantity) {
        assertThatThrownBy(() -> OrderItem.of(Menu.BBQ_RIB, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource({"CHRISTMAS_PASTA, 4, 100_000", "CHOCO_CAKE, 10,150_000", "RED_WINE, 3,180_000", "YANGSONGISOUP, 8,48_000"})
    @DisplayName("OrderItem 객체를 생성하여 주문한 메뉴당 구입금액을 구할 수 있다.")
    void shouldHaveSameAmountWithExpectedAmount(Menu menu, int quantity, int expectedAmount) {
        // given
        OrderItem orderItem = OrderItem.of(menu, quantity);
        // when
        int purchasedAmount = orderItem.getPurchasedAmount();
        // then
        assertThat(purchasedAmount).isEqualTo(expectedAmount);
    }

    @ParameterizedTest
    @CsvSource({"MAIN, DRINK", "MAIN,DESSERT", "MAIN,APPETIZER"})
    @DisplayName("같은 음식카테고리내의 음식이면 주문한 수량을 반환하고 아니면 0을 반환한다.")
    void getQuantityInSameCategory(MenuCategory sameCategory, MenuCategory differentCategory) {
        // given
        OrderItem orderItem = OrderItem.of(Menu.T_BONE_STEAK, 2);
        // when
        int quantityInSameCategory = orderItem.getQuantityInSameCategory(sameCategory);
        int quantityInDifferentCategory = orderItem.getQuantityInSameCategory(differentCategory);
        // then
        assertThat(quantityInSameCategory).isEqualTo(2);
        assertThat(quantityInDifferentCategory).isEqualTo(0);
    }
}