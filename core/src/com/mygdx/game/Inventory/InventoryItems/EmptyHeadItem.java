package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.HeadItem;

/**
 * Created by Tatskaari on 13/01/2015.
 */
public class EmptyHeadItem extends HeadItem {
    private Array<ItemContextAction> contextActions;

    public EmptyHeadItem(){
        contextActions = new Array<ItemContextAction>();
    }
    @Override
    public float getDefenceRating() {
        return 0;
    }

    @Override
    public String getItemName() {
        return "empty-helmet-slot";
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return contextActions;
    }

    @Override
    public boolean isEmptyItem() {
        return true;
    }
}
