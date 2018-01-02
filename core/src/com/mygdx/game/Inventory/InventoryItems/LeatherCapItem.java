package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.HeadItem;

public class LeatherCapItem extends HeadItem {
    @Override
    public String getTextureName() {
        return "leather-cap";
    }

    @Override
    public String getItemName() {
        return "Leather Cap";
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
