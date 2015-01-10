package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.InventorySlot;

public class InventoryActor extends Window{
    Inventory inventory;
    private final TextButton inventoryCloseButton;
    Skin skin;


    public InventoryActor(String title, Skin skin, Inventory inventory) {
        super(title, skin);

        this.inventory = inventory;
        this.skin = skin;

        inventoryCloseButton = new TextButton("X", skin);

        populateInventory();
    }

    private void populateInventory(){
        setVisible(false);
        inventoryCloseButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        setVisible(false);
                    }
                }
        );
        getButtonTable().add(inventoryCloseButton).height(getPadTop());
        setPosition((Gdx.graphics.getWidth() - 500) / 2, (Gdx.graphics.getHeight() - 500) / 2);
        defaults().fill().expand();
        row();

        for (int i = 0; i < inventory.getWidth(); i++){
            for (int j = 0; j < inventory.getHeight(); j++){
                InventorySlotActor slot = new InventorySlotActor(new InventorySlot(), skin.getDrawable("rounded"));
                slot.setBackgroundColor(Color.DARK_GRAY);
                slot.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Inventory Slot Clicked");
                    }
                });
                add(slot);
            }
            row();
        }

        setWidth(500);
        setHeight(500);
    }
}
