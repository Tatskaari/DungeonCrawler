package com.mygdx.game.UserInterface;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Inventory.Inventory;

class InventoryActor extends CenterScreenWindow{
    private final Inventory inventory;


    public InventoryActor(Skin skin, Inventory inventory, CenterWindowManager centerWindowManager) {
        super("Inventory", skin, centerWindowManager);
        this.inventory = inventory;
        populateInventory();
    }

    private void populateInventory(){
        defaults().fill().expand();
        row();

        for (int i = 0; i < inventory.getWidth(); i++){
            for (int j = 0; j < inventory.getHeight(); j++){
                InventorySlotActor slot = new InventorySlotActor(inventory.getSlot(j, i), getSkin());
                add(slot);
            }
            row();
        }
    }
}
