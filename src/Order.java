import java.util.ArrayList;
import java.util.List;
import menu.MenuItem;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private boolean isMeal = false;
    private double mealDiscount;

    public Order(double mealDiscount) {
        this.mealDiscount = mealDiscount;
    }

    public void addItem(MenuItem item) {
        if (item != null) {
            items.add(item);
        } else {
            System.out.println("Cannot add a null item to the order.");
        }
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
            total -= mealDiscount;
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
