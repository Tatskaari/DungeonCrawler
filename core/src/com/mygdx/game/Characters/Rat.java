package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;


public class Rat extends BasicNonPlayerCharacterEntity {

    public Rat(GridPoint2 position) {
        super(position);

        setMaxHealth(5);
        setHealth(5);

        setDamageRange(0, 2);
    }

    @Override
    public Texture getTexture() {
        return ResourceLoader.rat;
    }

    @Override
    public int getExperienceValue() {
        return 1;
    }
}
