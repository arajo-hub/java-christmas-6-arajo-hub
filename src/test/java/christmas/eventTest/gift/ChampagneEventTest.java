package christmas.eventTest.gift;

import christmas.eventplanner.EventPlannerDetail;
import christmas.order.Order;
import christmas.order.OrderMenu;
import christmas.enums.Menu;
import christmas.event.gift.ChampagneEvent;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ChampagneEventTest {

    @Test
    void 총주문금액_12만원_이상_샴페인_증정_성공() {
        LocalDate reservationDate = LocalDate.of(Year.now().getValue(), Month.DECEMBER.getValue(), 24);
        OrderMenu barbecueLip = new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 19);
        OrderMenu tBoneSteak = new OrderMenu(Menu.T_BONE_STEAK.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1);
        List<OrderMenu> orderMenus = List.of(barbecueLip, tBoneSteak);
        Order order = new Order(reservationDate, orderMenus);

        assertTrue(order.getPaymentAmountBeforeSale() >= 120_000);

        ChampagneEvent champagneEvent = new ChampagneEvent();
        champagneEvent.apply(order);

        assertEquals(1, champagneEvent.getCompensation().size());
        assertEquals(Menu.CHAMPAGNE, champagneEvent.getCompensation().get(0).getMenu());
        assertEquals(1, champagneEvent.getCompensation().get(0).getCount());
    }

    /* 이하 예외 케이스 */

    @Test
    void 총주문금액_12만원_미만_샴페인_증정_실패() {
        LocalDate reservationDate = LocalDate.of(Year.now().getValue(), Month.DECEMBER.getValue(), 24);
        OrderMenu tBoneSteak = new OrderMenu(Menu.T_BONE_STEAK.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1);

        assertTrue(tBoneSteak.getTotalPrice() < 120_000);

        List<OrderMenu> orderMenus = List.of(tBoneSteak);
        Order order = new Order(reservationDate, orderMenus);
        ChampagneEvent champagneEvent = new ChampagneEvent();
        champagneEvent.apply(order);

        assertEquals(0, champagneEvent.getCompensation().size());
    }

    @Test
    void 주문이_null일_때_적용_불가() {
        ChampagneEvent champagneEvent = new ChampagneEvent();
        assertFalse(champagneEvent.isAvailable(null));
    }

    @Test
    void 예약일이_이벤트_기간이_아닐_때_적용_불가() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1));
        Order order = new Order(LocalDate.of(2021, 1, 31), orderMenus);
        ChampagneEvent champagneEvent = new ChampagneEvent();
        assertFalse(champagneEvent.isAvailable(order));
    }

}
