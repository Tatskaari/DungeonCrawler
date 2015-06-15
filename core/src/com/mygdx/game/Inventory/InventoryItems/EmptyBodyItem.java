package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.BodyItem;

public class EmptyBodyItem extends BodyItem {
    private final Array<ItemContextAction> itemContextActions;

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
