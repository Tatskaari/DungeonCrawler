package com.mygdx.game.Inventory.ItemTypes;

public abstract class ArmourItem extends EquipableItem {
    public abstract float getDefenceRating();

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
