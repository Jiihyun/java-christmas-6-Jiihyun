package christmas.domain.discount.constants;

public enum DiscountCategory {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    FREE_GIFT("증정 이벤트");
    private final String name;

    DiscountCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
