package christmas.event;

import christmas.EventPlannerDetail;
import christmas.Order;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * 크리스마스 디데이 할인
 */
public class ChristmasDDayEvent extends Event<Sale> {

    private static final int BASE_DISCOUNT = 1_000;

    private static final int INTERVAL_DISCOUNT = 100;

    public ChristmasDDayEvent() {
        this.name = "크리스마스 디데이 할인";
        this.compensation = new ArrayList<>();
        this.startDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 1);
        this.endDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 25);
    }

    @Override
    boolean isAvailable(Order order) {
        return isInEventPeriod(order.getReservationDate());
    }

    @Override
    void apply(Order order) {
        if (isAvailable(order)) {
            int dayGap = order.getReservationDate().getDayOfMonth() - this.startDate.getDayOfMonth();
            this.compensation.add(new Sale(BASE_DISCOUNT + dayGap * INTERVAL_DISCOUNT));
        }
    }

}
