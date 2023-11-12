package christmas;

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

    public EventPlanner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.greeting();

        int date = getReservationDate();
        LocalDate reservationDate = LocalDate.of(Year.now().getValue(), EventDetail.EVENT_MONTH, date);

        List<OrderMenu> orderMenus = getOrderMenus();

        outputView.printEventHeader(reservationDate);
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
            String[] splitInputMenu = inputMenu.split(EventDetail.MENU_SEPARATOR);
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
