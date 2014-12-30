package com.mygdx.game.Behaviors;

import com.mygdx.game.Monsters.Monster;

/**
 * Created by jony1710 on 30/12/2014.
 */
public class DeadBehavior extends Behavior {
    private int respawnCounter;
    private Monster monster;

    public DeadBehavior(Monster monster, int respawnCounter){
        this.respawnCounter = respawnCounter;
        this.monster = monster;
    }

    @Override
    public Behavior act() {
        respawnCounter--;
        if (respawnCounter <= 0){
            monster.setHealth(monster.getMaxHealth());
            monster.moveTo(getRandomTileInAnyRoom());
        }
        return this;
    }
}
