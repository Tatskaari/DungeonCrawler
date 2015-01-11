package com.mygdx.game.Characters;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by jony1710 on 01/01/2015.
 */
public interface NonPlayerCharacterEntity extends CharacterEntity {
    public int getExperienceValue();
    public int getHealth();
    public int getMaxHealth();
    public void setMaxHealth(int maxHealth);
    public void setHealth(int health);
}
