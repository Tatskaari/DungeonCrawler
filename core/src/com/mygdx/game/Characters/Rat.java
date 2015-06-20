package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;


public class Rat extends BasicMonsterCharacterEntity {
    private static final int BASE_HEALTH = 5;
    private static final int BASE_DAMAGE = 2;
    private static final int BASE_XP = 1;

    private int level;

    private Rat(int level, Dungeon dungeon) {
        super(dungeon);

        setLevel(level);
    }

    public Rat(Dungeon dungeon) {
        this(dungeon.getLevel(), dungeon);
    }

    @Override
    public TextureRegion getTexture() {
        return ResourceLoader.rat;
    }

    @Override
    public int getExperienceValue() {
        return Math.floorDiv(level-1, 2) + BASE_XP;
    }

    private void setLevel(int level) {
        setMaxHealth(BASE_HEALTH+level);
        setHealth(getMaxHealth());

        this.level = level;
        setDamageRange(level, BASE_DAMAGE + level);
    }

    @Override
    public void die() {
        super.die();
        UserInterface.growlArea.println(new ColouredText("The rat crumples into a heap. You gain " + getExperienceValue() + " experience."));
    }
}
