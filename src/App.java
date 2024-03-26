import helper.Helper;

public class App {
    private static final Helper helper = new Helper();
    private static final MainMenu mainMenu = new MainMenu();
    private static final UpdatePrice updatePrice = new UpdatePrice();
    private static final StockManager stockManager = new StockManager();
    private static final SalesReport salesReport = new SalesReport(stockManager);
    private static final OrderMenu orderMenu = new OrderMenu(salesReport);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            mainMenu.displayMainMenu();
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
