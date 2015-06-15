package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.Drop;
import com.mygdx.game.Inventory.ItemContextActions.Equip;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

public abstract class EquipableItem implements InventoryItem {
    private final Array<ItemContextAction> itemContextActions;

    EquipableItem(){
        itemContextActions = new Array<ItemContextAction>();

        itemContextActions.add(new Drop(this));
        itemContextActions.add(new Equip(this));
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }
}
