package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.InventoryItem;

public class SwordItem implements InventoryItem {
    @Override
    public String getItemName() {
        return "sword";
    }

    @Override
    public void onClick() {
        System.out.println("Sword Item Clicked!");
    }
}
