import helpers.Helper;

public class App {
    private static final Helper helper = new Helper();
    private static final UpdatePrice updatePrice = new UpdatePrice();
    private static final StockManager stockManager = new StockManager();
    private static final SalesReport salesReport = new SalesReport(stockManager);
    private static final OrderMenu orderMenu = new OrderMenu(salesReport);

    public static void displayMainMenu() {
        System.out.println("\n===============================================================");
        System.out.println("Burrito King");
        System.out.println("===============================================================");
        System.out.println("a) Order");
        System.out.println("b) Show sales report");
        System.out.println("c) Update prices");
        System.out.println("d) Exit");
        System.out.print("Please select: ");
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            String userInput = helper.getUserInput();
            switch (userInput) {
                case "a":
                    orderMenu.orderMenu();
                    break;
                case "b":
                    salesReport.showSalesReport();
                    break;
                case "c":
                    updatePrice.updatePriceMenu();
                    break;
                case "d":
                    System.out.println("\nExiting...\n");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }
    }
}
