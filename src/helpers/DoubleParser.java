package helpers;

import java.util.InputMismatchException;

public class DoubleParser implements InputParser<Double> {
    @Override
    public Double parse(String input) throws InputMismatchException {
        return Double.parseDouble(input);
    }
}