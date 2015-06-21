package com.mygdx.game.Inventory.ItemTypes;

public abstract class SwordHandItem extends EquipableItem{
    @Override
    public int getMaxStackSize() {
        return 1;
    }


    public abstract int getAttackRating();
}
