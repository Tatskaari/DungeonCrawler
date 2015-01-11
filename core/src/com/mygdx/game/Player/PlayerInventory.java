package com.mygdx.game.Player;

import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventoryItem;
import com.mygdx.game.Inventory.InventoryItems.EmptySwordHandItem;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.InventorySlots.SwordHandSlot;

public class PlayerInventory extends Inventory {
    public PlayerInventory(int width, int height) {
        super(width, height);

        setSlot(0, 0, new SwordHandSlot());
    }

    public void equipItemInSwordHandSlot(EmptySwordHandItem item){
        InventorySlot oldSlot = findSlotWithItem(item);
        InventoryItem oldItem = getSlot(0, 0).getItem();

        if (!oldItem.isEmptyItem()){
            oldSlot.replaceItem(oldItem);
        } else {
            oldSlot.empty();
        }

        getSlot(0,0).replaceItem(item);
    }

    public EmptySwordHandItem getSwordHandItem() {
        return (EmptySwordHandItem) getSlot(0, 0).getItem();
    }
}
