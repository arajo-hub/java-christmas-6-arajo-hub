package christmas;

import christmas.event.*;
import christmas.event.badge.Badge;
import christmas.event.badge.BadgePolicy;
import christmas.event.gift.Gift;
import christmas.event.gift.GiftPolicy;
import christmas.event.sale.Sale;
import christmas.event.sale.SalePolicy;
import christmas.message.OrderErrorMessage;
import christmas.message.ReservationDateErrorMessage;
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

    private GiftPolicy giftPolicy;

    private SalePolicy salePolicy;

    private BadgePolicy badgePolicy;

    /**
     * 증정 정책, 할인 정책 기본으로 설정하고 생성
     * @param inputView 입력뷰
     * @param outputView 출력뷰
     */
    public EventPlanner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.giftPolicy = new GiftPolicy();
        this.salePolicy = new SalePolicy();
        this.badgePolicy = new BadgePolicy();
    }

    public void start() {
        outputView.greeting();

        int date = getReservationDate();
        LocalDate reservationDate = LocalDate.of(Year.now().getValue(), EventPlannerDetail.EVENT_MONTH, date);

        List<OrderMenu> orderMenus = getOrderMenus();

        outputView.printEventHeader(reservationDate);

        outputView.printOrderMenus(orderMenus);

        Order order = new Order(reservationDate, orderMenus);

        outputView.printPaymentAmountBeforeSale(order);

        List<Event<Gift>> giftEvents = giftPolicy.applyAndGetAppliedGiftPolicies(order);

        outputView.printGifts(giftEvents);

        List<Event<Sale>> saleEvents = salePolicy.applyAndGetAppliedSalePolicies(order);

        outputView.printSalesAndGifts(saleEvents, giftEvents);

        Benefit benefit = new Benefit(saleEvents, giftEvents);

        outputView.printTotalBenefitPrice(benefit.getTotalBenefit());

        order.setBenefit(benefit);

        outputView.printEstimatedPaymentAmountAfterSale(order.getPaymentAmountAfterSale());

        List<Event<Badge>> badgeEvents = badgePolicy.applyAndGetAppliedGiftPolicies(order);

        benefit.setBadges(badgeEvents);

        outputView.printBadges(badgeEvents);

    }

    /**
     * 예상 방문 일자를 입력받는다.
     * @return 방문 일자
     */
    private int getReservationDate() {
        while (true) {
            try {
                int reservationDate = inputView.readDate();
                if (ReservationDateValidator.isValid(reservationDate)) {
                    return reservationDate;
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
