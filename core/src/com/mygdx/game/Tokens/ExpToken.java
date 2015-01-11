package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class ExpToken extends BasicToken{
    int experience;

    public ExpToken(GridPoint2 position, int experience) {
        super(position);
        this.experience = experience;
        duration = 0.5f;
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        age+=delta;
        if (age <= duration){
            updatePosition(delta);
            ResourceLoader.expFont.draw(batch, "EXP: " + experience, position.x, position.y);
        }
    }
}
