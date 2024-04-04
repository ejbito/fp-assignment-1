import helpers.Helper;

public class App {
    private final Helper helper = new Helper();
    private final UpdatePrice updatePrice;
    private final StockManager stockManager;
    private final SalesReport salesReport;
    private final OrderMenu orderMenu;

    /**
     * Initialises the application by creating instances of {@link StockManager}, {@link UpdatePrice},
     * {@link SalesReport}, and {@link OrderMenu} to handle various functionalities such as managing stock,
     * updating prices, reporting sales, and processing orders.
     */
    public App() {
        this.stockManager = new StockManager();
        this.updatePrice = new UpdatePrice(helper);
        this.salesReport = new SalesReport(stockManager);
        this.orderMenu = new OrderMenu(salesReport, updatePrice);
    }

    /**
     * Displays the main menu of the application to the user. The menu includes options for ordering food,
     * viewing the sales report, updating prices of menu items, and exiting the application.
     */
    public void displayMainMenu() {
        System.out.println("\n===============================================================");
        System.out.println("Burrito King");
        System.out.println("===============================================================");
        System.out.println("a) Order");
        System.out.println("b) Show sales report");
        System.out.println("c) Update prices");
        System.out.println("d) Exit");
        System.out.print("Please select: ");
    }

    /**
     * Starts the application, entering a loop that displays the main menu and processes user input to navigate
     * through the application's functionalities. The loop continues until the user chooses to exit the application.
     */
    public void startApp() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            String userInput = helper.getUserInput();
            switch (userInput) {
                case "a":
                    orderMenu.displayOrderMenu();
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

    /**
     * The main entry point for the application. Creates an instance of {@code App} and calls {@code startApp}
     * to run the application.
     * 
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        App app = new App();
        app.startApp();
    }
}
