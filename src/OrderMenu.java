import helpers.DoubleParser;
import helpers.Helper;
import helpers.IntParser;
import menu.Burrito;
import menu.Fries;
import menu.MenuItem;
import menu.Soda;

public class OrderMenu {
    private Helper helper = new Helper();
    private StockManager stockManager = new StockManager();
    private UpdatePrice updatePrice = new UpdatePrice(helper);
    private SalesReport salesReport;

    public OrderMenu(SalesReport salesReport) {
        this.salesReport = salesReport;
    }

    public void orderMenu() {
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

    private int calculateFriesTime(int friesCount) {
        int currentStock = stockManager.getFriesStock();
        int additionalFriesNeeded = friesCount - currentStock;
        if (additionalFriesNeeded > 0) {
            int batchesNeeded = (int) Math.ceil((double) additionalFriesNeeded / 5);
            return batchesNeeded * 8;
        }
        return 0;
    }

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

    
    private void addMeal(Order order) {
        MenuItem burrito = updatePrice.getFoodItem("Burrito");
        MenuItem fries = updatePrice.getFoodItem("Fries");
        MenuItem soda = updatePrice.getFoodItem("Soda");
    
        if (burrito != null && fries != null && soda != null) {
            order.addItem(burrito);
            order.addItem(fries);
            order.addItem(soda);
            stockManager.updateStock(-1);
            order.makeItAMeal();
            System.out.println("Meal added to your order.");
        } else {
            System.out.println("Unable to add meal to order. Item(s) not found.");
        }
    }

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