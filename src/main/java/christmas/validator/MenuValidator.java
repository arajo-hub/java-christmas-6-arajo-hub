package christmas.validator;

import christmas.eventplanner.EventPlannerDetail;
import christmas.enums.Menu;

import static christmas.eventplanner.EventPlannerDetail.MENU_COUNT_SEPARATOR;

/**
 * 메뉴를 검증하는 클래스
 */
public class MenuValidator {

    public static boolean isValid(String input) {
        if (input.split(MENU_COUNT_SEPARATOR).length < 2) {
            return false;
        }

        String menuName = input.split(MENU_COUNT_SEPARATOR)[0];
        String count = input.split(MENU_COUNT_SEPARATOR)[1];

        return isValidMenuName(menuName) && isValidMenuCount(count);
    }

    public static boolean isValidMenuName(String menuName) {
        try {
            return Menu.findMenuByName(menuName) != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidMenuCount(String count) {
        try {
            return isMenuCountInValidRange(Integer.parseInt(count));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isMenuCountInValidRange(int count) {
        return count >= EventPlannerDetail.ORDER_MENU_MIN_COUNT;
    }

}
