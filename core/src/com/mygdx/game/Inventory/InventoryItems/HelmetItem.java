package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.HeadItem;

/**
 * Created by Tatskaari on 13/01/2015.
 */
public class HelmetItem extends HeadItem {

    @Override
    public String getItemName() {
        return "helmet";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }

    @Override
    public int getDefenceRating(){
        return 3;
    }
}
