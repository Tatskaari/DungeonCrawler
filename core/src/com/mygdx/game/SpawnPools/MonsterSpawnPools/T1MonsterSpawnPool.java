package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Dungeon.Dungeon;

public class T1MonsterSpawnPool extends MonsterSpawnPool {

    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(() -> new Rat(dungeon), 1);
    }
}
