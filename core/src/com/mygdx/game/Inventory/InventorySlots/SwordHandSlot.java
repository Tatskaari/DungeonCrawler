package com.mygdx.game.Inventory.InventorySlots;

import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.InventoryItems.EmptySwordHandItem;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.ItemTypes.SwordHandItem;

public class SwordHandSlot implements InventorySlot {
    private SwordHandItem item;

    public SwordHandSlot(){
        item = new EmptySwordHandItem();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        return false;
    }

    @Override
    public void addItem(InventoryItem item) {
        throw new RuntimeException("Sword hand inventory slot cannot take that item");
    }

    @Override
    public void replaceItem(InventoryItem item) {
        this.item = (SwordHandItem) item;
    }

    @Override
    public void empty() {
        item = new EmptySwordHandItem();
    }

    public InventoryItem getItem(){
        return item;
    }
}
