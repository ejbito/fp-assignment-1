public class App {
    private static Helper helper = new Helper();
    private static MainMenu mainMenu = new MainMenu();
    private static OrderMenu orderMenu = new OrderMenu();
    private static UpdatePrice updatePrice = new UpdatePrice();
    private static FriesStockManager friesStockManager = new FriesStockManager();

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        while (!exit) {
            mainMenu.displayMainMenu();
            String userInput = helper.getUserInput();
            switch (userInput) {
                case "a":
                    orderMenu.orderMenu();
                    break;
                case "b":
                    // todo : implement sales report
                    break;
                case "c":
                    updatePrice.updatePriceMenu();
                    break;
                case "d": // check fry stock *for testing*
                    friesStockManager.checkFriesStock();
                    break;
                case "e":
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
