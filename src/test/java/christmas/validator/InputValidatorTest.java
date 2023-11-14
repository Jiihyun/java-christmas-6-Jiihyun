package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.exception.ExceptionMessage.INPUT_ORDER_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    @DisplayName("사용자의 입력값이 공백이면 예외를 생성한다.")
    void inputWithWhiteSpace(String input) {

        assertThatThrownBy(() -> InputValidator.validateBlank(input, INPUT_ORDER_FORMAT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1, 제로콜라-2", "타파스,1-제로콜라-1", "타파스-1,제로콜라-1,", "타파스,1", "타파스-1,제로콜라 -1"})
    @DisplayName("사용자의 입력값이 유효한 메뉴와 수량 포맷이 아닐 때 예외를 생성한다.")
    void inputWithInvalidateFormat(String input) {

        assertThatThrownBy(() -> InputValidator.validateMenuAndQuantityFormat(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}