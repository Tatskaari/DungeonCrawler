package com.mygdx.game.Inventory.InventorySlots;


import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.InventoryItems.EmptyItem;
import com.mygdx.game.Inventory.InventorySlot;

public class StandardSlot implements InventorySlot{
    private InventoryItem item;
    private int itemCount = 0;

    public StandardSlot() {
        item = new EmptyItem();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        if (item.isEmptyItem()) {
            return true;
        } else if (item.getClass().equals(inventoryItem.getClass())) {
            return item.getMaxStackSize() < itemCount;
        } else {
            return false;
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
    public void emptySlot() {
        item = new EmptyItem();
        itemCount = 0;
    }

    public InventoryItem getItem(){
        return item;
    }
}
