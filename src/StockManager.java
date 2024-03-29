import interfaces.IStockManager;

public class StockManager implements IStockManager {
    private int friesStock = 0;

    /**
     * Returns the current stock level of fries.
     * 
     * @return The current number of fries in stock.
     */
    public int getFriesStock() {
        return friesStock;
    }

    /**
     * Checks and displays the current stock level of fries. Implements the {@code checkStock} method
     * from the {@code IStockManager} interface.
     */
    @Override
    public void checkStock() {
        System.out.println("Current fries stock in warmer: " + friesStock);
    }

    /**
     * Updates the stock level of fries by a specified quantity. If the resulting stock level is negative,
     * it is reset to 0. Implements the {@code updateStock} method from the {@code IStockManager} interface.
     * 
     * @param quantity The quantity to add to the stock. This value can be negative, indicating a reduction in stock.
     */
    @Override
    public void updateStock(int quantity) {
        friesStock += quantity;
        if (friesStock < 0) {
            friesStock = 0;
        }
    }
}