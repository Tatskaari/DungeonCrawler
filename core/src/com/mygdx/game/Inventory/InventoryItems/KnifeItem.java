package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.OffHandSwordItem;

public class KnifeItem extends OffHandSwordItem{
    @Override
    public int getAttackRating() {
        return 1;
    }

    @Override
    public String getTextureName() {
        return "knife";
    }

    @Override
    public String getItemName() {
        return "Rusty Knife";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }
}
