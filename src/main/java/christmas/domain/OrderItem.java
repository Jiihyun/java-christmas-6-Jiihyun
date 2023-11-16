package christmas.domain;

import christmas.domain.constants.Menu;
import christmas.domain.constants.MenuCategory;
import christmas.validator.OrderValidator;

public class OrderItem {
    private final Menu menu;

    private final int quantity;

    private OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(Menu menu, int quantity) {
        OrderValidator.validateMenuQuantity(quantity);
        return new OrderItem(menu, quantity);
    }

    public int getPurchasedAmount() {
        return menu.getPrice() * quantity;
    }

    public int getQuantityInSameCategory(MenuCategory menuCategory) {
        if (isSameCategory(menuCategory)) {
            return quantity;
        }
        return 0;
    }

    private boolean isSameCategory(MenuCategory menuCategory) {
        return menu.getMenuCategory() == menuCategory;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
