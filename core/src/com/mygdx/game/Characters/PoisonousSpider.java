package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class PoisonousSpider extends BasicMonsterCharacterEntity{

    private static final int BASE_HEALTH = 5;
    private static final int BASE_DAMAGE = 3;
    private static final int BASE_XP = 1;

    private int level;

    private PoisonousSpider(int level, Dungeon dungeon) {
        super(dungeon);
        setLevel(level);
    }

    public PoisonousSpider(Dungeon dungeon) {
        this(dungeon.getLevel(), dungeon);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.getResTextureRegion("spider");
    }

    @Override
    public int getExperienceValue() {
        return Math.floorDiv(level-1, 2) + BASE_XP;
    }

    private void setLevel(int level) {
        this.level = level;

        setMaxHealth(BASE_HEALTH+level*2);
        setHealth(getMaxHealth());

        setAttackRating(BASE_DAMAGE + level*2);
    }

    @Override
    public void die() {
        super.die();
        UserInterface.growlArea.println(new ColouredText("You splatter the spider. You gain " + getExperienceValue() + " experience."));
    }
}
