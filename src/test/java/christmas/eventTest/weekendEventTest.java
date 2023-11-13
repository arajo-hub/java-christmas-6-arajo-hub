package christmas.eventTest;

import christmas.EventPlannerDetail;
import christmas.Order;
import christmas.OrderMenu;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.event.sale.WeekdayEvent;
import christmas.event.sale.WeekendEvent;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class weekendEventTest {

    private WeekendEvent weekendEvent = new WeekendEvent();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 11, 12, 18, 19}) // 1, 2는 주말
    void 할인_적용_성공(int date) {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1));
        Order order = new Order(LocalDate.of(Year.now().getValue(), Month.DECEMBER.getValue(), date), orderMenus);

        int mainCount = (int) orderMenus.stream().filter(orderMenu -> orderMenu.getMenuType() == MenuType.MAIN).count();
        int expectedPrice = mainCount * 2_023;

        weekendEvent.apply(order);

        assertEquals(expectedPrice, weekendEvent.getCompensation().get(0).getDiscount());
    }

    /* 이하 예외 케이스 */

    @Test
    void 주문이_null일_때_적용_불가() {
        assertFalse(weekendEvent.isAvailable(null));
    }

    @Test
    void 예약일이_이벤트_기간이_아닐_때_적용_불가() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1));
        Order order = new Order(LocalDate.of(2021, 1, 31), orderMenus);
        assertFalse(weekendEvent.isAvailable(order));
    }

}
