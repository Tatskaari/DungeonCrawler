package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.ItemContextAction;

public class EmptyItem implements InventoryItem {
    private final Array<ItemContextAction> itemActions;

    public EmptyItem(){
        itemActions = new Array<ItemContextAction>();
    }

    @Override
    public String getItemName() {
        return "empty-slot";
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemActions;
    }

    @Override
    public boolean isEmptyItem() {
        return true;
    }
}