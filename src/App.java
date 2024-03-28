import helpers.Helper;

public class App {
    private final Helper helper = new Helper();
    private final UpdatePrice updatePrice;
    private final StockManager stockManager;
    private final SalesReport salesReport;
    private final OrderMenu orderMenu;

    public App() {
        this.stockManager = new StockManager();
        this.updatePrice = new UpdatePrice(helper);
        this.salesReport = new SalesReport(stockManager);
        this.orderMenu = new OrderMenu(salesReport);
    }

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

    public void startApp() {
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

    public static void main(String[] args) {
        App app = new App();
        app.startApp();
    }
}
