package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.Factories.GoblinFactory;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;

public class T5MonsterSpawnPool extends MonsterSpawnPool {
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(new GoblinFactory(dungeon), 2);
    }
}
