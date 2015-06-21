package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.ShieldItem;

public class KiteShieldItem extends ShieldItem {

    @Override
    public float getDefenceRating() {
        return 0.3f;
    }

    @Override
    public String getTextureName() {
        return "kite-shield";
    }

    @Override
    public String getItemName() {
        return "Kite Shield";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }
}
