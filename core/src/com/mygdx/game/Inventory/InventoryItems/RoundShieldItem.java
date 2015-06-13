package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.ShieldItem;

/**
 * Created by Tatskaari on 20/01/2015.
 */
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
