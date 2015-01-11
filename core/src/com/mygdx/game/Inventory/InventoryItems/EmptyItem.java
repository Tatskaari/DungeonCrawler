package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.InventoryItem;
import com.mygdx.game.Inventory.ItemContextAction;

public class EmptyItem implements InventoryItem {
    Array<ItemContextAction> itemActions;

    public EmptyItem(){
        itemActions = new Array<ItemContextAction>();
    }

    @Override
    public String getItemName() {
        return "rounded";
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemActions;
    }
}