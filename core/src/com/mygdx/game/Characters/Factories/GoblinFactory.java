package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Dungeon.Dungeon;

public class GoblinFactory extends MonsterFactory {
    private final Dungeon dungeon;

    public GoblinFactory(Dungeon dungeon){
        this.dungeon = dungeon;
    }
    @Override
    public Goblin newInstance() {
        Goblin goblin = new Goblin();
        initialise(goblin);
        return goblin;
    }

    @Override
    protected Dungeon getDungeon() {
        return dungeon;
    }
}
