package com.mygdx.game.Player;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventorySlots.BodySlot;
import com.mygdx.game.Inventory.InventorySlots.HeadSlot;
import com.mygdx.game.Inventory.InventorySlots.ShieldSlot;
import com.mygdx.game.Inventory.ItemTypes.*;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.InventorySlots.SwordHandSlot;

public class PlayerInventory extends Inventory {
    private final GridPoint2 swordHandItemSlotPos;
    private final GridPoint2 headItemSlotPos;
    private final GridPoint2 bodyItemSlotPos;
    private final GridPoint2 shieldItemSlotPos;

    public PlayerInventory(int width, int height) {
        super(width, height);

        swordHandItemSlotPos = new GridPoint2(0,0);
        headItemSlotPos = new GridPoint2(1, 0);
        bodyItemSlotPos = new GridPoint2(2, 0);
        shieldItemSlotPos = new GridPoint2(3, 0);

        setSlot(swordHandItemSlotPos, new SwordHandSlot());
        setSlot(headItemSlotPos, new HeadSlot());
        setSlot(bodyItemSlotPos, new BodySlot());
        setSlot(shieldItemSlotPos, new ShieldSlot());
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
    public BodyItem getBodyItem(){
        return (BodyItem) getSlot(bodyItemSlotPos).getItem();
    }
    public ShieldItem getShieldItem(){
        return (ShieldItem) getSlot(shieldItemSlotPos).getItem();
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

    public void equipItemInBodySlot(BodyItem bodyItem) {
        moveItemToSlot(bodyItem, getSlot(bodyItemSlotPos));
    }

    public void equipItemInShieldSlot(ShieldItem shieldItem) {
        moveItemToSlot(shieldItem, getSlot(shieldItemSlotPos));
    }
}
