package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.Factories.GoblinFactory;
import com.mygdx.game.Characters.Factories.RatFactory;
import com.mygdx.game.Characters.Factories.SkeletonFactory;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;

public class MonsterSpawnPool extends SpawnPool<NonPlayerCharacterEntity> {

    public MonsterSpawnPool(Dungeon dungeon){
        addNew(new RatFactory(dungeon), 5);
        addNew(new SkeletonFactory(dungeon), 3);
        addNew(new GoblinFactory(dungeon), 2);
    }
}
