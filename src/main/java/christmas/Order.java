package christmas;

import christmas.message.OrderErrorMessage;
import christmas.validator.OrderValidator;

import java.time.LocalDate;
import java.util.List;

/**
 * 주문 정보
 */
public class Order {

    private List<OrderMenu> orderMenus;
    private LocalDate reservationDate;
    private int paymentAmount;

    public Order(List<OrderMenu> orderMenus, LocalDate reservationDate, int paymentAmount) {
        if (!OrderValidator.isValid(orderMenus)) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER);
        }
        this.orderMenus = orderMenus;
        this.reservationDate = reservationDate;
        this.paymentAmount = paymentAmount;
    }

}
