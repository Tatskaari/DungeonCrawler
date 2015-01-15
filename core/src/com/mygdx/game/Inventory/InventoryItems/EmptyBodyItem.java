package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.BodyItem;

/**
 * Created by Tatskaari on 15/01/2015.
 */
public class EmptyBodyItem extends BodyItem {
    @Override
    public float getDefenceRating() {
        return 0;
    }

    @Override
    public String getItemName() {
        return "empty-body-slot";
    }

    @Override
    public boolean isEmptyItem() {
        return true;
    }
}
