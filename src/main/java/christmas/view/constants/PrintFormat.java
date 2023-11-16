package christmas.view.constants;

public enum PrintFormat {
    DECEMBER("12"),
    COUNT_UNIT("개"),
    MONEY_FORMAT("%,d원\n"),
    BENEFIT_DETAIL_FORMAT("%s: "),
    ORDERED_MENU_FORMAT("%s %d%s\n");
    private final String format;

    PrintFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
