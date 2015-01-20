package com.mygdx.game.Characters;

import com.badlogic.gdx.math.GridPoint2;

public interface NonPlayerCharacterEntity extends CharacterEntity {
    public int getExperienceValue();
    public int getHealth();
    public int getMaxHealth();
    public void setMaxHealth(int maxHealth);
    public void setHealth(int health);
    public void setPos(GridPoint2 pos);

    void setLevel(int level);
}
