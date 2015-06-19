package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;


public class Rat extends BasicNonPlayerCharacterEntity {
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
        return 1 + level/2;
    }

    private void setLevel(int level) {
        setMaxHealth(5+level);
        setHealth(getMaxHealth());

        this.level = level;
        setDamageRange(level, 2 + level);
    }

    @Override
    public void die() {
        super.die();
        UserInterface.growlArea.println(new ColouredText("The rat crumples into a heap. You gain " + getExperienceValue() + " experience."));
    }
}
