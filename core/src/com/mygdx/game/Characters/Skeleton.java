package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.SkeletonLootPool;

public class Skeleton extends BasicNonPlayerCharacterEntity {
    private int level;
    private final SkeletonLootPool lootPool;
    private final float dropChance = 0.6f;

    public Skeleton(GridPoint2 position, int level) {
        super(position);
        lootPool = new SkeletonLootPool();

        setLevel(level);
    }

    public Skeleton(){
        this(new GridPoint2(0,0), 1);
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
        if(MathUtils.randomBoolean(dropChance)){
            GameHandler.dungeon.getDungeonTile(getPosition()).addItem(lootPool.getSpawn());
        }
    }
    @Override
    public void setLevel(int level) {
        setMaxHealth(5+level);
        setHealth(5+level);

        this.level = level;
        setDamageRange(level, 2 + level);
    }
}
