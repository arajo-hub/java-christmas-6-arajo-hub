package christmas.event;

import christmas.enums.Menu;

/**
 * 할인
 */
public class Sale {

    private int discount;

    public Sale(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

}
