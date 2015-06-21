package com.mygdx.game.Inventory;

import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public interface InventorySlot {
    public InventoryItem getItem();
    public boolean canTakeItem(InventoryItem item);
    public void addItem(InventoryItem item);
    public void replaceItem(InventoryItem item);
    public void emptySlot();
}
