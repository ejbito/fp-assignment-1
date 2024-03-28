package interfaces;

import menu.MenuItem;

public interface IUpdatePrice {
    void updatePrice(String itemName, double newPrice);
    MenuItem getFoodItem(String itemName);
}
