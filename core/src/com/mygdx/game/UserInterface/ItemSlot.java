package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;



public class ItemSlot extends ImageButton {
    private Drawable icon;
    private Drawable background;
    private Color backgroundColor;
    private float padding;

    public ItemSlot(Drawable icon, Drawable background){
        super(icon);
        this.background = background;
        this.icon = icon;

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
        icon.draw(batch, getX() + padding, getY() + padding, getWidth() - padding * 2, getHeight() - padding*2);
    }
}
