package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.HeadItem;

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
