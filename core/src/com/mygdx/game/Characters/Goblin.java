package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.SkeletonLootPool;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

/**
 * Created by Tatskaari on 21/01/2015.
 */
public class Goblin extends BasicNonPlayerCharacterEntity {
    private int level;
    private final SkeletonLootPool lootPool;
    private final float dropChance = 0.7f;

    public Goblin(GridPoint2 position, int level) {
        super(position);
        lootPool = new SkeletonLootPool();

        setLevel(level);
    }

    public Goblin(){
        this(new GridPoint2(0,0), 1);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.goblin;
    }

    @Override
    public int getExperienceValue() {
        return 5 + level/2;
    }

    public void die(){
        super.die();
        if(MathUtils.randomBoolean(dropChance)){
            GameHandler.dungeon.getDungeonTile(getPosition()).addItem(lootPool.getSpawn());
        }
        UserInterface.growlArea.println(new ColouredText("The goblin's belly splits open at the force of your strike. You gain " + getExperienceValue() + " experience."));

    }
    @Override
    public void setLevel(int level) {
        setMaxHealth(8+level);
        setHealth(8+level);

        this.level = level;
        setDamageRange(2+level, 7 + level);
    }
}
