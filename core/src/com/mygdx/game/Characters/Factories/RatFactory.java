package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Dungeon.Dungeon;

public class RatFactory extends MonsterFactory{
    private final Dungeon dungeon;

    public RatFactory(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public Rat newInstance() {
        Rat rat = new Rat(dungeon);
        initialise(rat);
        return rat;
    }

    @Override
    protected Dungeon getDungeon() {
        return dungeon;
    }
}
