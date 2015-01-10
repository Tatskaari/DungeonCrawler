package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Inventory.InventorySlot;


public class InventorySlotActor extends ImageButton {
    private InventorySlot inventorySlot;
    private Drawable icon;
    private Drawable background;
    private Color backgroundColor;
    private float padding;

    public InventorySlotActor(InventorySlot inventorySlot, Drawable background){
        super(new TextureRegionDrawable(inventorySlot.getItemIcon()));
        this.background = background;
        this.inventorySlot = inventorySlot;
        this.icon = new TextureRegionDrawable(inventorySlot.getItemIcon());
        setPadding(20);
    }

    public void setBackgroundColor(Color c){
        backgroundColor = c;
    }

    public void setPadding(float padding){
        this.padding = padding;
        setWidth(getWidth() + padding*2);
        setHeight(getHeight() + padding*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(backgroundColor);
        background.draw(batch, getX(), getY(), getWidth(), getHeight());
        batch.setColor(Color.WHITE);
        icon.draw(batch, getX() + padding, getY() + padding, getWidth() - padding * 2, getHeight() - padding * 2);
    }
}
