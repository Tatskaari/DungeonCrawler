package com.mygdx.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Renderers.Renderer;

public interface Monster {
    public void act();
    public void beAttacked(int damage);
    public boolean isDead();
    public int getHealth();
    public int getMaxHealth();
    public void setHealth(int health);
    public Texture getTexture();
    public GridPoint2 getPosition();
    public Renderer getRenderer();
    public boolean moveTo(GridPoint2 position);

    public void attack(Monster monster);
}
