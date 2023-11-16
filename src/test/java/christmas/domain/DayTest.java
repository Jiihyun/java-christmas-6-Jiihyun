package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DayTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 32, -1, 40})
    @DisplayName("날짜가 유효하지 않으면(1~31) 예외를 생성한다.")
    void throwExceptionWhenDayValueIsInvalidate(int input) {

        assertThatThrownBy(() -> new Day(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 31})
    @DisplayName("Day 클래스가 유효하면 toInt시 int값이 동일하다.")
    void getDayValue(int dayValue) {
        // given
        Day day = new Day(dayValue);
        // when
        int result = day.toInt();
        // then
        assertThat(result).isEqualTo(dayValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 31})
    @DisplayName("날짜를 통해 요일을 얻을 수 있다.")
    void getDayOfWeekWhenPutDayValue(int dayValue) {
        // given
        Day day = new Day(dayValue);
        // when
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        // then
        assertThat(dayOfWeek).isEqualTo(LocalDate.of(2023, 12, dayValue).getDayOfWeek());
    }
}
