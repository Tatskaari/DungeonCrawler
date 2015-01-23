package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.Goblin;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Characters.Rat;
import com.mygdx.game.Characters.Skeleton;

public class MonsterSpawnPool extends SpawnPool<NonPlayerCharacterEntity>{

    public MonsterSpawnPool(){
        addNew(Skeleton.class, 1);
        addNew(Rat.class, 2);
        addNew(Goblin.class, 5);
    }
}
