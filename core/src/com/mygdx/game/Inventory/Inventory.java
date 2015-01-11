package com.mygdx.game.Inventory;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.InventorySlots.StandardInventorySlot;
import com.mygdx.game.Inventory.InventorySlots.SwordHandSlot;

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
                inventorySlots.get(i).add(new StandardInventorySlot());
            }
        }
    }

    public InventorySlot getSlot(int x, int y) {
        return inventorySlots.get(x).get(y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addItem(InventoryItem inventoryItem) {
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                InventorySlot slot = inventorySlots.get(i).get(j);
                if(slot.canTakeItem(inventoryItem)){
                    slot.addItem(inventoryItem);
                    return;
                }
            }
        }

        System.out.println("You don't have space to pick that item up!");
    }

    public void setSlot(int x, int y, InventorySlot slot){
        inventorySlots.get(x).set(y, slot);
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
