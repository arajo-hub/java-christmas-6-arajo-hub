package christmas.validatorTest;

import christmas.validator.ReservationDateValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ReservationDateValidatorTest {

    private ReservationDateValidator reservationDateValidator = new ReservationDateValidator();

    @Test
    void 예약날짜_1_정상() {
        int reservationDate = 1;
        assertTrue(reservationDateValidator.isValid(reservationDate));
    }

    /**
     * 이벤트가 12월일 경우, 마지막 날짜가 31로 정상 동작하나,
     * 2월 등 마지막 날짜가 31이 아닌 경우 테스트 코드에 사용된 예약 날짜를 수정해야 함.
     * @See ReservationDateValidator.isValid()
     */
    @Test
    void 예약날짜_31_정상() {
        int reservationDate = 31;
        assertTrue(reservationDateValidator.isValid(reservationDate));
    }

    /* 이하 예외 케이스 */

    @Test
    void 예약날짜_0_실패() {
        int reservationDate = 0;
        assertFalse(reservationDateValidator.isValid(reservationDate));
    }

    @Test
    void 예약날짜_음수_실패() {
        int reservationDate = -1;
        assertFalse(reservationDateValidator.isValid(reservationDate));
    }

    @Test
    void 존재하지_않는_예약날짜_실패() {
        int reservationDate = 500;
        assertFalse(reservationDateValidator.isValid(reservationDate));
    }

}
