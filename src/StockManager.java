public class StockManager {
    private int friesStock = 0;

    public void checkFriesStock() {
        System.out.println("Current fries stock in warmer: " + friesStock);
    }

    public int getFriesStock() {
        return friesStock;
    }

    public void updateFriesStock(int amount) {
        friesStock += amount;
        if (friesStock < 0) {
            friesStock = 0;
        }
    }
}