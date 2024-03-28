import java.util.ArrayList;
import java.util.List;
import menu.MenuItem;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private int mealCount = 0;
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

    public void addMeal(List<MenuItem> mealItems) {
        if (mealItems != null) {
            items.addAll(mealItems);
            mealCount++;
        }
    }

    public double calculateTotal() {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        total -= (mealDiscount * mealCount);
        return total;
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isMeal() {
        return mealCount > 0;
    }
}