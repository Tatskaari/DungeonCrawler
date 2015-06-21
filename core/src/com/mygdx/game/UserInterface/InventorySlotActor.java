package com.mygdx.game.UserInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Inventory.InventorySlot;


class InventorySlotActor extends ImageButton {
    private final InventorySlot inventorySlot;
    private final Drawable background;
    private static final Color BACKGROUND_COLOUR = Color.DARK_GRAY;
    private float padding;
    private final Skin skin;

    public InventorySlotActor(final InventorySlot inventorySlot, final Skin skin){
        super(skin.getDrawable(inventorySlot.getItem().getTextureName()));
        this.background = skin.getDrawable("rounded");
        this.inventorySlot = inventorySlot;
        this.skin = skin;
        setPadding(1);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(inventorySlot.getItem().getItemContextActions().size > 0){
                    ContextMenuActor menu = new ContextMenuActor(skin, inventorySlot.getItem());
                    menu.showOnCursor(getStage());
                }
            }
        });
    }

    void setPadding(float padding){
        this.padding = padding;
        setWidth(getWidth() + padding*2);
        setHeight(getHeight() + padding*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(BACKGROUND_COLOUR);
        background.draw(batch, getX(), getY(), getWidth(), getHeight());
        batch.setColor(Color.WHITE);

        Drawable itemDrawable = skin.getDrawable(inventorySlot.getItem().getTextureName());
        itemDrawable.draw(batch, getX() + padding, getY() + padding, getWidth() - padding * 2, getHeight() - padding * 2);
    }
}
