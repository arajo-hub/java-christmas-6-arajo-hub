package christmas.order;

import christmas.eventplanner.EventPlannerDetail;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.message.OrderErrorMessage;
import christmas.validator.MenuValidator;

/**
 * 주문한 메뉴
 */
public class OrderMenu {

    private Menu menu;
    private int count;

    public OrderMenu(String input) {
        if (!MenuValidator.isValid(input)) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER);
        }

        String name = input.split(EventPlannerDetail.MENU_COUNT_SEPARATOR)[0];
        int count = Integer.parseInt(input.split(EventPlannerDetail.MENU_COUNT_SEPARATOR)[1]);

        this.menu = Menu.findMenuByName(name);
        this.count = count;

    }

    public Menu getMenu() {
        return menu;
    }

    public MenuType getMenuType() {
        return menu.getMenuType();
    }

    public int getCount() {
        return count;
    }

    public int getTotalPrice() {
        return menu.getPrice() * count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.menu.getName());
        sb.append(EventPlannerDetail.MENU_COUNT_SEPARATOR);
        sb.append(this.count);
        return sb.toString();
    }
}
