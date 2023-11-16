package christmas.converter;

import christmas.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConverterTest {

    @ParameterizedTest
    @CsvSource({
            "10, INPUT_DAY_FORMAT",
            "28, INPUT_ORDER_FORMAT"
    })
    @DisplayName("숫자 형식의 문자열을 숫자로 변환한다.")
    void stringWithNumberFormat(String string, ExceptionMessage exceptionMessage) {
        //given
        int convertedNumber = Converter.convertToInt(string, exceptionMessage);
        //when
        //then
        assertThat(convertedNumber)
                .isInstanceOf(Integer.class)
                .isEqualTo(Integer.parseInt(string));

    }

    @ParameterizedTest
    @CsvSource({
            "h10, INPUT_DAY_FORMAT, [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.",
            "  , INPUT_ORDER_FORMAT,[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.",
            "*36, INPUT_DAY_FORMAT,[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.",
            "17아, INPUT_ORDER_FORMAT,[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.",
    })
    @DisplayName("문자열이 숫자 형식으로 안 이뤄져 있으면 예외를 반환한다.")
    void stringWithNonNumberFormat(
            String string,
            ExceptionMessage exceptionMessage,
            String exceptionMsg) {

        assertThatThrownBy(() -> Converter.convertToInt(string, exceptionMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMsg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,초코케이크-2"})
    @DisplayName("중복된 key가 없을 시 Map을 생성한다.")
    void createMapWithoutDuplicatedKey(String orderInput) {
        //given
        Map<String, Integer> expected = Map.of("타파스", 1, "초코케이크", 2);
        //when
        Map<String, Integer> result = Converter.convertToMap(orderInput, ExceptionMessage.INPUT_ORDER_FORMAT);
        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,타파스-2"})
    @DisplayName("중복된 key가 있을 시 예외를 생성한다.")
    void createMapWithDuplicatedKey(String orderInput) {

        assertThatThrownBy(() -> Converter.convertToMap(orderInput, ExceptionMessage.INPUT_ORDER_FORMAT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}