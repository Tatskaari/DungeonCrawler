package com.mygdx.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Renderers.Renderer;

public interface Monster {
    public void act();
    public void beAttacked();
    public boolean isDead();
    public Texture getTexture();
    public GridPoint2 getPosition();
    public Renderer getRenderer();
}
