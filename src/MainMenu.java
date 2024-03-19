import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        System.out.println("\n===============================================================");
        System.out.println("Burrito King");
        System.out.println("===============================================================");
        System.out.println("a) Order");
        System.out.println("b) Show sales report");
        System.out.println("c) Update prices");
        System.out.println("d) Check fry stock");
        System.out.println("e) Exit");
        System.out.print("Please select: ");
    }

    public String getUserInput() {
        return scanner.next();
    }

    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next();
            }
        }
    }
}
