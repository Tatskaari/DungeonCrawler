package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Crab;
import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Characters.Skeleton;
import com.mygdx.game.Dungeon.Dungeon;

public class T2MonsterSpawnPool extends MonsterSpawnPool{
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(() -> new Rat(dungeon), 3);
        addNew(() -> new Skeleton(dungeon), 2);
        addNew(() -> new Crab(dungeon), 4);

    }
}
