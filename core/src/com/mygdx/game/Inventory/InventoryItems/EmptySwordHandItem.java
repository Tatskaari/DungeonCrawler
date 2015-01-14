package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.SwordHandItem;

public class EmptySwordHandItem extends SwordHandItem {
    private Array<ItemContextAction> itemContextActions;

    public EmptySwordHandItem(){
        itemContextActions = new Array<ItemContextAction>();
    }

    @Override
    public String getItemName() {
        return "empty-sword-hand-slot";
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
