import java.util.Scanner;

public class OrderMenu {
    private Scanner scanner = new Scanner(System.in);
    private static Helper helper = new Helper();
    private static FriesStockManager friesStockManager = new FriesStockManager();
    // sales report
    // update price item

    public OrderMenu(FriesStockManager friesStockManager) {
        this.friesStockManager = friesStockManager;
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
                case 1: // Burrito
                    // add burrito
                    break;
                case 2: // Fries
                    // add fries
                    break;
                case 3: // Soda
                    // add soda
                    break;
                case 4: // Meal
                    // add meal
                    break;
                case 5: // No more
                    exit = true;
                    // process payment
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }

    // Todo: add item to order
    
    // Todo: add meal

    // Todo: process payment

    // Todo: calculate wait time
}