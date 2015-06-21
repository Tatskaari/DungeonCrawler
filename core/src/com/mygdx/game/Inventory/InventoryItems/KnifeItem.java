package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemContextActions.EquipInOffHand;
import com.mygdx.game.Inventory.ItemContextActions.ItemContextAction;
import com.mygdx.game.Inventory.ItemTypes.OffHandSwordItem;

public class KnifeItem extends OffHandSwordItem{
    private final Array<ItemContextAction> actions = super.getItemContextActions();
    public KnifeItem(){
        //TODO move to OffHandSwordItem
        actions.add(new EquipInOffHand(this));
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return actions;
    }

    @Override
    public int getAttackRating() {
        return 1;
    }

    @Override
    public String getTextureName() {
        return "knife";
    }

    @Override
    public String getItemName() {
        return "Rusty Knife";
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }
}
