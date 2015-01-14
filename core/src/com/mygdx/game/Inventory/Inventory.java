package com.mygdx.game.Inventory;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.InventorySlots.StandardSlot;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class Inventory {
    Array<Array<InventorySlot>> inventorySlots;
    private int width;
    private int height;

    public Inventory(int width, int height){
        inventorySlots = new Array<Array<InventorySlot>>();
        this.width = width;
        this.height = height;

        for(int i = 0; i < width; i++){
            inventorySlots.add(new Array<InventorySlot>());
            for (int j = 0; j < height; j++){
                inventorySlots.get(i).add(new StandardSlot());
            }
        }
    }

    public InventorySlot getSlot(GridPoint2 pos) {
        return inventorySlots.get(pos.y).get(pos.x);
    }

    public InventorySlot getSlot(int x, int y) {
        return inventorySlots.get(y).get(x);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean addItem(InventoryItem inventoryItem) {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                InventorySlot slot = inventorySlots.get(i).get(j);
                if(slot.canTakeItem(inventoryItem)){
                    slot.addItem(inventoryItem);
                    return true;
                }
            }
        }

        return false;
    }

    public void setSlot(GridPoint2 pos, InventorySlot slot){
        inventorySlots.get(pos.y).set(pos.x, slot);
    }

    public InventorySlot findSlotWithItem(InventoryItem item) {
        InventorySlot slot = null;
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++){
                InventorySlot currentSlot = inventorySlots.get(i).get(j);
                if (currentSlot.getItem() == item){
                    slot = currentSlot;
                    return slot;
                }
            }
        }

        return slot;
    }
}
