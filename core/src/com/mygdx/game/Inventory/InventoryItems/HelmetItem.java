package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.HeadItem;

/**
 * Created by Tatskaari on 13/01/2015.
 */
public class HelmetItem extends HeadItem {
    @Override
    public String getTextureName() {
        return "helmet";
    }

    @Override
    public String getItemName() {
        return "Helmet";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }

    @Override
    public float getDefenceRating(){
        return 0.1f;
    }
}
