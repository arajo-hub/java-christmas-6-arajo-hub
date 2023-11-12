package christmas;

import christmas.event.Event;
import christmas.event.Gift;
import christmas.event.Sale;

import java.util.List;

/**
 * 혜택
 */
public class Benefit {

    private List<Event<Sale>> sales;
    private List<Event<Gift>> gifts;

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

}
