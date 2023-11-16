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

class WeekdayTest {
    private OrderItems orderItems;
    private Weekday weekdayStrategy;

    @BeforeEach
    public void setUp() {
        orderItems = OrderItems.from(Map.of("초코케이크", 5));
        weekdayStrategy = new Weekday();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 11, 19, 28, 31})
    @DisplayName("평일에 디저트메뉴를 주문하면 할인을 받을 수 있다.")
    void canGetWeekdayDiscountWithDessertOnWeekday(int day) {
        //given
        Day orderDate = new Day(day);
        //when
        boolean applicable = weekdayStrategy.isApplicable(orderDate, orderItems);
        //then
        Assertions.assertThat(applicable).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("주말에 디저트메뉴를 주문하면 할인을 받을 수 없다.")
    void cannotGetWeekdayDiscountWithDessertOnWeekend(int day) {
        //given
        Day orderDate = new Day(day);
        //when
        boolean applicable = weekdayStrategy.isApplicable(orderDate, orderItems);
        //then
        Assertions.assertThat(applicable).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 11, 12, 13, 14})
    @DisplayName("평일이지만 디저트를 주문하지 않으면 할인이 적용되지 않는다.")
    void cannotGetWeekdayDiscountWithNoDessert(int day) {
        // given
        Day orderDate = new Day(day);
        orderItems = OrderItems.from(Map.of("양송이수프", 1));
        //when
        boolean applicable = weekdayStrategy.isApplicable(orderDate, orderItems);
        //then
        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    @DisplayName("디저트 하나당 2023원씩 할인이 적용된다.")
    void applyDiscountPerDessert() {
        // given
        // when
        DiscountInfo discountInfo = weekdayStrategy.applyDiscount(orderItems);
        // then
        assertThat(discountInfo.getCategoryName()).isEqualTo("평일 할인");
        assertThat(discountInfo.getAmount()).isEqualTo(-10_115);
    }
}