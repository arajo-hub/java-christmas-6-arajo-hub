package christmas.validator;

import christmas.EventDetail;

import java.time.Year;
import java.time.YearMonth;

/**
 * 예상 방문 일자를 검증하는 클래스
 */
public class ReservationDateValidator {

    public static boolean isValid(int date) {
        return date >= 1
                && date <= YearMonth.of(Year.now().getValue(), EventDetail.EVENT_MONTH).lengthOfMonth();
    }

}
