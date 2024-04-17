package helpers;

import java.util.InputMismatchException;

public class IntParser implements InputParser<Integer> {
    @Override
    public Integer parse(String input) throws InputMismatchException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}