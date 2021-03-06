package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Renderers.Renderer;

public interface CharacterEntity {
    public void act();
    public void beAttacked(int damage);
    public TextureRegion getTexture();
    public Renderer getRenderer();
    public boolean moveTo(GridPoint2 position);
    public void attack(CharacterEntity characterEntity);
    public GridPoint2 getPosition();
    public boolean isAlive();
}
