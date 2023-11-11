package christmas.domain;

public class OrderItem {
    private final Menu menu;

    private final int quantity;

    private OrderItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(Menu menu, int quantity) {
        return new OrderItem(menu, quantity);
    }
}
