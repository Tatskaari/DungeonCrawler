package com.mygdx.game.Tokens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Token {
    public void render(float delta, SpriteBatch batch);
    public boolean isAlive();
}
