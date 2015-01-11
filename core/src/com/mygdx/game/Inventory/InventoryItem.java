package com.mygdx.game.Inventory;

import com.badlogic.gdx.utils.Array;

public interface InventoryItem {
    public String getItemName();

    public int getMaxStackSize();
    public Array<ItemContextAction> getItemContextActions();

}
