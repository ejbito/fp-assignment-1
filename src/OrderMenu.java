public class OrderMenu {
    private Helper helper = new Helper();
    private FriesStockManager friesStockManager = new FriesStockManager();
    private UpdatePrice updatePrice = new UpdatePrice();
    private SalesReport salesReport;

    public OrderMenu(SalesReport salesReport) {
        this.salesReport = salesReport;
    }

    public void orderMenu() {
        UserOrder userOrder = new UserOrder();
        boolean exit = false;
        while (!exit) {
            System.out.println("\n> Select the food item");
            System.out.println("1. Burrito");
            System.out.println("2. Fries");
            System.out.println("3. Soda");
            System.out.println("4. Meal (1 Burrito, 1 Fries, 1 Soda)");
            System.out.println("5. No more");
            int userInput = helper.getIntInput("Please select: ");
            switch (userInput) {
                case 1:
                    addItem(userOrder, "Burrito");
                    break;
                case 2:
                    addItem(userOrder, "Fries");
                    break;
                case 3:
                    addItem(userOrder, "Soda");
                    break;
                case 4:
                    addMeal(userOrder);
                    break;
                case 5:
                    exit = true;
                    processPayment(userOrder);
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    private void updateFriesStockAfterCalculation(int friesCount) {
        int currentStock = friesStockManager.getFriesStock();
        if (friesCount <= currentStock) {
            friesStockManager.updateFriesStock(-friesCount);
        } else {
            int friesNeeded = friesCount - currentStock;
            int batchesToCook = (int)Math.ceil((double) friesNeeded / 5);
            friesStockManager.updateFriesStock((batchesToCook * 5) - friesCount);
        }
    }

    private int calculateFriesTime(int friesCount) {
        int currentStock = friesStockManager.getFriesStock();
        int additionalFriesNeeded = friesCount - currentStock;
        if (additionalFriesNeeded > 0) {
            int batchesNeeded = (int) Math.ceil((double) additionalFriesNeeded / 5);
            return batchesNeeded * 8;
        }
        return 0;
    }

    private void addItem(UserOrder userOrder, String itemName) {
        FoodItem item = updatePrice.getFoodItem(itemName);
        if (item != null) {
            int itemCount = helper.getIntInput("How many " + itemName.toLowerCase() + "s would you like to buy: ");
            for (int i = 0; i < itemCount; i++) {
                userOrder.addItem(new FoodItem(item.getName(), item.getPrice()));
            }
            if ("Fries".equals(itemName)) {
                calculateFriesTime(itemCount);
            }
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }
    
    private void addMeal(UserOrder userOrder) {
        FoodItem burrito = updatePrice.getFoodItem("Burrito");
        FoodItem fries = updatePrice.getFoodItem("Fries");
        FoodItem soda = updatePrice.getFoodItem("Soda");
    
        if (burrito != null && fries != null && soda != null) {
            userOrder.addItem(burrito);
            userOrder.addItem(fries);
            userOrder.addItem(soda);
            friesStockManager.updateFriesStock(-1);
            userOrder.makeItAMeal();
            System.out.println("Meal added to your order.");
        } else {
            System.out.println("Unable to add meal to order. Item(s) not found.");
        }
    }

    private void processPayment(UserOrder userOrder) {
        double total = userOrder.calculateTotal();
        System.out.println("\nTotal for your order is $" + String.format("%.2f", total));
        System.out.print("Please enter amount paid: $");
        double paidAmount = helper.getDoubleInput("");
        while (paidAmount < total) {
            System.out.println("Insufficient payment. The total is $" + String.format("%.2f", total));
            System.out.print("Please enter amount paid: ");
            paidAmount = helper.getDoubleInput("");
        }
        System.out.println("Change returned: $" + String.format("%.2f", paidAmount - total));
        
        salesReport.updateSales(userOrder);
        
        displayWaitTime(userOrder);
    }

    public void displayWaitTime(UserOrder userOrder) {
        int burritoCount = 0;
        int friesCount = 0;
        for (FoodItem item : userOrder.getItems()) {
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
    
        updateFriesStockAfterCalculation(friesCount);
    
        System.out.println(friesStockManager.getFriesStock() + " serves of fries will be left for next order.");
    }
}