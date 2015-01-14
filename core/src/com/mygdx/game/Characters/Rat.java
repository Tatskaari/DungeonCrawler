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

        setLevel(level);
    }

    public Rat(){
        this(new GridPoint2(0,0), 1);
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
    public void setLevel(int level) {
        setMaxHealth(5+level);
        setHealth(5+level);

        this.level = level;
        setDamageRange(0+level, 2 + level);
    }

    @Override
    public void die() {
        super.die();
    }
}
