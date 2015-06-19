package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Characters.Skeleton;
import com.mygdx.game.Dungeon.Dungeon;

public class T3MonsterSpawnPool extends MonsterSpawnPool{
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(() -> new Rat(dungeon), 5);
        addNew(() -> new Skeleton(dungeon), 3);
        addNew(() -> new Goblin(dungeon), 2);
    }
}
