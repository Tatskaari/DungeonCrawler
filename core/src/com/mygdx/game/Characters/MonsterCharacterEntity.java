package com.mygdx.game.Characters;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;

public interface MonsterCharacterEntity extends CharacterEntity {
    public int getExperienceValue();
    public int getHealth();
    public int getMaxHealth();
    public void setHealth(int health);
    public void setPos(GridPoint2 pos);
    public Dungeon getDungeon();

}
