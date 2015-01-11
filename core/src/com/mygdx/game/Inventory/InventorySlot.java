package com.mygdx.game.Inventory;


import com.mygdx.game.Inventory.InventoryItems.EmptyItem;

public class InventorySlot {
    private InventoryItem item;
    private int itemCount = 1;

    public InventorySlot() {
        item = new EmptyItem();
    }

    public InventorySlot(InventoryItem item) {
        this.item = item;
    }

    public boolean hasItem(){
        if (item == null){
            return false;
        } else {
            return true;
        }
    }

    public String getItemName(){
        return item.getItemName();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        if (item instanceof EmptyItem) {
            return true;
        } else if(item.getMaxStackSize() < itemCount){
            return true;
        } else {
            return false;
        }
    }

    public void addItem(InventoryItem inventoryItem) {
        if (canTakeItem(inventoryItem)){
            item = inventoryItem;
        } else {
            throw new RuntimeException("Cannot add that item to this slot.");
        }
    }

    public InventoryItem getItem(){
        return item;
    }
}
