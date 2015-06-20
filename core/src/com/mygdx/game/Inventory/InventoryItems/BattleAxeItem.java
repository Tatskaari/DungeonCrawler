package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.SwordHandItem;


public class BattleAxeItem extends SwordHandItem {

    @Override
    public String getTextureName() {
        return "battle-axe";
    }

    @Override
    public String getItemName() {
        return "Battle Axe";
    }

    @Override
    public int getMaxStackSize() {
        return 1;
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
