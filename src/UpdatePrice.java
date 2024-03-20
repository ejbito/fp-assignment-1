import java.util.HashMap;
import java.util.Map;

public class UpdatePrice {
    private static Map<String, FoodItem> items = new HashMap<>();
    private static Helper helper = new Helper();

    static {
        items.put("Burrito", new FoodItem("Burrito", 7.0));
        items.put("Fries", new FoodItem("Fries", 4.0));
        items.put("Soda", new FoodItem("Soda", 2.5));
    }

    public void updatePriceMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n> Select the food item to update the price");
            System.out.println("1. Burrito");
            System.out.println("2. Fries");
            System.out.println("3. Soda");
            System.out.println("4. Exit");
            System.out.print("Please select: ");

            int choice = helper.getIntInput("");
            String itemName = null;
            switch (choice) {
                case 1:
                    itemName = "Burrito";
                    break;
                case 2:
                    itemName = "Fries";
                    break;
                case 3:
                    itemName = "Soda";
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    continue;
            }
            if (!exit && itemName != null) {
                double newPrice = helper.getDoubleInput("Please enter new price: ");
                updatePrice(itemName, newPrice);
                System.out.println("The unit price of " + itemName.toLowerCase() + " is updated to $" + newPrice);
            }
        }
    }

    public void updatePrice(String name, double newPrice) {
        FoodItem item = items.get(name);
        if (item != null) {
            item.setPrice(newPrice);
        } else {
            System.out.println("Item not found: " + name);
        }
    }

    public FoodItem getFoodItem(String name) {
        return items.get(name);
    }
}