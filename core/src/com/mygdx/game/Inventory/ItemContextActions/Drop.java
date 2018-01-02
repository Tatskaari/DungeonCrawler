package com.mygdx.game.Inventory.ItemContextActions;

import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventType;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class Drop implements ItemContextAction{
    private final InventoryItem item;

    public Drop(InventoryItem item){
        this.item = item;
    }

    @Override
    public void onClick() {
        PlayerCharacterEntity player = PlayerCharacterEntity.getInstance();
        player.inventory.dropItem(item);
        Dungeon.getActiveDungeon().getDungeonTile(player.getPosition()).addItem(item);
        EventHandler.getInstance().triggerEvent(new Event(EventType.STEP_TURN));
    }

    @Override
    public String getActionPrompt() {
        return "Drop";
    }
}
