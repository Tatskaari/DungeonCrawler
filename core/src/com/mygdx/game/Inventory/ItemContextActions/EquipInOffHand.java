package com.mygdx.game.Inventory.ItemContextActions;

import com.mygdx.game.Inventory.ItemTypes.EquipableItem;
import com.mygdx.game.Inventory.ItemTypes.OffHandSwordItem;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class EquipInOffHand implements ItemContextAction {
    private final OffHandSwordItem item;

    public EquipInOffHand(OffHandSwordItem item){
        this.item = item;
    }

    @Override
    public void onClick() {
        PlayerCharacterEntity.getInstance().inventory.equipItemInOffHand(item);
    }

    @Override
    public String getActionPrompt() {
        return "Equip In Off Hand";
    }
}
