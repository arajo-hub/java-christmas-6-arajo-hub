package christmas.validatorTest;

import christmas.validator.ReservationDateValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationDateValidatorTest {

    private ReservationDateValidator reservationDateValidator = new ReservationDateValidator();

    @DisplayName("예약 날짜가 1일 경우 정상")
    @Test
    void reservationDateOneTest() {
        int reservationDate = 1;
        assertTrue(reservationDateValidator.isValid(reservationDate));
    }

    /**
     * 이벤트가 12월일 경우, 마지막 날짜가 31로 정상 동작하나,
     * 2월 등 마지막 날짜가 31이 아닌 경우 테스트 코드에 사용된 예약 날짜를 수정해야 함.
     * @See ReservationDateValidator.isValid()
     */
    @DisplayName("예약 날짜가 31일 경우 정상")
    @Test
    void reservationDateThirtyOneTest() {
        int reservationDate = 31;
        assertTrue(reservationDateValidator.isValid(reservationDate));
    }

    /* 이하 예외 케이스 */

    @DisplayName("예약 날짜가 0일 경우 예외 발생")
    @Test
    void reservationDateZeroTest() {
        int reservationDate = 0;
        assertFalse(reservationDateValidator.isValid(reservationDate));
    }

    @DisplayName("예약 날짜가 음수일 경우 예외 발생")
    @Test
    void reservationDateNegativeTest() {
        int reservationDate = -1;
        assertFalse(reservationDateValidator.isValid(reservationDate));
    }

    @DisplayName("예약 날짜가 존재하지 않는 일자일 경우 예외 발생")
    @Test
    void reservationDateNotExistTest() {
        int reservationDate = 500;
        assertFalse(reservationDateValidator.isValid(reservationDate));
    }

}
