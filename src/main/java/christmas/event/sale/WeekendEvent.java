package christmas.event.sale;

import christmas.eventplanner.EventPlannerDetail;
import christmas.order.Order;
import christmas.enums.MenuType;
import christmas.event.Event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * 평일 할인
 */
public class WeekendEvent extends Event<Sale> {

    private static final MenuType target = MenuType.MAIN;

    private static final int BASE_DISCOUNT_PER_TARGET = 2_023;

    public WeekendEvent() {
        this.name = "주말 할인";
        this.compensation = new ArrayList<>();
        this.startDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 1);
        this.endDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, 31);
    }

    @Override
    public boolean isAvailable(Order order) {
        return order != null
                && isInEventPeriod(order.getReservationDate())
                && isWeekend(order.getReservationDate());
    }

    private boolean isWeekend(LocalDate date) {
        return DayOfWeek.FRIDAY.equals(date.getDayOfWeek())
                || DayOfWeek.SATURDAY.equals(date.getDayOfWeek());
    }

    @Override
    public void apply(Order order) {
        if (order != null &&  isAvailable(order)) {
            order.getOrderMenus().stream()
                    .filter(orderMenu -> orderMenu.getMenu().getMenuType() == target)
                    .forEach(orderMenu -> this.compensation.add(new Sale(BASE_DISCOUNT_PER_TARGET * orderMenu.getCount())));
        }
    }

}
