package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.SkeletonLootPool;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class Demon extends BasicMonsterCharacterEntity{
    private static final float DROP_CHANCE = 1f;
    private static final int BASE_HEALTH = 30;
    private static final int BASE_DAMAGE = 17;
    private static final int BASE_XP = 10;

    private int level;
    private final SkeletonLootPool lootPool;

    private Demon(int level, Dungeon dungeon) {
        super(dungeon);
        lootPool = new SkeletonLootPool();

        setLevel(level);
    }

    public Demon(Dungeon dungeon) {
        this(dungeon.getLevel(), dungeon);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.getResTextureRegion("demon");
    }

    @Override
    public int getExperienceValue() {
        return Math.floorDiv(level, 2) + BASE_XP;
    }

    public void die(){
        super.die();
        if(MathUtils.randomBoolean(DROP_CHANCE)){
            Dungeon.getActiveDungeon().getDungeonTile(getPosition()).addItem(lootPool.getNewInstance());
        }
        UserInterface.growlArea.println(new ColouredText("The demon retreats into a flaming vortex. You gain " + getExperienceValue() + " experience."));

    }

    private void setLevel(int level) {
        setMaxHealth(BASE_HEALTH+level*2);
        setHealth(getMaxHealth());

        this.level = level;
        setAttackRating(BASE_DAMAGE + level);
    }
}
