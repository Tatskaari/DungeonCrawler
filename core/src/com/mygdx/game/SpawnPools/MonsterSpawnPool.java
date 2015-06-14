package com.mygdx.game.SpawnPools;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;

public abstract class MonsterSpawnPool extends SpawnPool<NonPlayerCharacterEntity> {
    public abstract void initialisePool(Dungeon dungeon);

}
