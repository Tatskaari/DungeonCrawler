package com.mygdx.game.Inventory.InventorySlots;

import com.mygdx.game.Inventory.InventoryItems.EmptyBodyItem;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.ItemTypes.BodyItem;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

/**
 * Created by Tatskaari on 15/01/2015.
 */
public class BodySlot implements InventorySlot {
    private BodyItem item;

    public BodySlot(){
        emptySlot();
    }

    @Override
    public InventoryItem getItem() {
        return item;
    }

    @Override
    public boolean canTakeItem(InventoryItem item) {
        return false;
    }

    @Override
    public void addItem(InventoryItem item) {
        throw new RuntimeException("Body inventory slot cannot take that item");
    }

    @Override
    public void replaceItem(InventoryItem item) {
        this.item = (BodyItem) item;
    }

    @Override
    public void emptySlot() {
        item = new EmptyBodyItem();
    }
}
