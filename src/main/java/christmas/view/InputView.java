package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.EventDetail;
import christmas.message.ReservationDateErrorMessage;

/**
 * 사용자로부터 입력을 받는 클래스
 */
public class InputView {

    public int readDate() throws IllegalArgumentException {
        System.out.println(String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)", EventDetail.EVENT_MONTH));
        String input = Console.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ReservationDateErrorMessage.INVALID_RESERVATION_DATE);
        }
    }

}
