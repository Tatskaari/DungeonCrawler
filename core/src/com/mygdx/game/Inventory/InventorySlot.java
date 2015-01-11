package com.mygdx.game.Inventory;

public interface InventorySlot {
    public InventoryItem getItem();
    public boolean canTakeItem(InventoryItem item);
    public void addItem(InventoryItem item);
    public void replaceItem(InventoryItem item);
    public void empty();
}
