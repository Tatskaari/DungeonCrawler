package com.mygdx.game.Renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Renderer {
    public abstract void render(float delta, SpriteBatch batch);
    public void devRender(float delta, SpriteBatch batch){
        render(delta, batch);
    }
}
