package helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    private Scanner scanner = new Scanner(System.in);

    public <T> T getInput(String prompt, InputParser<T> parser) {
        while (true) {
            System.out.print(prompt);
            try {
                return parser.parse(scanner.next());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
                scanner.next();
            }
        }
    }

    public String getUserInput() {
        return scanner.next();
    }
}

interface InputParser<T> {
    T parse(String input) throws InputMismatchException;
}
