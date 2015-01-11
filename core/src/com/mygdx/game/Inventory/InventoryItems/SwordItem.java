package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemContextAction;

public class SwordItem extends EmptySwordHandItem {
    private Array<ItemContextAction> itemContextActions;
    private final SwordItem thisItem = this;

    public SwordItem() {
        itemContextActions = new Array<ItemContextAction>();
        itemContextActions.add(new ItemContextAction() {

            @Override
            public void onClick() {
                GameHandler.player.inventory.equipItemInSwordHandSlot(thisItem);
            }

            @Override
            public String getActionPrompt() {
                return "Equip";
            }
        });
    }

    @Override
    public String getItemName() {
        return "sword";
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }

    @Override
    public boolean isEmptyItem() {
        return false;
    }

    @Override
    public int getAttackRating(){
        return 3;
    }
}
