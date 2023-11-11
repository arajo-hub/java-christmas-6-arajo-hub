package christmas.enums;

/**
 * 주문 가능한 메뉴 정보
 */
public enum Menu {

    MUSHROOM_SOUP("양송이수프", MenuType.APPETIZER, 6_000),
    TAPAS("타파스", MenuType.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8_000),

    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55_000),
    BARBECUE_LIP("바비큐립", MenuType.MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25_000),

    CHOCOLATE_CAKE("초코케이크", MenuType.DESSERT, 15_000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5_000),

    ZERO_COKE("제로콜라", MenuType.DRINK, 3_000),
    RED_WINE("레드와인", MenuType.DRINK, 60_000),
    CHAMPAGNE("샴페인", MenuType.DRINK, 25_000),
    ;

    private String name;
    private MenuType menuType;
    private int price;

    Menu(String name, MenuType menuType, int price) {
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public int getPrice() {
        return price;
    }

    public static Menu findMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 메뉴");
    }

}