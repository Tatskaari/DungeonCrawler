package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.Factory;

abstract class MonsterFactory implements Factory<NonPlayerCharacterEntity> {
    void initialise(NonPlayerCharacterEntity characterEntity){
        characterEntity.setPos(DungeonUtils.getRandomSpawnLocation(getDungeon()));
    }

    protected abstract Dungeon getDungeon();
}
