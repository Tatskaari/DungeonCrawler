package com.mygdx.game.Inventory.InventoryItems;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.InventoryItem;
import com.mygdx.game.Inventory.ItemContextAction;

public class SwordItem implements InventoryItem {
    private Array<ItemContextAction> itemContextActions;

    public SwordItem() {
        itemContextActions = new Array<ItemContextAction>();
        itemContextActions.add(new ItemContextAction() {

            @Override
            public void onClick() {
                System.out.println("Equip action clicked");
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
}
