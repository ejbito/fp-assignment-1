import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<FoodItem> items = new ArrayList<>();
    private boolean isMeal = false;
    private static final double MEAL_DISCOUNT = 3.0;

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public void makeItAMeal() {
        this.isMeal = true;
    }

    public double calculateTotal() {
        double total = 0;
        for (FoodItem item : items) {
            total += item.getPrice();
        }
        if (isMeal) {
            total -= MEAL_DISCOUNT;
        }
        return total;
    }

    public List<FoodItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public boolean isMeal() {
        return this.isMeal;
    }
}
