package com.mygdx.game.Inventory.InventorySlots;

import com.mygdx.game.Inventory.InventoryItems.EmptyShieldItem;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.ItemTypes.OffHandItem;

public class OffHandSlot implements InventorySlot {


    private OffHandItem item;

    public OffHandSlot(){
        item = new EmptyShieldItem();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        return false;
    }

    @Override
    public void addItem(InventoryItem item) {
        throw new RuntimeException("Shield inventory slot cannot take that item");
    }

    @Override
    public void replaceItem(InventoryItem item) {
        this.item = (OffHandItem) item;
    }

    @Override
    public void emptySlot() {
        item = new EmptyShieldItem();
    }

    public InventoryItem getItem(){
        return item;
    }
}
