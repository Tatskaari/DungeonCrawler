package com.mygdx.game.Inventory.ItemContextActions;

import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemTypes.EquipableItem;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class Equip implements ItemContextAction {
    EquipableItem item;

    public Equip(EquipableItem item){
        this.item = item;
    }

    @Override
    public void onClick() {
        PlayerCharacterEntity.getInstance().inventory.equipItem(item);
    }

    @Override
    public String getActionPrompt() {
        return "Equip";
    }
}
