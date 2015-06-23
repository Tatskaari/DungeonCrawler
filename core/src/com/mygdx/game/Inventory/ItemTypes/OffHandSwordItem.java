package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.EquipInOffHand;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

public abstract class OffHandSwordItem extends SwordHandItem implements OffHandItem {
    private Array<ItemContextAction> actions;

    public OffHandSwordItem(){
        actions = super.getItemContextActions();
        actions.add(new EquipInOffHand(this));
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return actions;
    }
}
