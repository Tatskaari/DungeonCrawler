package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
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
            getNewPath();
        }
        if (!moveMonsterAlongPath(character, path)){
            getNewPath();
        }
        return this;
    }

    private void getNewPath(){
        int tries = 0;
        GridPoint2 potentialTarget = getRandomTileInAnyRoom();
        Array<AstarNode> potentialPath = generateNewPathBetween(character.getPosition(), potentialTarget);

        while (potentialPath.size == 0 || potentialPath.get(0).fScore > pathCostThreshold){
            potentialTarget = getRandomTileInAnyRoom();
            potentialPath = generateNewPathBetween(character.getPosition(), potentialTarget);
            tries++;
            if (tries > GameHandler.dungeon.getRoomCount()*2){
                return;
            }
        }

        pathTarget.set(potentialTarget);

        path = potentialPath;
    }
}
