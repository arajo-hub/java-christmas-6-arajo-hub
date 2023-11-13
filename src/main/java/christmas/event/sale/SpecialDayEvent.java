package christmas.event.sale;

import christmas.eventplanner.EventPlannerDetail;
import christmas.order.Order;
import christmas.event.Event;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * 특별 할인
 */
public class SpecialDayEvent extends Event<Sale> {

    private static final int BASE_DISCOUNT = 1_000;

    public SpecialDayEvent() {
        this.name = "특별 할인";
        this.compensation = new ArrayList<>();
        this.startDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 1);
        this.endDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 25);
    }

    @Override
    public boolean isAvailable(Order order) {
        return order != null && isInEventPeriod(order.getReservationDate()) && SpecialDay.isSpecialDay(order.getReservationDate());
    }

    @Override
    public void apply(Order order) {
        if (order != null && isAvailable(order)) {
            this.compensation.add(new Sale(BASE_DISCOUNT));
        }
    }

}
