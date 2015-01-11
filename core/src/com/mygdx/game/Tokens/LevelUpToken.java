package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ResourceLoader;

public class LevelUpToken extends BasicToken {
    private int level;

    public LevelUpToken(GridPoint2 position, int level) {
        super(position);
        this.level = level;
        duration = 1;
        velocity = new Vector2(0, 5);
    }

    @Override
    public void render(float delta, SpriteBatch batch) {
        age+=delta;
        if (age <= duration){
            updatePosition(delta);
            ResourceLoader.expFont.draw(batch, "Level Up: " + level, position.x, position.y);
        }
    }
}
