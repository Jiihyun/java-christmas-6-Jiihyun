package christmas.domain.discount.strategy;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendTest {
    private OrderItems orderItems;
    private Weekend weekendStrategy;

    @BeforeEach
    public void setUp() {
        orderItems = OrderItems.from(Map.of("해산물파스타", 2));
        weekendStrategy = new Weekend();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("주말에 메인메뉴를 주문하면 할인을 받을 수 있다.")
    void canGetWeekendDiscountWithMainOnWeekend(int day) {
        //given
        Day orderDate = new Day(day);
        //when
        boolean applicable = weekendStrategy.isApplicable(orderDate, orderItems);
        //then
        Assertions.assertThat(applicable).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    @DisplayName("평일에 메인메뉴를 주문하면 할인을 받을 수 없다.")
    void cannotGetWeekendDiscountWithMainOnWeekday(int day) {
        //given
        Day orderDate = new Day(day);
        //when
        boolean applicable = weekendStrategy.isApplicable(orderDate, orderItems);
        //then
        Assertions.assertThat(applicable).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 9, 22, 23})
    @DisplayName("주말이지만 메인메뉴를 주문하지 않으면 할인이 적용되지 않는다.")
    void cannotGetWeekendDiscountWithNoMain(int day) {
        // given
        Day orderDate = new Day(day);
        orderItems = OrderItems.from(Map.of("시저샐러드", 1));
        //when
        boolean applicable = weekendStrategy.isApplicable(orderDate, orderItems);
        //then
        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    @DisplayName("메인메뉴 하나당 2023원씩 할인이 적용된다.")
    void applyDiscountPerMain() {
        // given
        // when
        DiscountInfo discountInfo = weekendStrategy.applyDiscount(orderItems);
        // then
        assertThat(discountInfo.getCategoryName()).isEqualTo("주말 할인");
        assertThat(discountInfo.getAmount()).isEqualTo(-4_046);
    }

}