package christmas.eventplanner;

import christmas.event.*;
import christmas.event.badge.Badge;
import christmas.event.badge.BadgePolicy;
import christmas.event.badge.Policy;
import christmas.event.gift.Gift;
import christmas.event.gift.GiftPolicy;
import christmas.event.sale.Sale;
import christmas.event.sale.SalePolicy;
import christmas.message.OrderErrorMessage;
import christmas.message.ReservationDateErrorMessage;
import christmas.order.Benefit;
import christmas.order.Order;
import christmas.order.OrderMenu;
import christmas.validator.ReservationDateValidator;
import christmas.view.ErrorOutputView;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * 이벤트 플래너 클래스(이벤트 계획을 입력하면 그에 따른 정보 출력
 */
public class EventPlanner {

    private InputView inputView;

    private OutputView outputView;

    public EventPlanner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(GiftPolicy giftPolicy, SalePolicy salePolicy, BadgePolicy badgePolicy) {
        outputView.greeting();

        LocalDate reservationDate = getReservationDate();
        List<OrderMenu> orderMenus = getOrderMenus();

        outputView.printEventHeader(reservationDate);
        outputView.printOrderMenus(orderMenus);

        calculateBenefit(giftPolicy, salePolicy, badgePolicy, reservationDate, orderMenus);
    }

    private void calculateBenefit(GiftPolicy giftPolicy, SalePolicy salePolicy, BadgePolicy badgePolicy, LocalDate reservationDate, List<OrderMenu> orderMenus) {
        Order order = new Order(reservationDate, orderMenus);

        List<Event<Gift>> giftEvents = applyPolicies(giftPolicy, order);
        List<Event<Sale>> saleEvents = applyPolicies(salePolicy, order);

        Benefit benefit = new Benefit(saleEvents, giftEvents);
        order.setBenefit(benefit);

        List<Event<Badge>> badgeEvents = applyPolicies(badgePolicy, order);
        benefit.setBadges(badgeEvents);

        printBenefit(order, giftEvents, saleEvents, badgeEvents);
    }

    private void printBenefit(Order order, List<Event<Gift>> giftEvents, List<Event<Sale>> saleEvents, List<Event<Badge>> badgeEvents) {
        outputView.printPaymentAmountBeforeSale(order);
        outputView.printGifts(giftEvents);
        outputView.printSalesAndGifts(saleEvents, giftEvents);
        int totalBenefit = order.getPaymentAmountBeforeSale();
        if (order.getBenefit() != null) {
            totalBenefit = order.getBenefit().getTotalBenefit();
        }
        outputView.printTotalBenefitPrice(totalBenefit);
        outputView.printEstimatedPaymentAmountAfterSale(order.getPaymentAmountAfterSale());
        outputView.printBadges(badgeEvents);
    }

    private static <T> List<Event<T>> applyPolicies(Policy<T> policy, Order order) {
        List<Event<T>> events = new ArrayList<>();
        if (policy != null) {
            events = policy.applyAndGetAppliedPolicies(order);
        }
        return events;
    }

    /**
     * 예상 방문 일자를 입력받는다.
     * @return 방문 일자
     */
    private LocalDate getReservationDate() {
        while (true) {
            try {
                int reservationDate = inputView.readDate();
                if (ReservationDateValidator.isValid(reservationDate)) {
                    return LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, reservationDate);
                }
                throw new IllegalArgumentException(ReservationDateErrorMessage.INVALID_RESERVATION_DATE);
            } catch (IllegalArgumentException e) {
                ErrorOutputView.printError(e.getMessage());
            }
        }
    }

    /**
     * 메뉴와 개수를 입력받는다.
     * @return 주문한 메뉴 리스트
     */
    private List<OrderMenu> getOrderMenus() {
        while (true) {
            List<OrderMenu> menus = new ArrayList<>();
            String inputMenu = inputView.readMenu();
            String[] splitInputMenu = inputMenu.split(EventPlannerDetail.MENU_SEPARATOR);
            try {
                if (splitInputMenu.length == 0) {
                    throw new IllegalArgumentException(OrderErrorMessage.INVALID_ORDER);
                }

                for (int i = 0; i < splitInputMenu.length; i++) {
                    menus.add(new OrderMenu(splitInputMenu[i]));
                }

                return menus;
            } catch (IllegalArgumentException e) {
                ErrorOutputView.printError(e.getMessage());
            }
        }
    }

}
