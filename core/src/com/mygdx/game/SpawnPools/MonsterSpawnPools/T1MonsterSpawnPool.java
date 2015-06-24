package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Crab;
import com.mygdx.game.Characters.PoisonousSpider;
import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Dungeon.Dungeon;

public class T1MonsterSpawnPool extends MonsterSpawnPool {

    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(() -> new Rat(dungeon), 2);
        addNew(() -> new Crab(dungeon), 1);
        addNew(() -> new PoisonousSpider(dungeon), 1);

    }
}
