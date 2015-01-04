package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class Skeleton extends BasicNonPlayerCharacterEntity {

    public Skeleton(GridPoint2 position) {
        super(position);

        setMaxHealth(10);
        setHealth(10);

        setDamageRange(1, 3);
    }

    @Override
    public Texture getTexture() {
        return ResourceLoader.skeleton;
    }

    @Override
    public int getExperienceValue() {
        return 3;
    }
}
