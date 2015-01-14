package com.mygdx.game.Player;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventorySlots.HeadSlot;
import com.mygdx.game.Inventory.ItemTypes.HeadItem;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.Inventory.InventoryItems.EmptySwordHandItem;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.InventorySlots.SwordHandSlot;
import com.mygdx.game.Inventory.ItemTypes.SwordHandItem;

public class PlayerInventory extends Inventory {
    private GridPoint2 swordHandItemSlotPos;
    private GridPoint2 headItemSlotPos;

    public PlayerInventory(int width, int height) {
        super(width, height);

        swordHandItemSlotPos = new GridPoint2(0,0);
        headItemSlotPos = new GridPoint2(1, 0);

        setSlot(swordHandItemSlotPos, new SwordHandSlot());
        setSlot(headItemSlotPos, new HeadSlot());
    }



    public void equipItemInSwordHandSlot(SwordHandItem item){
        moveItemToSlot(item, getSlot(swordHandItemSlotPos));
    }

    public SwordHandItem getSwordHandItem() {
        return (SwordHandItem) getSlot(swordHandItemSlotPos).getItem();
    }

    public void equipItemInHeadSlot(HeadItem item) {
        moveItemToSlot(item, getSlot(headItemSlotPos));
    }

    public HeadItem getHeadItem(){
        return (HeadItem) getSlot(headItemSlotPos).getItem();
    }

    private void moveItemToSlot(InventoryItem item, InventorySlot slot){
        InventoryItem oldItem = slot.getItem();
        InventorySlot fromSlot = findSlotWithItem(item);

        if (!oldItem.isEmptyItem()){
            fromSlot.replaceItem(oldItem);
        } else {
            fromSlot.empty();
        }

        slot.replaceItem(item);
    }
}
