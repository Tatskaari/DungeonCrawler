package com.mygdx.game.Inventory.ItemContextActions;

import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class Drop implements ItemContextAction{
    private InventoryItem item;

    public Drop(InventoryItem item){
        this.item = item;
    }

    @Override
    public void onClick() {
        PlayerCharacterEntity player = GameHandler.player;
        GameHandler.player.inventory.dropItem(item);
        GameHandler.dungeon.getDungeonTile(player.getPosition()).addItem(item);
    }

    @Override
    public String getActionPrompt() {
        return "Drop";
    }
}
