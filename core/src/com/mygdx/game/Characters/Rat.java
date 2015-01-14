package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.InventoryItems.HelmetItem;
import com.mygdx.game.ResourceLoader;


public class Rat extends BasicNonPlayerCharacterEntity {
    private int level;

    public Rat(GridPoint2 position, int level) {
        super(position);

        setMaxHealth(5+level);
        setHealth(5+level);

        this.level = level;
        setDamageRange(0+level, 2 + level);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.rat;
    }

    @Override
    public int getExperienceValue() {
        return 1 + level/2;
    }

    @Override
    public void die() {
        super.die();
        GameHandler.dungeon.getDungeonTile(getPosition()).addItem(new HelmetItem());
    }
}
