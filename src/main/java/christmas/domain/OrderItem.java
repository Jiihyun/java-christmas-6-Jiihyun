package christmas.domain;

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

    public boolean isSameCategoryAs(MenuCategory menuCategory) {
        return menu.getMenuCategory() == menuCategory;
    }
}
