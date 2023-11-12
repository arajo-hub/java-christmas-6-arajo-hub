package christmas.event;

import christmas.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * 이벤트
 */
public abstract class Event<T> {

    protected String name;
    protected List<T> compensation;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public abstract boolean isAvailable(Order order);

    public abstract void apply(Order order);

    public String getName() {
        return name;
    }

    public List<T> getCompensation() {
        return compensation;
    }

    /**
     * 날짜를 입력받아 이벤트 기간 내인지 확인한다.
     *
     * @param date 날짜
     * @return 이벤트 기간 내인지 여부
     */
    public boolean isInEventPeriod(LocalDate date) {
        return (startDate.isEqual(date) || startDate.isBefore(date))
                && (endDate.isEqual(date) || endDate.isAfter(date));
    }

}