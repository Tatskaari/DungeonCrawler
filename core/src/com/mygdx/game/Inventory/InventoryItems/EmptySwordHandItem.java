package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.InventoryItem;
import com.mygdx.game.Inventory.ItemContextAction;

public class EmptySwordHandItem implements InventoryItem {
    private Array<ItemContextAction> itemContextActions;

    public EmptySwordHandItem(){
        itemContextActions = new Array<ItemContextAction>();
    }

    @Override
    public String getItemName() {
        return "empty-sword-hand-slot";
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }

    @Override
    public boolean isEmptyItem() {
        return true;
    }

    public int getAttackRating(){
        return 0;
    }
}
