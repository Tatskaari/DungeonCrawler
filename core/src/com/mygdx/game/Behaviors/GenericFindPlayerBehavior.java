package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.GameHandler;
import com.mygdx.game.PathFinding.AstarNode;

public class GenericFindPlayerBehavior extends Behavior {
    private final NonPlayerCharacterEntity character;
    private final float pathCostThreshold = 50000;
    private final GridPoint2 targetPoint;
    private final GridPoint2 pathTarget;
    private Array<AstarNode> path;

    public GenericFindPlayerBehavior(NonPlayerCharacterEntity character){
        this.character = character;
        targetPoint = new GridPoint2();
        pathTarget = new GridPoint2();
        path = new Array<AstarNode>();
        getNewPath();
    }

    @Override
    public Behavior act() {
        if (!targetPoint.equals(pathTarget) || character.getPosition().equals(pathTarget)){
            if (MathUtils.randomBoolean()){
                return new GenericWanderBehavior(character);
            }
            if (!getNewPath()){
                return new GenericWanderBehavior(character);
            }
        }
        if (!moveMonsterAlongPath(character, path)){
            if (!getNewPath()){
                return new GenericWanderBehavior(character);
            }
        }
        return this;
    }

    private boolean getNewPath(){
        Dungeon dungeon = Dungeon.getActiveDungeon();
        int tries = 0;
        GridPoint2 potentialTarget = DungeonUtils.getRandomTileInAnyRoom(dungeon);
        Array<AstarNode> potentialPath = DungeonUtils.generateNewPathBetween(character.getPosition(), potentialTarget, character.getDungeon());

        //Loop trying to find a path to a random room. Give up after 3 tries.
        //TODO build the fScore limit into the algo to avoid unnecesairy path generation
        while (potentialPath.size == 0 || potentialPath.get(0).fScore > pathCostThreshold){
            potentialTarget = DungeonUtils.getRandomTileInAnyRoom(dungeon);
            potentialPath = DungeonUtils.generateNewPathBetween(character.getPosition(), potentialTarget, character.getDungeon());
            tries++;
            if (tries >= 3){
                return false;
            }
        }

        pathTarget.set(potentialTarget);

        path = potentialPath;

        return true;
    }
}
