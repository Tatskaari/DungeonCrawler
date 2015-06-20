package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Inventory.Inventory;

class InventoryActor extends CenterScreenWindow{
    private final Inventory inventory;


    //TODO stop the player input handler when in the inventory
    public InventoryActor(Skin skin, Inventory inventory) {
        super("Inventory", skin);
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
