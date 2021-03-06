package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

public class EmptyItem implements InventoryItem {
    private final Array<ItemContextAction> itemActions;

    public EmptyItem(){
        itemActions = new Array<>();
    }

    @Override
    public String getTextureName() {
        return "empty-slot";
    }

    @Override
    public String getItemName() {
        return "";
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

    @Override
    public float getWorldScale() {
        return 0;
    }

}