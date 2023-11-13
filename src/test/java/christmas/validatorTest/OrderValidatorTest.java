package christmas.validatorTest;

import christmas.order.OrderMenu;
import christmas.validator.OrderValidator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OrderValidatorTest {

    private OrderValidator orderValidator = new OrderValidator();

    @Test
    void 주문_정상() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu("타파스-1"), new OrderMenu("레드와인-1"));
        assertTrue(orderValidator.isValid(orderMenus));
    }

    /* 이하 예외 케이스 */

    @Test
    void 최대_주문가능_메뉴_개수_초과_실패() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu("타파스-1000"));
        assertFalse(orderValidator.isValid(orderMenus));
    }

    @Test
    void 중복된_메뉴_주문_실패() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu("타파스-1"), new OrderMenu("타파스-1"));
        assertFalse(orderValidator.isValid(orderMenus));
    }

    @Test
    void 음료로만_주문_실패() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu("레드와인-1"), new OrderMenu("제로콜라-1"));
        assertFalse(orderValidator.isValid(orderMenus));
    }

}
