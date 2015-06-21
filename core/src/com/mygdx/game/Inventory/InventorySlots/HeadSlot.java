package com.mygdx.game.Inventory.InventorySlots;

import com.mygdx.game.Inventory.InventoryItems.EmptyHeadItem;
import com.mygdx.game.Inventory.ItemTypes.HeadItem;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.InventorySlot;

public class HeadSlot implements InventorySlot {


    private HeadItem item;

    public HeadSlot(){
        item = new EmptyHeadItem();
    }

    public boolean canTakeItem(InventoryItem inventoryItem) {
        return false;
    }

    @Override
    public void addItem(InventoryItem item) {
        throw new RuntimeException("Head inventory slot cannot take that item");
    }

    @Override
    public void replaceItem(InventoryItem item) {
        this.item = (HeadItem) item;
    }

    @Override
    public void emptySlot() {
        item = new EmptyHeadItem();
    }

    public InventoryItem getItem(){
        return item;
    }
}
