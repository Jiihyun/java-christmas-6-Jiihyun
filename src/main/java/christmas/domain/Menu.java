package christmas.domain;

import christmas.exception.ExceptionMessage;

import java.util.List;
import java.util.function.Predicate;

import static christmas.domain.MenuCategory.*;

public enum Menu {
    // APPETIZER
    YANGSONGISOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESARSALAD("시저샐러드", 8000, APPETIZER),

    // MAIN
    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BBQ_RIB("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),

    // DESSERT
    CHOCO_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),

    // DRINK
    ZERO_COLA("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    private static final List<Menu> menus = List.of(values());
    private final String name;
    private final int price;
    private final MenuCategory menuCategory;

    Menu(String name, int price, MenuCategory menuCategory) {
        this.name = name;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    public static Menu from(String menuName) {
        Predicate<Menu> isSameName = menu -> menu.name.equals(menuName);

        return menus.stream()
                .filter(isSameName)
                .findFirst()
                .orElseThrow(ExceptionMessage.INPUT_ORDER_FORMAT::getException);
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
