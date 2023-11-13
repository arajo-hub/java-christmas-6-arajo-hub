package christmas;

import christmas.event.Event;
import christmas.event.badge.Badge;
import christmas.event.badge.BadgeEvent;
import christmas.event.badge.BadgePolicy;
import christmas.event.gift.ChampagneEvent;
import christmas.event.gift.Gift;
import christmas.event.gift.GiftPolicy;
import christmas.event.sale.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 증정품 정책 설정
        List<Event<Gift>> giftEvents = List.of(new ChampagneEvent());
        GiftPolicy giftPolicy = new GiftPolicy(giftEvents);

        // 할인 정책 설정
        List<Event<Sale>> saleEvents = List.of(new ChristmasDDayEvent(),
                new WeekdayEvent(),
                new WeekendEvent(),
                new SpecialDayEvent());
        SalePolicy salePolicy = new SalePolicy(saleEvents);

        // 배지 정책 설정
        List<Event<Badge>> badgeEvents = List.of(new BadgeEvent());
        BadgePolicy badgePolicy = new BadgePolicy(badgeEvents);

        EventPlanner eventPlanner = new EventPlanner(new InputView(), new OutputView());
        eventPlanner.start(giftPolicy, salePolicy, badgePolicy);
    }
}
