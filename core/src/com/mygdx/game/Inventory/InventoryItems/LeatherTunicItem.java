package com.mygdx.game.Inventory.InventoryItems;

import com.mygdx.game.Inventory.ItemTypes.BodyItem;

/**
 * Created by Tatskaari on 15/01/2015.
 */
public class LeatherTunicItem extends BodyItem {
    @Override
    public float getDefenceRating() {
        return 0.3f;
    }

    @Override
    public String getTextureName() {
        return "leather-tunic";
    }

    @Override
    public String getItemName() {
        return "Leather Tunic";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }
}
