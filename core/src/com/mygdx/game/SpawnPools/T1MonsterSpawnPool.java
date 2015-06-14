package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.Factories.RatFactory;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;

public class T1MonsterSpawnPool extends MonsterSpawnPool {

    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(new RatFactory(dungeon), 1);
    }
}
