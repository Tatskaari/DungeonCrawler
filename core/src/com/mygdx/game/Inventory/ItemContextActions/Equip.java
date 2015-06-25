package com.mygdx.game.Inventory.ItemContextActions;

import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventType;
import com.mygdx.game.Inventory.ItemTypes.EquipableItem;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class Equip implements ItemContextAction {
    private final EquipableItem item;

    public Equip(EquipableItem item){
        this.item = item;
    }

    @Override
    public void onClick() {
        PlayerCharacterEntity.getInstance().inventory.equipItem(item);
        EventHandler.getInstance().triggerEvent(new Event(EventType.STEP_TURN));
    }

    @Override
    public String getActionPrompt() {
        return "Equip";
    }
}
