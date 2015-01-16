package com.mygdx.game.Inventory.ItemTypes;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemContextAction;

public abstract class BodyItem extends ArmourItem{
    private final Array<ItemContextAction> itemContextActions;
    private final BodyItem thisItem = this;

    protected BodyItem(){
        itemContextActions = new Array<ItemContextAction>();
        itemContextActions.add(new ItemContextAction() {

            @Override
            public void onClick() {
                GameHandler.player.inventory.equipItemInBodySlot(thisItem);
            }

            @Override
            public String getActionPrompt() {
                return "Equip";
            }
        });
    }

    @Override
    public Array<ItemContextAction> getItemContextActions() {
        return itemContextActions;
    }
}
