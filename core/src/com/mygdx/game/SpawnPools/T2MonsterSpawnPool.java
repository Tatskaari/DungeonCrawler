package com.mygdx.game.SpawnPools;


import com.mygdx.game.Characters.Factories.RatFactory;
import com.mygdx.game.Characters.Factories.SkeletonFactory;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;

public class T2MonsterSpawnPool extends MonsterSpawnPool{
    public void initialisePool(Dungeon dungeon){
        reset();
        addNew(new RatFactory(dungeon), 5);
        addNew(new SkeletonFactory(dungeon), 3);
    }
}
