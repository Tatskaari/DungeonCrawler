package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.ShieldItem;

public class EmptyShieldItem extends ShieldItem {
    private final Array<ItemContextAction> itemContextActions;

    public EmptyShieldItem(){
        itemContextActions = new Array<>();
    }

    @Override
    public float getDefenceRating() {
        return 0;
    }

    @Override
    public String getTextureName() {
        return "empty-shield-slot";
    }

    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public boolean isEmptyItem() {
        return true;
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }
}
