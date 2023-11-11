package christmas.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static OrderItems from(Map<String, Integer> menuNamesAndQuantities) {
        List<OrderItem> orderItem = menuNamesAndQuantities.entrySet()
                .stream()
                .map(entry -> {
                    Menu menu = Menu.from(entry.getKey());
                    Integer quantity = entry.getValue();

                    return OrderItem.of(menu, quantity);
                })
                .collect(Collectors.toList());

        return new OrderItems(orderItem);
    }
}



