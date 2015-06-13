package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.Factory;

/**
 * Created by Tatskaari on 13/06/2015.
 */
public class RatFactory extends MonsterFactory{
    private final Dungeon dungeon;

    public RatFactory(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public Rat newInstance() {
        Rat rat = new Rat();
        initialise(rat);
        return rat;
    }

    @Override
    protected Dungeon getDungeon() {
        return dungeon;
    }
}
