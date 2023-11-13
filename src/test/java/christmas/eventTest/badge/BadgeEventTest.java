package christmas.eventTest.badge;

import christmas.EventPlannerDetail;
import christmas.Order;
import christmas.OrderMenu;
import christmas.enums.Menu;
import christmas.event.badge.Badge;
import christmas.event.badge.BadgeEvent;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BadgeEventTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 5_000, 5_001, 10_000, 10_001, 20_000, 20_001})
    void 총혜택금액에_따라_별_증정(int totalBenefit) {
        if (totalBenefit < 5_000) {
            assertEquals(null, Badge.getBadge(totalBenefit));
        } else if (totalBenefit < 10_000){
            assertEquals(Badge.STAR, Badge.getBadge(totalBenefit));
        } else if (totalBenefit < 20_000) {
            assertEquals(Badge.TREE, Badge.getBadge(totalBenefit));
        } else {
            assertEquals(Badge.SANTA, Badge.getBadge(totalBenefit));
        }
    }

    @Test
    void 배지이벤트_이벤트기간내주문_성공() {
        LocalDate reservationDate = LocalDate.of(Year.now().getValue(), Month.DECEMBER.getValue(), 24);
        OrderMenu barbecueLip = new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 19);
        OrderMenu tBoneSteak = new OrderMenu(Menu.T_BONE_STEAK.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1);
        List<OrderMenu> orderMenus = List.of(barbecueLip, tBoneSteak);
        Order order = new Order(reservationDate, orderMenus);

        BadgeEvent badgeEvent = new BadgeEvent();
        assertTrue(badgeEvent.isInEventPeriod(reservationDate));
        assertTrue(badgeEvent.isAvailable(order));
    }

    /* 이하 예외 케이스 */

    @Test
    void 배지이벤트_이벤트기간외주문_실패() {
        LocalDate reservationDate = LocalDate.of(Year.now().getValue() - 1, Month.DECEMBER.getValue(), 24);
        OrderMenu barbecueLip = new OrderMenu(Menu.BARBECUE_LIP.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 19);
        OrderMenu tBoneSteak = new OrderMenu(Menu.T_BONE_STEAK.getName() + EventPlannerDetail.MENU_COUNT_SEPARATOR + 1);
        List<OrderMenu> orderMenus = List.of(barbecueLip, tBoneSteak);
        Order order = new Order(reservationDate, orderMenus);

        BadgeEvent badgeEvent = new BadgeEvent();
        assertFalse(badgeEvent.isInEventPeriod(reservationDate));
        assertFalse(badgeEvent.isAvailable(order));
    }

    @Test
    void 주문이_null일_때_적용_불가() {
        BadgeEvent badgeEvent = new BadgeEvent();
        assertFalse(badgeEvent.isAvailable(null));
    }

}
