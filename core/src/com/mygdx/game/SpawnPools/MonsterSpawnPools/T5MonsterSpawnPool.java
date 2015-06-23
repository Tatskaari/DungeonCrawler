package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Demon;
import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Characters.Skeleton;
import com.mygdx.game.Dungeon.Dungeon;

public class T5MonsterSpawnPool extends MonsterSpawnPool {
    public void initialisePool(final Dungeon dungeon){
        reset();
        addNew(() -> new Goblin(dungeon), 2);
        addNew(() -> new Skeleton(dungeon), 1);
        addNew(() -> new Demon(dungeon), 1);
    }
}
