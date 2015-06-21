package com.mygdx.game.SpawnPools.MonsterSpawnPools;

import com.mygdx.game.Characters.MonsterCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.SpawnPools.SpawnPool;

public abstract class MonsterSpawnPool extends SpawnPool<MonsterCharacterEntity> {
    public abstract void initialisePool(Dungeon dungeon);

}
