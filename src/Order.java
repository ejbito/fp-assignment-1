import java.util.ArrayList;
import java.util.List;

import menu.MenuItem;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private boolean isMeal = false;
    private static final double MEAL_DISCOUNT = 3.0;

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void makeItAMeal() {
        this.isMeal = true;
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        if (isMeal) {
            total -= MEAL_DISCOUNT;
        }
        return total;
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public boolean isMeal() {
        return this.isMeal;
    }
}
