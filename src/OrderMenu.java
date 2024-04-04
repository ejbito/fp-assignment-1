import java.util.ArrayList;
import java.util.List;

import helpers.DoubleParser;
import helpers.Helper;
import helpers.IntParser;
import menu.Burrito;
import menu.Fries;
import menu.MenuItem;
import menu.Soda;

public class OrderMenu {
    private Helper helper = new Helper();
    private StockManager stockManager;
    private UpdatePrice updatePrice;
    private SalesReport salesReport;

    /**
     * Initialises a new OrderMenu with a link to a SalesReport.
     * 
     * @param salesReport The SalesReport object that will be updated with sales information from orders.
     */
    public OrderMenu(StockManager stockManager, SalesReport salesReport, UpdatePrice updatePrice) {
        this.stockManager = stockManager;
        this.salesReport = salesReport;
        this.updatePrice = updatePrice;
    }

    /**
     * Starts the order menu process, allowing users to select and order menu items until they choose to exit.
     * Users can order individual items, meals, or choose to complete their order.
     */
    public void displayOrderMenu() {
        Order order = new Order(3.00);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n> Select the food item");
            System.out.println("1. Burrito");
            System.out.println("2. Fries");
            System.out.println("3. Soda");
            System.out.println("4. Meal (1 Burrito, 1 Fries, 1 Soda)");
            System.out.println("5. No more");
            int userInput = helper.getInput("Please select: ", new IntParser());
            switch (userInput) {
                case 1:
                    addItem(order, "Burrito");
                    break;
                case 2:
                    addItem(order, "Fries");
                    break;
                case 3:
                    addItem(order, "Soda");
                    break;
                case 4:
                    addMeal(order);
                    break;
                case 5:
                    exit = true;
                    processPayment(order);
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    /**
     * Updates the stock for fries based on the number ordered. If there's not enough stock, it calculates
     * how many additional fries need to be cooked.
     * 
     * @param friesCount The number of fries being ordered.
     */
    private void updateFriesStock(int friesCount) {
        int currentStock = stockManager.getFriesStock();
        if (friesCount <= currentStock) {
            stockManager.updateStock(-friesCount);
        } else {
            int friesNeeded = friesCount - currentStock;
            int batchesToCook = (int)Math.ceil((double) friesNeeded / 5);
            stockManager.updateStock((batchesToCook * 5) - friesCount);
        }
    }

    /**
     * Calculates the time needed to cook a given number of fries, based on the current stock and the number ordered.
     * Assumes fries are cooked in batches of 5, with each batch taking 8 minutes to cook.
     * 
     * @param friesCount The number of fries being ordered.
     * @return The time in minutes required to cook the fries if additional fries are needed beyond the current stock.
     */
    private int calculateFriesTime(int friesCount) {
        int currentStock = stockManager.getFriesStock();
        int additionalFriesNeeded = friesCount - currentStock;
        if (additionalFriesNeeded > 0) {
            int batchesNeeded = (int) Math.ceil((double) additionalFriesNeeded / 5);
            return batchesNeeded * 8;
        }
        return 0;
    }

    /**
     * Adds a specified number of a given menu item to the order. If the item is "Fries", it also calculates
     * the cooking time based on the order quantity.
     * 
     * @param order The current order to which the item should be added.
     * @param itemName The name of the item to be added (e.g., "Burrito", "Fries", "Soda").
     */
    private void addItem(Order order, String itemName) {
        MenuItem item = null;
        int itemCount = helper.getInput("How many " + itemName.toLowerCase() + "s would you like to buy: ", new IntParser());
        while (itemCount < 0) {
            System.out.println("Invalid number of items. Please enter a positive number.");
            itemCount = helper.getInput("How many " + itemName.toLowerCase() + "s would you like to buy: ", new IntParser());
        }
        switch (itemName) {
            case "Burrito":
                item = new Burrito(updatePrice.getFoodItem(itemName).getPrice());
                break;
            case "Fries":
                item = new Fries(updatePrice.getFoodItem(itemName).getPrice());
                calculateFriesTime(itemCount);
                break;
            case "Soda":
                item = new Soda(updatePrice.getFoodItem(itemName).getPrice());
                break;
            default:
                System.out.println("Item not found: " + itemName);
                return;
        }
        for (int i = 0; i < itemCount; i++) {
            order.addItem(item);
        }
    }

    /**
     * Adds a predefined meal (1 Burrito, 1 Fries, 1 Soda) to the order and prints a confirmation message.
     * 
     * @param order The current order to which the meal should be added.
     */
    private void addMeal(Order order) {
        List<MenuItem> mealItems = new ArrayList<>();
        mealItems.add(new Burrito(updatePrice.getFoodItem("Burrito").getPrice()));
        mealItems.add(new Fries(updatePrice.getFoodItem("Fries").getPrice()));
        mealItems.add(new Soda(updatePrice.getFoodItem("Soda").getPrice()));
        order.addMeal(mealItems);
        System.out.println("Meal added to your order.");
    }

    /**
     * Processes the payment for the current order, prints the total, collects payment from the user,
     * calculates and prints the change, updates the sales report, and displays the wait time.
     * 
     * @param order The order for which payment is being processed.
     */
    private void processPayment(Order order) {
        double total = order.calculateTotal();
        System.out.println("\nTotal for your order is $" + String.format("%.2f", total));
        System.out.print("Please enter amount paid: $");
        double paidAmount = helper.getInput("", new DoubleParser());
        while (paidAmount < total) {
            System.out.println("Insufficient payment. The total is $" + String.format("%.2f", total));
            System.out.print("Please enter amount paid: ");
            paidAmount = helper.getInput("", new DoubleParser());
        }
        System.out.println("Change returned: $" + String.format("%.2f", paidAmount - total));
        
        salesReport.updateSales(order);
        
        displayWaitTime(order);
    }

    /**
     * Displays the estimated wait time for the current order based on the items ordered.
     * Calculates wait times for burritos and fries separately and uses the maximum of the two as the total wait time.
     * Also updates the fries stock based on the order.
     * 
     * @param order The order for which the wait time is being calculated.
     */
    private void displayWaitTime(Order order) {
        int burritoCount = 0;
        int friesCount = 0;
        for (MenuItem item : order.getItems()) {
            if ("Burrito".equalsIgnoreCase(item.getName())) {
                burritoCount++;
            } else if ("Fries".equalsIgnoreCase(item.getName())) {
                friesCount++;
            }
        }
        int burritoBatches = (int) Math.ceil(burritoCount / 2.0);
        int burritoTime = burritoBatches * 9;
        int friesTime = calculateFriesTime(friesCount);
        int totalWaitTime = Math.max(burritoTime, friesTime);
        System.out.println("\nEstimated wait time for your order is: " + totalWaitTime + " minutes.");
        updateFriesStock(friesCount);
        System.out.println(stockManager.getFriesStock() + " serves of fries will be left for next order.");
    }
}