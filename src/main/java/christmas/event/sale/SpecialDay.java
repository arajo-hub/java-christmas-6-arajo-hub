package christmas.event.sale;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;

/**
 * 특별 할인 적용일(캘린더에 별 표시)
 */
public enum SpecialDay {

    DECEMBER_3(LocalDate.of(Year.now().getValue(), 12, 3)),
    DECEMBER_10(LocalDate.of(Year.now().getValue(), 12, 10)),
    DECEMBER_17(LocalDate.of(Year.now().getValue(), 12, 17)),
    DECEMBER_24(LocalDate.of(Year.now().getValue(), 12, 24)),
    DECEMBER_25(LocalDate.of(Year.now().getValue(), 12, 25)),
    DECEMBER_31(LocalDate.of(Year.now().getValue(), 12, 31)),
    ;

    private LocalDate date;

    SpecialDay(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public static boolean isSpecialDay(LocalDate date) {
        for (SpecialDay day : SpecialDay.values()) {
            if (day.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

}
