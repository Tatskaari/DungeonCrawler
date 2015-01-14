package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.InventoryItems.BattleAxeItem;
import com.mygdx.game.Inventory.InventoryItems.SwordItem;
import com.mygdx.game.ResourceLoader;

public class Skeleton extends BasicNonPlayerCharacterEntity {
    private int level;

    public Skeleton(GridPoint2 position, int level) {
        super(position);

        setMaxHealth(10+level);
        setHealth(10+level);

        this.level = level;
        setDamageRange(1+level, 3+level);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.skeleton;
    }

    @Override
    public int getExperienceValue() {
        return 3 + level/2;
    }

    public void die(){
        super.die();
        GameHandler.dungeon.getDungeonTile(getPosition()).addItem(new BattleAxeItem());
    }
}
