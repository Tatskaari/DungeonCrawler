package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Characters.PoisonousSpider;
import com.mygdx.game.Characters.Skeleton;
import com.mygdx.game.Dungeon.Dungeon;

public class T4MonsterSpawnPool extends MonsterSpawnPool{
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(() -> new Skeleton(dungeon), 3);
        addNew(() -> new Goblin(dungeon), 2);
        addNew(() -> new PoisonousSpider(dungeon), 1);

    }
}
