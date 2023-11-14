package christmas.event.badge;

import christmas.event.Policy;
import christmas.order.Order;
import christmas.event.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 배지 정책
 */
public class BadgePolicy implements Policy<Badge> {

    private List<Event<Badge>> badgePolicies;

    public BadgePolicy(List<Event<Badge>> badgePolicies) {
        this.badgePolicies = badgePolicies;
    }

    @Override
    public List<Event> getAvailablePolicies(Order order) {
        List<Event> events = new ArrayList<>();
        for (Event badgePolicy : badgePolicies) {
            if (badgePolicy.isAvailable(order)) {
                events.add(badgePolicy);
            }
        }
        return events;
    }

    @Override
    public List<Event<Badge>> applyAndGetAppliedPolicies(Order order) {
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
