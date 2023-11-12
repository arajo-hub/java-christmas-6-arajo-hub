package christmas.validatorTest;

import christmas.EventPlannerDetail;
import christmas.enums.Menu;
import christmas.validator.MenuValidator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OrderMenuValidatorTest {

    private MenuValidator menuValidator = new MenuValidator();

    @Test
    void 메뉴_정상() {
        StringBuilder sb = new StringBuilder();
        sb.append(Menu.TAPAS.getName());
        sb.append(EventPlannerDetail.MENU_COUNT_SEPARATOR);
        sb.append(1);
        assertTrue(menuValidator.isValid(sb.toString()));
    }

    /* 이하 예외 케이스 */

    @Test
    void 존재하지_않는_메뉴명_입력시_실패() {
        String menuInput = "abc-1";
        assertFalse(menuValidator.isValid(menuInput));
    }

    @Test
    void 잘못된_메뉴입력형식_실패() {
        String menuInput = "abc#1";
        assertFalse(menuValidator.isValid(menuInput));
    }

    @Test
    void 개수_0_입력시_예외_발생() {
        StringBuilder sb = new StringBuilder();
        sb.append(Menu.TAPAS.getName());
        sb.append(EventPlannerDetail.MENU_COUNT_SEPARATOR);
        sb.append(0);
        assertFalse(menuValidator.isValid(sb.toString()));
    }

}
