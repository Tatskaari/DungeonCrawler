package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

public interface InventoryItem {
    public abstract String getTextureName();
    public abstract String getItemName();

    public abstract int getMaxStackSize();
    public abstract Array<ItemContextAction> getItemContextActions();

    public abstract boolean isEmptyItem();

}
