package com.mygdx.game.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Inventory.Inventory;

class InventoryActor extends Window{
    private final Inventory inventory;
    private final TextButton inventoryCloseButton;
    private final Skin skin;


    public InventoryActor(Skin skin, Inventory inventory) {
        super("Inventory", skin);

        this.inventory = inventory;
        this.skin = skin;

        inventoryCloseButton = new TextButton("X", skin);

        populateInventory();
        positionAndSize();

        setVisible(false);
        setMovable(false);

    }

    private void populateInventory(){
        inventoryCloseButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        setVisible(false);
                    }
                }
        );

        getButtonTable().add(inventoryCloseButton).height(getPadTop());

        defaults().fill().expand();
        row();

        for (int i = 0; i < inventory.getWidth(); i++){
            for (int j = 0; j < inventory.getHeight(); j++){
                InventorySlotActor slot = new InventorySlotActor(inventory.getSlot(j, i), skin);
                add(slot);
            }
            row();
        }
    }

    private void positionAndSize(){
        int screenHeight = Gdx.graphics.getHeight();

        float size = 0.8f*screenHeight;
        float y = 0.1f*screenHeight;
        float x = (Gdx.graphics.getWidth() - size)/2;

        setPosition(x,y);
        setSize(size, size);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        positionAndSize();
    }
}
