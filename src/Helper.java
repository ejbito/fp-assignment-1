import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    private Scanner scanner = new Scanner(System.in);

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
