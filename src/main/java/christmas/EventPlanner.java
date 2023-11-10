package christmas;

import christmas.message.ReservationDateErrorMessage;
import christmas.validator.ReservationDateValidator;
import christmas.view.ErrorOutputView;
import christmas.view.InputView;
import christmas.view.OutputView;

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

}
