import menu.MenuItem;

public class SalesReport {
    private int burritosSold = 0;
    private int friesSold = 0;
    private int sodasSold = 0;
    private int mealsSold = 0;
    private double burritosRevenue = 0.0;
    private double friesRevenue = 0.0;
    private double sodasRevenue = 0.0;
    private double totalSales = 0.0;

    private StockManager stockManager;

    /**
     * Initialises a new SalesReport with a reference to the StockManager.
     * 
     * @param stockManager The StockManager instance that tracks stock levels of items.
     */
    public SalesReport(StockManager stockManager) {
        this.stockManager = stockManager;
    }

    /**
     * Updates the sales report based on the contents of the given order. It tallies the items sold,
     * calculates revenue from each item type, and updates the total sales. If items are part of a meal,
     * they are discounted accordingly.
     * 
     * @param order The order from which sales data will be updated. The method checks if an order is a meal
     * and applies discounts and tallies accordingly.
     */
    public void updateSales(Order order) {
        for (MenuItem item : order.getItems()) {
            switch (item.getName().toLowerCase()) {
                case "burrito":
                    if (order.isMeal()) {
                        burritosSold++;
                        mealsSold++;
                        burritosRevenue += item.getPrice() - 1;

                    } else {
                        burritosSold++;
                        burritosRevenue += item.getPrice();
                    }
                    break;
                case "fries":
                    if (order.isMeal()) {
                        friesSold++;
                        friesRevenue += item.getPrice() - 1;
                    } else {
                        friesSold++;
                        friesRevenue += item.getPrice();
                    }
                    break;
                case "soda":
                    if (order.isMeal()) {
                        sodasSold++;
                        sodasRevenue += item.getPrice() - 1;
                    } else {
                        sodasSold++;
                        sodasRevenue += item.getPrice();
                    }
                    break;
            }
        }
        totalSales = burritosRevenue + friesRevenue + sodasRevenue;
    }

    /**
     * Displays a summary of the sales report. It includes the unsold servers of fries, the total number of
     * each item sold, revenue generated from each item, and the total sales. It also reports the total number
     * of meals sold.
     */
    public void showSalesReport() {
        System.out.println("\n..................................");
        System.out.println("Unsold Servers of Fries: " + stockManager.getFriesStock());
        System.out.println("\nTotal Sales:");
        System.out.println("Burritos: " + burritosSold + "\t\t$" + String.format("%.2f", burritosRevenue));
        System.out.println("Fries: " + friesSold + "\t\t$" + String.format("%.2f", friesRevenue));
        System.out.println("Sodas: " + sodasSold + "\t\t$" + String.format("%.2f", sodasRevenue));
        System.out.println("Meals: " + mealsSold);
        System.out.println("..................................");
        System.out.println("Total: " + (burritosSold + friesSold + sodasSold) + "\t\t$" + String.format("%.2f", totalSales));
        System.out.println("..................................");
    }
}
