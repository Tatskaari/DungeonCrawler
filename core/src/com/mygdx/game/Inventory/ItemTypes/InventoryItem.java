package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

public interface InventoryItem {
    public String getTextureName();
    public String getItemName();

    public int getMaxStackSize();
    public Array<ItemContextAction> getItemContextActions();

    public boolean isEmptyItem();

    public float getWorldScale();

}
