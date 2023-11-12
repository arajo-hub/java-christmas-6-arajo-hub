package christmas.event.badge;

import christmas.EventPlannerDetail;
import christmas.Order;
import christmas.event.Event;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * 배지 이벤트
 */
public class BadgeEvent extends Event<Badge> {

    public BadgeEvent() {
        this.name = "배지 이벤트";
        this.compensation = new ArrayList<>();
        this.startDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 1);
        this.endDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 31);
    }

    @Override
    public boolean isAvailable(Order order) {
        return isInEventPeriod(order.getReservationDate());
    }

    @Override
    public void apply(Order order) {
        if (isAvailable(order)) {
            Badge badge = Badge.getBadge(order.getBenefit().getTotalBenefit());
            if (badge != null) {
                this.compensation.add(badge);
            }
        }
    }
}
