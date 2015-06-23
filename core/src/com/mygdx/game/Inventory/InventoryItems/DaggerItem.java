package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.OffHandSwordItem;

public class DaggerItem extends OffHandSwordItem {
    @Override
    public int getAttackRating() {
        return 2;
    }

    @Override
    public String getTextureName() {
        return "dagger";
    }

    @Override
    public String getItemName() {
        return "Dagger";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }
}
