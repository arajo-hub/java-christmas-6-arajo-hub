package christmas.validatorTest;

import christmas.validator.ReservationDateValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReservationDateValidatorTest {

    private ReservationDateValidator reservationDateValidator = new ReservationDateValidator();

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
