package com.mygdx.game.Inventory;

import com.badlogic.gdx.utils.Array;

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
                inventorySlots.get(i).add(new InventorySlot());
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
}
