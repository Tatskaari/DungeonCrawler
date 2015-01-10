package com.mygdx.game.Inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ResourceLoader;

public class InventorySlot {
    private InventoryItem item;
    private int itemCount;

    public TextureRegion getItemIcon(){
        return ResourceLoader.rat;
    }
}
