public class App {
    private static Helper helper = new Helper();
    private static MainMenu mainMenu = new MainMenu();

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        while (!exit) {
            mainMenu.displayMainMenu();
            String userInput = helper.getUserInput();
            switch (userInput) {
                case "a": // order
                    break;
                case "b": // sales report
                    break;
                case "c": // update item price
                    break;
                case "d": // check fry stock *addition*
                    break;
                case "e": // exit
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
