package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.*;
import com.mygdx.game.Dungeon.Dungeon;

public class T3MonsterSpawnPool extends MonsterSpawnPool{
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(() -> new Rat(dungeon), 1.5f);
        addNew(() -> new Crab(dungeon), 1.5f);
        addNew(() -> new PoisonousSpider(dungeon), 1.5f);
        addNew(() -> new Skeleton(dungeon), 3);
        addNew(() -> new Goblin(dungeon), 2);
    }
}
