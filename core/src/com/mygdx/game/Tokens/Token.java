package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jony1710 on 29/12/2014.
 */
public interface Token {
    public void render(float delta, SpriteBatch batch);
    public boolean isAlive();
}
