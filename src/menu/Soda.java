package menu;

public class Soda extends MenuItem {
    /**
     * Constructs a new Soda instance with the specified price.
     * Inherits from MenuItem, setting the name to "Soda".
     * 
     * @param price The price of the soda.
     */
    public Soda(double price) {
        super("Soda", price);
    }
}
