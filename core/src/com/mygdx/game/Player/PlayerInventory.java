package com.mygdx.game.Player;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventoryItems.EmptyShieldItem;
import com.mygdx.game.Inventory.InventorySlots.BodySlot;
import com.mygdx.game.Inventory.InventorySlots.HeadSlot;
import com.mygdx.game.Inventory.InventorySlots.OffHandSlot;
import com.mygdx.game.Inventory.ItemTypes.*;
import com.mygdx.game.Inventory.InventorySlot;
import com.mygdx.game.Inventory.InventorySlots.SwordHandSlot;

public class PlayerInventory extends Inventory {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;

    private final GridPoint2 swordHandItemSlotPos;
    private final GridPoint2 headItemSlotPos;
    private final GridPoint2 bodyItemSlotPos;
    private final GridPoint2 offHandSlotPos;

    public PlayerInventory() {
        super(WIDTH, HEIGHT);

        swordHandItemSlotPos = new GridPoint2(0,0);
        headItemSlotPos = new GridPoint2(1, 0);
        bodyItemSlotPos = new GridPoint2(2, 0);
        offHandSlotPos = new GridPoint2(3, 0);

        setSlot(swordHandItemSlotPos, new SwordHandSlot());
        setSlot(headItemSlotPos, new HeadSlot());
        setSlot(bodyItemSlotPos, new BodySlot());
        setSlot(offHandSlotPos, new OffHandSlot());
    }



    void equipItemInSwordHandSlot(SwordHandItem item){
        moveItemToSlot(item, getSlot(swordHandItemSlotPos));
    }

    public SwordHandItem getSwordHandItem() {
        return (SwordHandItem) getSlot(swordHandItemSlotPos).getItem();
    }

    void equipItemInHeadSlot(HeadItem item) {
        moveItemToSlot(item, getSlot(headItemSlotPos));
    }

    public HeadItem getHeadItem(){
        return (HeadItem) getSlot(headItemSlotPos).getItem();
    }
    public BodyItem getBodyItem(){
        return (BodyItem) getSlot(bodyItemSlotPos).getItem();
    }
    public ShieldItem getShieldItem(){
        InventoryItem item = getSlot(offHandSlotPos).getItem();
        if (item instanceof ShieldItem){
            return (ShieldItem) getSlot(offHandSlotPos).getItem();

        }
        return new EmptyShieldItem();
    }

    private void moveItemToSlot(InventoryItem item, InventorySlot slot){
        InventoryItem oldItem = slot.getItem();
        InventorySlot fromSlot = findSlotWithItem(item);

        if (!oldItem.isEmptyItem()){
            fromSlot.replaceItem(oldItem);
        } else {
            fromSlot.emptySlot();
        }

        slot.replaceItem(item);
    }

    void equipItemInBodySlot(BodyItem bodyItem) {
        moveItemToSlot(bodyItem, getSlot(bodyItemSlotPos));
    }

    void equipItemInOffHandSlot(OffHandItem offHandItem) {
        moveItemToSlot(offHandItem, getSlot(offHandSlotPos));
    }

    public void equipItem(EquipableItem item){
        if (item instanceof SwordHandItem) {
            equipItemInSwordHandSlot((SwordHandItem)item);
        } else if (item instanceof ShieldItem) {
            equipItemInOffHandSlot((ShieldItem) item);
        } else if (item instanceof HeadItem) {
            equipItemInHeadSlot((HeadItem)item);
        } else if (item instanceof BodyItem){
            equipItemInBodySlot((BodyItem) item);
        } else {
            throw new RuntimeException("Unknown item type " + item.toString());
        }
    }

    public void equipItemInOffHand(OffHandSwordItem item){
        equipItemInOffHandSlot(item);
    }

    public boolean hasOffHandSword() {
        return getSlot(offHandSlotPos).getItem() instanceof OffHandSwordItem;
    }

    public OffHandSwordItem getOffHandItem() {
        return (OffHandSwordItem) getSlot(offHandSlotPos).getItem();
    }
}
