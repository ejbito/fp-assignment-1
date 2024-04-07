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
    private Helper helper;
    private Map<String, MenuItem> items = new HashMap<>();

    /**
     * Constructs an UpdatePrice object with a reference to a Helper object.
     * Initialises the list of menu items with their default prices.
     * 
     * @param helper The helper object used for input parsing.
     */
    public UpdatePrice(Helper helper) {
        this.helper = helper;
        initialiseItems();
    }

    /**
     * Initialises the menu items with their default prices. This method is called upon object creation
     * to populate the items map with initial values.
     */
    private void initialiseItems() {
        items.put("Burrito", new Burrito(7.0));
        items.put("Fries", new Fries(4.0));
        items.put("Soda", new Soda(2.5));
    }

    /**
     * Displays the menu for updating the prices of available food items. Allows the user to select an item
     * and input a new price for it. Validates the user input for both selection and new price value.
     */
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

    /**
     * Updates the price of a specified food item to a new value. If the item exists in the menu, its price
     * is updated; otherwise, a message indicating the item was not found is displayed.
     * 
     * @param itemName The name of the item whose price is to be updated.
     * @param newPrice The new price to be set for the item. Must be a non-negative value.
     */
    @Override
    public void updatePrice(String itemName, double newPrice) {
        MenuItem item = items.get(itemName);
        if (item != null) {
            item.setPrice(newPrice);
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    /**
     * Retrieves a MenuItem object by its name. If the item exists in the menu, the MenuItem object
     * is returned; otherwise, returns null.
     * 
     * @param itemName The name of the item to retrieve.
     * @return The MenuItem object associated with the given name, or null if the item does not exist.
     */
    @Override
    public MenuItem getFoodItem(String itemName) {
        return items.get(itemName);
    }
}
