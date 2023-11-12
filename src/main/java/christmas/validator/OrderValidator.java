package christmas.validator;

import christmas.EventPlannerDetail;
import christmas.OrderMenu;

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
        if (isOnlyOneMenuType(orderMenus)) {
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

    private static boolean isOnlyOneMenuType(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenuType)
                .distinct()
                .count() == 1;
    }

}
