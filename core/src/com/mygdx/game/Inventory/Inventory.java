package com.mygdx.game.Inventory;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.EventHandlers.ContextMessageEvent;
import com.mygdx.game.EventHandlers.Event;
import com.mygdx.game.EventHandlers.EventHandler;
import com.mygdx.game.EventHandlers.EventListener;
import com.mygdx.game.Inventory.InventorySlots.StandardSlot;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;

public class Inventory{
    private final Array<Array<InventorySlot>> inventorySlots;
    private final int width;
    private final int height;

    protected Inventory(int width, int height){
        inventorySlots = new Array<>();
        this.width = width;
        this.height = height;

        for(int i = 0; i < width; i++){
            inventorySlots.add(new Array<>());
            for (int j = 0; j < height; j++){
                inventorySlots.get(i).add(new StandardSlot());
            }
        }
    }

    protected InventorySlot getSlot(GridPoint2 pos) {
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

    protected void setSlot(GridPoint2 pos, InventorySlot slot){
        inventorySlots.get(pos.y).set(pos.x, slot);
    }

    protected InventorySlot findSlotWithItem(InventoryItem item) {
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++){
                InventorySlot currentSlot = inventorySlots.get(i).get(j);
                if (currentSlot.getItem() == item){
                    return currentSlot;
                }
            }
        }
        return null;
    }

    public void dropItem(InventoryItem item) {
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++){
                InventorySlot currentSlot = inventorySlots.get(i).get(j);
                if (currentSlot.getItem() == item){
                    currentSlot.emptySlot();
                }
            }
        }
    }

    public void emptyInventory(){
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                inventorySlots.get(i).get(j).emptySlot();
            }
        }
    }
}
