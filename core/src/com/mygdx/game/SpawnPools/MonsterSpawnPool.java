package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.Factories.GoblinFactory;
import com.mygdx.game.Characters.Factories.RatFactory;
import com.mygdx.game.Characters.Factories.SkeletonFactory;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;

public class MonsterSpawnPool extends SpawnPool<NonPlayerCharacterEntity> {
    private final Dungeon dungeon;

    public MonsterSpawnPool(Dungeon dungeon){
        this.dungeon = dungeon;

        //TODO Add factories for rats and goblins
        addNew(new SkeletonFactory(dungeon), 3);
        addNew(new RatFactory(), 5);
        addNew(new GoblinFactory(), 2);
    }
}
