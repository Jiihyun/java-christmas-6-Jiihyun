package christmas.domain;

import christmas.domain.constants.Menu;
import christmas.validator.OrderValidator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static OrderItems from(Map<String, Integer> menuNamesAndQuantities) {
        OrderValidator.validateOrderItems(menuNamesAndQuantities);

        List<OrderItem> orderItems = getOrderItems(menuNamesAndQuantities);
        return new OrderItems(orderItems);
    }

    private static List<OrderItem> getOrderItems(Map<String, Integer> menuNamesAndQuantities) {
        return menuNamesAndQuantities.entrySet()
                .stream()
                .map(getOrderItem())
                .collect(Collectors.toList());
    }

    private static Function<Map.Entry<String, Integer>, OrderItem> getOrderItem() {
        return entry -> {
            Menu menu = Menu.from(entry.getKey());
            Integer quantity = entry.getValue();
            return OrderItem.of(menu, quantity);
        };
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int getTotalPurchasedAmount() {

        return orderItems.stream()
                .map(OrderItem::getPurchasedAmount)
                .reduce(0, Integer::sum);
    }
}


