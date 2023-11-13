package christmas.event.gift;

import christmas.EventPlannerDetail;
import christmas.Order;
import christmas.enums.Menu;
import christmas.event.Event;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * 샴페인 증정 이벤트
 */
public class ChampagneEvent extends Event<Gift> {

    public static final int EVENT_AMOUNT_THRESHOLD = 120_000;

    public ChampagneEvent() {
        this.name = "증정 이벤트";
        this.compensation = new ArrayList<>();
        this.startDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 1);
        this.endDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 31);
    }

    @Override
    public boolean isAvailable(Order order) {
        if (order == null || !isInEventPeriod(order.getReservationDate())) {
            return false;
        }
        return order.getPaymentAmountBeforeSale() >= EVENT_AMOUNT_THRESHOLD;
    }

    @Override
    public void apply(Order order) {
        if (order != null && isAvailable(order)) {
            compensation.add(new Gift(Menu.CHAMPAGNE, 1));
        }
    }
}
