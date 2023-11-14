package christmas.event.badge;

import christmas.event.Event;
import christmas.order.Order;
import java.util.List;

public interface Policy<T> {

    List<Event> getAvailablePolicies(Order order);

    List<Event<T>> applyAndGetAppliedPolicies(Order order);

}
