import java.util.ArrayList;
import java.util.List;
import menu.MenuItem;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private int mealCount = 0;
    private double mealDiscount;

    /**
     * Constructs a new Order with a specified discount for meals.
     * 
     * @param mealDiscount The discount amount to be applied for each meal in the order.
     */
    public Order(double mealDiscount) {
        this.mealDiscount = mealDiscount;
    }

    /**
     * Adds a single menu item to the order. If the item is null, it prints an error message and does not add the item.
     * 
     * @param item The MenuItem to be added to the order. If null, the addition is ignored with an error message.
     */
    public void addItem(MenuItem item) {
        if (item != null) {
            items.add(item);
        } else {
            System.out.println("Cannot add a null item to the order.");
        }
    }

    /**
     * Adds a list of menu items as a meal to the order. Increments the meal count for discount calculation.
     * If the provided list is null, the method does nothing.
     * 
     * @param mealItems The list of MenuItem objects to be added as a meal to the order.
     */
    public void addMeal(List<MenuItem> mealItems) {
        if (mealItems != null) {
            items.addAll(mealItems);
            mealCount++;
        }
    }

    /**
     * Calculates the total cost of the order, applying discounts for meals.
     * 
     * @return The total cost of the order after applying meal discounts.
     */
    public double calculateTotal() {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        total -= (mealDiscount * mealCount);
        return total;
    }

    /**
     * Retrieves a list of all menu items in the order.
     * 
     * @return A new list containing all the items in the order. Modifications to this list do not affect the original order.
     */
    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Checks if the order includes any meals (defined as orders with a meal count greater than zero).
     * 
     * @return True if the order contains one or more meals, false otherwise.
     */
    public boolean isMeal() {
        return mealCount > 0;
    }
}