import java.util.HashMap;
import java.util.Map;

import helpers.DoubleParser;
import helpers.Helper;
import helpers.IntParser;
import menu.MenuItem;

public class UpdatePrice {
    private static Helper helper = new Helper();
    private static final Map<String, MenuItem> items = new HashMap<>();

    static {
        items.put("Burrito", new MenuItem("Burrito", 7.0));
        items.put("Fries", new MenuItem("Fries", 4.0));
        items.put("Soda", new MenuItem("Soda", 2.5));
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

            int choice = helper.getInput("", new IntParser());
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
                double newPrice = helper.getInput("Please enter new price: ", new DoubleParser());
                updatePrice(itemName, newPrice);
                System.out.println("The unit price of " + itemName.toLowerCase() + " is updated to $" + newPrice);
            }
        }
    }

    public void updatePrice(String name, double newPrice) {
        MenuItem item = items.get(name);
        if (item != null) {
            item.setPrice(newPrice);
        } else {
            System.out.println("Item not found: " + name);
        }
    }

    public MenuItem getFoodItem(String name) {
        return items.get(name);
    }
}
