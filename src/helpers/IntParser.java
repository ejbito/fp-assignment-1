package helpers;

import java.util.InputMismatchException;

public class IntParser implements InputParser<Integer> {
    @Override
    public Integer parse(String input) throws InputMismatchException {
        return Integer.parseInt(input);
    }
}