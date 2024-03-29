package menu;

public class Burrito extends MenuItem {
    /**
     * Constructs a new Burrito instance with the specified price.
     * Inherits from MenuItem, setting the name to "Burrito".
     * 
     * @param price The price of the burrito.
     */
    public Burrito(double price) {
        super("Burrito", price);
    }
}
