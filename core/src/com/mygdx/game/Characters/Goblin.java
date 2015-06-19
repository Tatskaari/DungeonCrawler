package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.SpawnPools.SkeletonLootPool;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class Goblin extends BasicNonPlayerCharacterEntity {
    private int level;
    private final SkeletonLootPool lootPool;
    private static final float DROP_CHANCE = 0.7f;

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
        return ResourceLoader.goblin;
    }

    @Override
    public int getExperienceValue() {
        return 5 + level/2;
    }

    public void die(){
        super.die();
        if(MathUtils.randomBoolean(DROP_CHANCE)){
            Dungeon.getActiveDungeon().getDungeonTile(getPosition()).addItem(lootPool.getNewInstance());
        }
        UserInterface.growlArea.println(new ColouredText("The goblin's belly splits open at the force of your strike. You gain " + getExperienceValue() + " experience."));

    }
    private void setLevel(int level) {
        setMaxHealth(8+level*2);
        setHealth(getMaxHealth());

        this.level = level;
        setDamageRange(2+level*2, 7+level*2);
    }
}
