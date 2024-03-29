package menu;

public abstract class MenuItem {
    private String name;
    private double price;

    /**
     * Constructs a new MenuItem with the specified name and price.
     * 
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     */
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the name of the menu item.
     * 
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the menu item.
     * 
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets a new price for the menu item.
     * 
     * @param price The new price to be set for the menu item.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
