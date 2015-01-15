package com.mygdx.game.Inventory.ItemTypes;

/**
 * Created by Tatskaari on 15/01/2015.
 */
public abstract class ArmourItem implements InventoryItem {
    public abstract float getDefenceRating();

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
