package christmas.event.badge;

import christmas.Order;
import christmas.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 배지 정책
 */
public class BadgePolicy {

    private List<Event<Badge>> badgePolicies;

    public BadgePolicy(List<Event<Badge>> badgePolicies) {
        this.badgePolicies = badgePolicies;
    }

    /**
     * 적용 가능한 배지 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용 가능한 배지 정책 목록
     */
    public List<Event> getAvailableBadgePolicies(Order order) {
        List<Event> events = new ArrayList<>();
        for (Event badgePolicy : badgePolicies) {
            if (badgePolicy.isAvailable(order)) {
                events.add(badgePolicy);
            }
        }
        return events;
    }

    /**
     * 적용 가능한 배지 정책을 적용하고 적용한 배지 정책 목록을 반환한다.
     * @param order 주문
     * @return 적용한 배지 정책 목록
     */
    public List<Event<Badge>> applyAndGetAppliedGiftPolicies(Order order) {
        List<Event<Badge>> events = new ArrayList<>();
        for (Event<Badge> badgePolicy : badgePolicies) {
            if (badgePolicy.isAvailable(order)) {
                badgePolicy.apply(order);
                events.add(badgePolicy);
            }
        }
        return events;
    }

}
