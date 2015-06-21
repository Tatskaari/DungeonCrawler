package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Characters.Skeleton;
import com.mygdx.game.Dungeon.Dungeon;

public class T5MonsterSpawnPool extends MonsterSpawnPool {
    public void initialisePool(final Dungeon dungeon){
        reset();
        addNew(() -> new Goblin(dungeon), 3);
        addNew(() -> new Skeleton(dungeon), 1);
    }
}
