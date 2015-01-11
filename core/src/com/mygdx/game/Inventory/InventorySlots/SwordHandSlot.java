package com.mygdx.game.Inventory.InventorySlots;

import com.mygdx.game.Inventory.InventoryItem;
import com.mygdx.game.Inventory.InventoryItems.EmptySwordHandItem;
import com.mygdx.game.Inventory.InventorySlot;

public class SwordHandSlot implements InventorySlot {
    private EmptySwordHandItem item;

    public SwordHandSlot(){
        item = new EmptySwordHandItem();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        return false;
    }

    @Override
    public void addItem(InventoryItem item) {
        if (canTakeItem(item)){
            this.item = (EmptySwordHandItem) item;
        } else {
            throw new RuntimeException("Sword hand inventory slot cannot take that item");
        }
    }

    @Override
    public void replaceItem(InventoryItem item) {
        this.item = (EmptySwordHandItem) item;
    }

    @Override
    public void empty() {
        item = new EmptySwordHandItem();
    }

    public InventoryItem getItem(){
        return item;
    }

    public void equipSwordHandItem(EmptySwordHandItem item){
        this.item = item;
    }
}
