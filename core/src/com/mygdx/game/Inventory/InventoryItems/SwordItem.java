package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.SwordHandItem;

public class SwordItem extends SwordHandItem {

    @Override
    public String getTextureName() {
        return "sword";
    }

    @Override
    public String getItemName() {
        return "Short Sword";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }

    @Override
    public int getAttackRating(){
        return 3;
    }
}
