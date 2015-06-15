package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.ShieldItem;

public class RoundShieldItem extends ShieldItem {
    @Override
    public float getDefenceRating() {
        return 0.2f;
    }

    @Override
    public String getTextureName() {
        return "round-shield";
    }

    @Override
    public String getItemName() {
        return "Round Shield";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }
}
