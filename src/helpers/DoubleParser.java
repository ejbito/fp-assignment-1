package helpers;

import java.util.InputMismatchException;

public class DoubleParser implements InputParser<Double> {
    @Override
    public Double parse(String input) throws InputMismatchException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
}