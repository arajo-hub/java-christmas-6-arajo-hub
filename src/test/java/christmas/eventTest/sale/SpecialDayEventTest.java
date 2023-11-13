package christmas.eventTest.sale;

import christmas.eventplanner.EventPlannerDetail;
import christmas.order.Order;
import christmas.order.OrderMenu;
import christmas.enums.Menu;
import christmas.event.sale.SpecialDayEvent;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SpecialDayEventTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 10}) // 3, 10은 Special Day
    void 할인_적용_성공(int date) {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1));
        Order order = new Order(LocalDate.of(Year.now().getValue(), Month.DECEMBER.getValue(), date), orderMenus);
        SpecialDayEvent specialDayEvent = new SpecialDayEvent();
        specialDayEvent.apply(order);

        assertEquals(1_000, specialDayEvent.getCompensation().get(0).getDiscount());
    }

    /* 이하 예외 케이스 */

    @Test
    void 주문이_null일_때_적용_불가() {
        SpecialDayEvent specialDayEvent = new SpecialDayEvent();
        assertFalse(specialDayEvent.isAvailable(null));
    }

    @Test
    void 예약일이_이벤트_기간이_아닐_때_적용_불가() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1));
        Order order = new Order(LocalDate.of(2021, 1, 31), orderMenus);
        SpecialDayEvent specialDayEvent = new SpecialDayEvent();
        assertFalse(specialDayEvent.isAvailable(order));
    }

}
