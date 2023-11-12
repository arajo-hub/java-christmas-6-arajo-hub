package christmas.event.gift;

import christmas.Order;
import christmas.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 증정 정책
 */
public class GiftPolicy {

    private List<Event<Gift>> giftPolicies;

    /**
     * 증정 정책 기본 설정
     */
    public GiftPolicy() {
        this.giftPolicies = new ArrayList<>();
        giftPolicies.add(new ChampagneEvent());
    }

    public GiftPolicy(List<Event<Gift>> giftPolicies) {
        this.giftPolicies = giftPolicies;
    }

    /**
     * 적용 가능한 증정 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용 가능한 증정 정책 목록
     */
    public List<Event> getAvailableGiftPolicies(Order order) {
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
    public List<Event<Gift>> applyAndGetAppliedGiftPolicies(Order order) {
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
