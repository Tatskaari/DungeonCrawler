package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.SkeletonLootPool;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class Goblin extends BasicMonsterCharacterEntity {
    private static final float DROP_CHANCE = 0.8f;
    private static final int BASE_HEALTH = 20;
    private static final int BASE_DAMAGE = 10;
    private static final int BASE_XP = 5;

    private int level;
    private final SkeletonLootPool lootPool;

    private Goblin(int level, Dungeon dungeon) {
        super(dungeon);
        lootPool = new SkeletonLootPool();

        setLevel(level);
    }

    public Goblin(Dungeon dungeon) {
        this(dungeon.getLevel(), dungeon);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.getResTextureRegion("goblin");
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
        UserInterface.growlArea.println(new ColouredText("The goblin's belly splits open at the force of your strike. You gain " + getExperienceValue() + " experience."));

    }
    private void setLevel(int level) {
        setMaxHealth(BASE_HEALTH+level*2);
        setHealth(getMaxHealth());

        this.level = level;
        setAttackRating(BASE_DAMAGE + level);
    }
}
