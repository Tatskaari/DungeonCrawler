package com.mygdx.game.Behaviors;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.GameHandler;

public class DeadBehavior extends Behavior {
    private int respawnCounter;
    private final NonPlayerCharacterEntity character;

    public DeadBehavior(NonPlayerCharacterEntity character, int respawnCounter){
        this.respawnCounter = respawnCounter;
        this.character = character;
    }

    @Override
    public Behavior act() {
        respawnCounter--;
        if (respawnCounter <= 0){
            character.setHealth(character.getMaxHealth());
            character.moveTo(DungeonUtils.getRandomNonVisibleTileInAnyRoom(GameHandler.dungeon));
            return new GenericWanderBehavior(character);
        }
        return this;
    }
}
