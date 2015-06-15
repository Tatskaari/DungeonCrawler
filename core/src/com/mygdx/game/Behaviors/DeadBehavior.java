package com.mygdx.game.Behaviors;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;

public class DeadBehavior extends Behavior {
    private static final int RESPAWN_TIME = 20;

    private int respawnCounter;
    private final NonPlayerCharacterEntity character;

    public DeadBehavior(NonPlayerCharacterEntity character){
        this.character = character;
        respawnCounter = RESPAWN_TIME;
    }

    @Override
    public Behavior act() {
        respawnCounter--;
        if (respawnCounter <= 0){
            character.setHealth(character.getMaxHealth());
            character.moveTo(DungeonUtils.getRandomNonVisibleTilePosInAnyRoom(Dungeon.getActiveDungeon()));
            return new GenericWanderBehavior(character);
        }
        return this;
    }
}
