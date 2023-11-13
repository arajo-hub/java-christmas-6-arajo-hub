package christmas.order;

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
    private Benefit benefit;

    public Order(LocalDate reservationDate, List<OrderMenu> orderMenus) {
        if (!OrderValidator.isValid(orderMenus)) {
            throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER);
        }
        this.orderMenus = orderMenus;
        this.reservationDate = reservationDate;
        this.paymentAmount = orderMenus.stream().mapToInt(orderMenu -> orderMenu.getTotalPrice()).sum();
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public int getPaymentAmountBeforeSale() {
        return paymentAmount;
    }

    public int getPaymentAmountAfterSale() {
        if (this.benefit == null) {
            return paymentAmount;
        }
        return paymentAmount - this.benefit.getOnlySaleBenefit();
    }
}
