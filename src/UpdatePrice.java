import java.util.HashMap;
import java.util.Map;

import helpers.DoubleParser;
import helpers.Helper;
import helpers.IntParser;
import menu.Burrito;
import menu.Fries;
import menu.MenuItem;
import menu.Soda;
import interfaces.IUpdatePrice;

public class UpdatePrice implements IUpdatePrice {
    private final Helper helper;
    private final Map<String, MenuItem> items = new HashMap<>();

    public UpdatePrice(Helper helper) {
        this.helper = helper;
        initialiseItems();
    }

    private void initialiseItems() {
        items.put("Burrito", new Burrito(7.0));
        items.put("Fries", new Fries(4.0));
        items.put("Soda", new Soda(2.5));
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
                if (newPrice >= 0) {
                    updatePrice(itemName, newPrice);
                    System.out.println("The unit price of " + itemName.toLowerCase() + " is updated to $" + newPrice);
                } else {
                    System.out.println("Invalid price. Please enter a positive number.");
                }
            }
        }
    }

    @Override
    public void updatePrice(String itemName, double newPrice) {
        MenuItem item = items.get(itemName);
        if (item != null) {
            item.setPrice(newPrice);
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    @Override
    public MenuItem getFoodItem(String itemName) {
        return items.get(itemName);
    }
}
