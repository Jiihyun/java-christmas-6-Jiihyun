package christmas.validator;

import christmas.domain.constants.Menu;
import christmas.domain.constants.MenuCategory;
import christmas.exception.ExceptionMessage;

import java.util.List;
import java.util.Map;

public class OrderValidator {
    private static final int MINIMUM_QUANTITY = 0;
    private static final int MAXIMUM_QUANTITY = 20;

    private OrderValidator() {
        throw new AssertionError();
    }

    public static void validateOrderItems(Map<String, Integer> menuNamesAndQuantities) {
        validateNotOnlyIncludeDrink(menuNamesAndQuantities);
        validateTotalMenuQuantity(menuNamesAndQuantities);
    }

    private static void validateNotOnlyIncludeDrink(Map<String, Integer> menuNamesAndQuantities) {
        List<Menu> menus = getMenus(menuNamesAndQuantities);

        boolean hasOnlyDrinkCategory = menus.stream()
                .allMatch(menu -> menu.getMenuCategory()
                        .equals(MenuCategory.DRINK));

        if (hasOnlyDrinkCategory) {
            throw ExceptionMessage.INPUT_ORDER_FORMAT.getException();
        }
    }

    private static List<Menu> getMenus(Map<String, Integer> menuNamesAndQuantities) {
        return menuNamesAndQuantities.keySet()
                .stream()
                .map(Menu::from)
                .toList();
    }

    private static void validateTotalMenuQuantity(Map<String, Integer> menuNamesAndQuantities) {
        int totalQuantity = menuNamesAndQuantities.values()
                .stream()
                .reduce(0, Integer::sum);
        if (totalQuantity > MAXIMUM_QUANTITY) {
            throw ExceptionMessage.INPUT_ORDER_FORMAT.getException();
        }
    }

    public static void validateMenuQuantity(int quantity) {
        if (quantity <= MINIMUM_QUANTITY) {
            throw ExceptionMessage.INPUT_ORDER_FORMAT.getException();
        }
    }
}

