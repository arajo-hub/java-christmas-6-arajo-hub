package christmas.enums;

/**
 * 메뉴 종류
 */
public enum MenuType {

    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료"),
    ;

    private String name;

    MenuType(String name) {
        this.name = name;
    }

}
