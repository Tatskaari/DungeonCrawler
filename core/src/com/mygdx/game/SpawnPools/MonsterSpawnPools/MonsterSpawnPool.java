package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.SpawnPools.SpawnPool;

public abstract class MonsterSpawnPool extends SpawnPool<NonPlayerCharacterEntity> {
    public abstract void initialisePool(Dungeon dungeon);

}
