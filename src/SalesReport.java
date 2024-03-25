public class SalesReport {
    private int burritosSold = 0;
    private int friesSold = 0;
    private int sodasSold = 0;
    private double burritosRevenue = 0.0;
    private double friesRevenue = 0.0;
    private double sodasRevenue = 0.0;
    private double totalSales = 0.0;

    private FriesStockManager friesStockManager;

    public SalesReport(FriesStockManager friesStockManager) {
        this.friesStockManager = friesStockManager;
    }

    public void updateSales(UserOrder userOrder) {
        for (FoodItem item : userOrder.getItems()) {
            switch (item.getName().toLowerCase()) {
                case "burrito":
                    burritosSold++;
                    burritosRevenue += item.getPrice();
                    break;
                case "fries":
                    friesSold++;
                    friesRevenue += item.getPrice();
                    break;
                case "soda":
                    sodasSold++;
                    sodasRevenue += item.getPrice();
                    break;
            }
        }
        totalSales = burritosRevenue + friesRevenue + sodasRevenue;
    }
    

    public void showSalesReport() {
        System.out.println("\n..................................");
        System.out.println("Unsold Servers of Fries: " + friesStockManager.getFriesStock());
        System.out.println("\nTotal Sales:");
        System.out.println("Burritos: " + burritosSold + "\t\t$" + String.format("%.2f", burritosRevenue));
        System.out.println("Fries: " + friesSold + "\t\t$" + String.format("%.2f", friesRevenue));
        System.out.println("Sodas: " + sodasSold + "\t\t$" + String.format("%.2f", sodasRevenue));
        System.out.println("..................................");
        System.out.println("Total: " + (burritosSold + friesSold + sodasSold) + "\t\t$" + String.format("%.2f", totalSales));
        System.out.println("..................................");
    }
}
