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

    //TODO make a basicMonsterFactory that will spawn monsters in a room not ocupied by the player (currently done in the dungeon and dead bahvior classes)
    @Override
    public Skeleton newInstance() {
        Skeleton skeleton = new Skeleton();
        initialise(skeleton);
        return skeleton;
    }

    @Override
    protected Dungeon getDungeon() {
        return dungeon;
    }
}
