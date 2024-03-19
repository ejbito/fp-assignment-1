public class FriesStockManager {
    private int friesStock = 5;

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