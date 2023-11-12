package christmas.event.sale;

import christmas.EventPlannerDetail;
import christmas.Order;
import christmas.enums.MenuType;
import christmas.event.Event;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * 평일 할인
 */
public class WeekdayEvent extends Event<Sale> {

    private static final MenuType target = MenuType.DESSERT;

    private static final int BASE_DISCOUNT_PER_TARGET = 2_023;

    public WeekdayEvent() {
        this.name = "평일 할인";
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
            order.getOrderMenus().stream()
                    .filter(orderMenu -> orderMenu.getMenu().getMenuType() == target)
                    .forEach(orderMenu -> this.compensation.add(new Sale(BASE_DISCOUNT_PER_TARGET * orderMenu.getCount())));
        }
    }

}
