package christmas.view;

import christmas.EventPlannerDetail;
import christmas.Order;
import christmas.OrderMenu;
import christmas.event.Event;
import christmas.event.Gift;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 안내문 출력 클래스(출력 순서에 따라 메서드를 추가 or 수정)
 */
public class OutputView {

    public String changeToMoneyFormat(int money) {
        return String.format("%,d원", money);
    }

    public void greeting() {
        System.out.println(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", EventPlannerDetail.EVENT_MONTH));
    }

    public void printEventHeader(LocalDate date) {
        System.out.println(String.format("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date.getMonth().getValue(), date.getDayOfMonth()));
        System.out.println();
    }

    public void printOrderMenus(List<OrderMenu> orderMenus) {
        System.out.println("<주문 메뉴>");
        changeToOrderMenusForPrintOrderMenus(orderMenus).forEach(System.out::println);
        System.out.println();
    }

    /**
     * 주문 메뉴에 출력하기 위해 주문 메뉴를 포맷화한다.
     * @param orderMenus 주문 메뉴 리스트
     * @return 포맷화된 주문 메뉴 리스트
     */
    private List<String> changeToOrderMenusForPrintOrderMenus(List<OrderMenu> orderMenus) {
        List<String> result = new ArrayList<>();
        for (OrderMenu orderMenu : orderMenus) {
            result.add(String.format("%s %d개", orderMenu.getMenu().getName(), orderMenu.getCount()));
        }
        return result;
    }

    public void printPaymentAmountBeforeSale(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(changeToMoneyFormat(order.getPaymentAmount()));
        System.out.println();
    }

    public void printGifts(List<Event<Gift>> events) {
        System.out.println("<증정 메뉴>");
        changeToGiftsForPrintGifts(events).forEach(System.out::println);
        System.out.println();
    }

    /**
     * 증정 메뉴에 출력하기 위해 증정 메뉴를 포맷화한다.
     * @param giftEvents 증정 이벤트 리스트
     * @return 포맷화된 증정 메뉴 리스트
     */
    private List<String> changeToGiftsForPrintGifts(List<Event<Gift>> giftEvents) {
        List<String> result = new ArrayList<>();
        if (giftEvents.isEmpty()) {
            result.add("없음");
            return result;
        }
        giftEvents.forEach(giftEvent -> {
            for (Gift gift : giftEvent.getCompensation()) {
                result.add(String.format("%s %d개", gift.getMenu().getName(), gift.getCount()));
            }
        });
        return result;
    }

}
