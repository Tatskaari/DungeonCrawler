package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.GameHandler;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.SkeletonLootPool;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class Skeleton extends BasicNonPlayerCharacterEntity {
    private int level;
    private final SkeletonLootPool lootPool;
    private final float dropChance = 0.7f;

    public Skeleton(GridPoint2 position, int level, Dungeon dungeon) {
        super(position, dungeon);
        lootPool = new SkeletonLootPool();

        setLevel(level);
    }

    public Skeleton(Dungeon dungeon) {
        this(new GridPoint2(0,0), 1, dungeon);
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
            Dungeon.getActiveDungeon().getDungeonTile(getPosition()).addItem(lootPool.getNewInstance());
        }
        UserInterface.growlArea.println(new ColouredText("The skeleton falls to pieces at the joints. You gain " + getExperienceValue() + " experience."));

    }
    @Override
    public void setLevel(int level) {
        setMaxHealth(5+level);
        setHealth(getMaxHealth());

        this.level = level;
        setDamageRange(level, 2+level*2);
    }
}
