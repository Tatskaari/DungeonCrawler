package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.HeadItem;

public class EmptyHeadItem extends HeadItem {
    private final Array<ItemContextAction> contextActions;

    public EmptyHeadItem(){
        contextActions = new Array<>();
    }
    @Override
    public float getDefenceRating() {
        return 0;
    }

    @Override
    public String getTextureName() {
        return "empty-helmet-slot";
    }

    @Override
    public String getItemName() {
        return "";
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
