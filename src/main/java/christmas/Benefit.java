package christmas;

import christmas.event.Event;
import christmas.event.badge.Badge;
import christmas.event.gift.Gift;
import christmas.event.sale.Sale;

import java.util.List;

/**
 * 혜택
 */
public class Benefit {

    private List<Event<Sale>> sales;
    private List<Event<Gift>> gifts;
    private List<Event<Badge>> badges;

    public Benefit(List<Event<Sale>> sales, List<Event<Gift>> gifts) {
        this.sales = sales;
        this.gifts = gifts;
    }

    public int getTotalBenefit() {
        int totalBenefit = 0;
        for (Event<Sale> sale : sales) {
            totalBenefit += sale.getCompensation().stream().mapToInt(Sale::getDiscount).sum();
        }
        for (Event<Gift> gift : gifts) {
            totalBenefit += gift.getCompensation().stream().mapToInt(g -> g.getMenu().getPrice() * g.getCount()).sum();
        }
        return totalBenefit;
    }

    public int getOnlySaleBenefit() {
        int totalBenefit = 0;
        for (Event<Sale> sale : sales) {
            totalBenefit += sale.getCompensation().stream().mapToInt(Sale::getDiscount).sum();
        }
        return totalBenefit;
    }

    public List<Event<Badge>> getBadges() {
        return badges;
    }

    public void setBadges(List<Event<Badge>> badges) {
        this.badges = badges;
    }

}
