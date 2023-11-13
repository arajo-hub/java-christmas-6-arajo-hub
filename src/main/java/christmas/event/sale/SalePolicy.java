package christmas.event.sale;

import christmas.Order;
import christmas.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 할인 정책
 */
public class SalePolicy {

    private List<Event<Sale>> salePolicies;

    /**
     * 할인 정책 기본 설정
     */
    public SalePolicy() {
        this.salePolicies = List.of(new ChristmasDDayEvent(),
                new WeekdayEvent(),
                new WeekendEvent(),
                new SpecialDayEvent());
    }

    public SalePolicy(List<Event<Sale>> salePolicies) {
        this.salePolicies = salePolicies;
    }

    /**
     * 적용 가능한 할인 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용 가능한 할인 정책 목록
     */
    public List<Event> getAvailableSalePolicies(Order order) {
        List<Event> events = new ArrayList<>();
        for (Event giftPolicy : salePolicies) {
            if (giftPolicy.isAvailable(order)) {
                events.add(giftPolicy);
            }
        }
        return events;
    }

    /**
     * 적용 가능한 할인 정책을 적용하고 적용한 할인 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용한 할인 정책 목록
     */
    public List<Event<Sale>> applyAndGetAppliedSalePolicies(Order order) {
        List<Event<Sale>> events = new ArrayList<>();
        for (Event<Sale> salePolicy : salePolicies) {
            if (salePolicy.isAvailable(order)) {
                salePolicy.apply(order);
                events.add(salePolicy);
            }
        }
        return events;
    }

}
