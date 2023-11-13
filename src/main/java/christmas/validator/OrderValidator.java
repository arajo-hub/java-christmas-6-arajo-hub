package christmas.validator;

import christmas.eventplanner.EventPlannerDetail;
import christmas.order.OrderMenu;
import christmas.enums.MenuType;

import java.util.List;

/**
 * 주문을 검증하는 클래스
 */
public class OrderValidator {

    public static boolean isValid(List<OrderMenu> orderMenus) {
        if (isDuplicateOrderMenu(orderMenus)) {
            return false;
        }
        if (isOverMenuMaxCount(orderMenus)) {
            return false;
        }
        if (isOnlyDrinkMenuType(orderMenus)) {
            return false;
        }
        return true;
    }

    private static boolean isOverMenuMaxCount(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getCount)
                .sum() > EventPlannerDetail.ORDER_MENU_TOTAL_MAX_COUNT;
    }

    private static boolean isDuplicateOrderMenu(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenu)
                .distinct()
                .count() != orderMenus.size();
    }

    private static boolean isOnlyDrinkMenuType(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .filter(orderMenu -> MenuType.DRINK.equals(orderMenu.getMenuType()))
                .count() == (orderMenus.size());
    }

}
