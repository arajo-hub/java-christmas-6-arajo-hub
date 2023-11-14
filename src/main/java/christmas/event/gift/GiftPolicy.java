package christmas.event.gift;

import christmas.event.Policy;
import christmas.order.Order;
import christmas.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 증정 정책
 */
public class GiftPolicy implements Policy<Gift> {

    private List<Event<Gift>> giftPolicies;

    public GiftPolicy(List<Event<Gift>> giftPolicies) {
        this.giftPolicies = giftPolicies;
    }

    /**
     * 적용 가능한 증정 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용 가능한 증정 정책 목록
     */
    @Override
    public List<Event> getAvailablePolicies(Order order) {
        List<Event> events = new ArrayList<>();
        for (Event giftPolicy : giftPolicies) {
            if (giftPolicy.isAvailable(order)) {
                events.add(giftPolicy);
            }
        }
        return events;
    }

    /**
     * 적용 가능한 증정 정책을 적용하고 적용한 증정 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용한 증정 정책 목록
     */
    @Override
    public List<Event<Gift>> applyAndGetAppliedPolicies(Order order) {
        List<Event<Gift>> events = new ArrayList<>();
        for (Event<Gift> giftPolicy : giftPolicies) {
            if (giftPolicy.isAvailable(order)) {
                giftPolicy.apply(order);
                events.add(giftPolicy);
            }
        }
        return events;
    }

}
