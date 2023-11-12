package christmas.event.gift;

import christmas.enums.Menu;

/**
 * 증정품
 */
public class Gift {

    private Menu menu;
    private int count;

    public Gift(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

}
