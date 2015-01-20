package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.BodyItem;

/**
 * Created by Tatskaari on 15/01/2015.
 */
public class EmptyBodyItem extends BodyItem {
    Array<ItemContextAction> itemContextActions;

    public EmptyBodyItem(){
        itemContextActions = new Array<ItemContextAction>();
    }

    @Override
    public float getDefenceRating() {
        return 0;
    }

    @Override
    public String getTextureName() {
        return "empty-body-slot";
    }

    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }

    @Override
    public boolean isEmptyItem() {
        return true;
    }
}
