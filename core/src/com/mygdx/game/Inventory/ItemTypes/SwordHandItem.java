package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemContextActions.Drop;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;

public abstract class SwordHandItem extends EquipableItem{
    @Override
    public int getMaxStackSize() {
        return 1;
    }


    public abstract int getAttackRating();
}
