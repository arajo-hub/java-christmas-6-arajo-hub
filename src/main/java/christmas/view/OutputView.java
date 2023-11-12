package christmas.view;

import christmas.EventDetail;

import java.time.LocalDate;

/**
 * 안내문 출력 클래스(출력 순서에 따라 메서드를 추가 or 수정)
 */
public class OutputView {

    public void greeting() {
        System.out.println(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", EventDetail.EVENT_MONTH));
    }

    public void printEventHeader(LocalDate date) {
        System.out.println(String.format("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date.getMonth().getValue(), date.getDayOfMonth()));
    }

}
