package com.mygdx.game.Characters.Factories;

import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Characters.Skeleton;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.Factory;

public class SkeletonFactory extends MonsterFactory{

    private final Dungeon dungeon;

    public SkeletonFactory(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public Skeleton newInstance() {
        Skeleton skeleton = new Skeleton(dungeon);
        initialise(skeleton);
        return skeleton;
    }

    @Override
    protected Dungeon getDungeon() {
        return dungeon;
    }
}
