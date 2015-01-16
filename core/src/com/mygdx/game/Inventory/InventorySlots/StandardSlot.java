package com.mygdx.game.Inventory.InventorySlots;


import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.InventoryItems.EmptyItem;
import com.mygdx.game.Inventory.InventorySlot;

public class StandardSlot implements InventorySlot{
    private InventoryItem item;
    private int itemCount = 1;

    public StandardSlot() {
        item = new EmptyItem();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        if (item instanceof EmptyItem) {
            return true;
        } else {
            return item.getMaxStackSize() < itemCount;
        }
    }

    public void addItem(InventoryItem inventoryItem) {
        if (canTakeItem(inventoryItem)){
            item = inventoryItem;
            itemCount++;
        } else {
            throw new RuntimeException("Cannot add that item to this slot.");
        }
    }

    @Override
    public void replaceItem(InventoryItem item) {
        this.item = item;
    }

    @Override
    public void empty() {
        item = new EmptyItem();
    }

    public InventoryItem getItem(){
        return item;
    }
}
