package christmas;

import christmas.event.Event;
import christmas.event.badge.BadgeEvent;
import christmas.event.badge.BadgePolicy;
import christmas.event.gift.ChampagneEvent;
import christmas.event.gift.GiftPolicy;
import christmas.event.sale.*;
import christmas.eventplanner.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 증정품 정책 설정
        GiftPolicy giftPolicy = new GiftPolicy(List.of(new ChampagneEvent()));

        // 할인 정책 설정
        List<Event<Sale>> saleEvents = List.of(new ChristmasDDayEvent(),
                new WeekdayEvent(),
                new WeekendEvent(),
                new SpecialDayEvent());
        SalePolicy salePolicy = new SalePolicy(saleEvents);

        // 배지 정책 설정
        BadgePolicy badgePolicy = new BadgePolicy(List.of(new BadgeEvent()));

        EventPlanner eventPlanner = new EventPlanner(new InputView(), new OutputView());
        eventPlanner.start(giftPolicy, salePolicy, badgePolicy);
    }
}
