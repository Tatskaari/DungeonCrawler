package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.Factories.GoblinFactory;
import com.mygdx.game.Characters.Factories.RatFactory;
import com.mygdx.game.Characters.Factories.SkeletonFactory;
import com.mygdx.game.Dungeon.Dungeon;

public class T3MonsterSpawnPool extends MonsterSpawnPool{
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(new RatFactory(dungeon), 5);
        addNew(new SkeletonFactory(dungeon), 3);
        addNew(new GoblinFactory(dungeon), 2);
    }
}
