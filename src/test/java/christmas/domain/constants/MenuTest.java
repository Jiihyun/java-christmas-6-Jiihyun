package christmas.domain.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class MenuTest {
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"})
    @DisplayName("메뉴판에 존재하는 메뉴시 메뉴를 생성한다")
    void createMenu(String menuName) {
        //given
        //when
        //then
        assertThatCode(() -> Menu.from(menuName)).doesNotThrowAnyException();
        assertThat(Menu.from(menuName).getName()).isEqualTo(menuName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이스프", " ", "씨저샐러드", "T본스테이크", "바베큐립", "해산물파수타", "christmas파스타"})
    @DisplayName("메뉴판에 존재하지 않는 메뉴 입력시 예외를 생성한다")
    void createMenuWithWrongName(String menuName) {
        //given
        //when
        //then
        assertThatThrownBy(() -> Menu.from(menuName)).isInstanceOf(IllegalArgumentException.class).hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource({
            "CAESARSALAD,시저샐러드,8000,APPETIZER",
            "CHRISTMAS_PASTA,크리스마스파스타,25000,MAIN",
            "CHOCO_CAKE,초코케이크,15000,DESSERT",
            "CHAMPAGNE, 샴페인, 25000,DRINK"
    })
    @DisplayName("메뉴객체를 생성했을 때 올바른 이름,금액,카테고리를 가지고 있다")
    void hasRightField(Menu menu, String menuName, int amount, MenuCategory menuCategory) {
        //given
//        String menuName = "크리스마스파스타";
        //when
        Menu result = Menu.from(menuName);
        //then
        assertThat(result).isEqualTo(menu);
        assertThat(result.getName()).isEqualTo(menuName);
        assertThat(result.getPrice()).isEqualTo(amount);
        assertThat(result.getMenuCategory()).isEqualTo(menuCategory);

    }
}