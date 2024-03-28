import interfaces.IStockManager;

public class StockManager implements IStockManager {
    private int friesStock = 0;

    public int getFriesStock() {
        return friesStock;
    }

    @Override
    public void checkStock() {
        System.out.println("Current fries stock in warmer: " + friesStock);
    }

    @Override
    public void updateStock(int quantity) {
        friesStock += quantity;
        if (friesStock < 0) {
            friesStock = 0;
            System.out.println("Adjusted fries stock to 0 to prevent negative stock.");
        }
    }
}