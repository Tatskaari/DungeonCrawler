package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemContextAction;

/**
 * Created by Tatskaari on 13/01/2015.
 */
public abstract class SwordHandItem implements InventoryItem{
    private final Array<ItemContextAction> itemContextActions;
    private final SwordHandItem thisItem = this;

    public SwordHandItem(){
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
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }

    public abstract int getAttackRating();
}
