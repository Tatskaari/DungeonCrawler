package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.PathFinding.AstarNode;

/**
 * Created by jony1710 on 29/12/2014.
 */
public class SkeletonFindPlayerBehavior extends Behavior {
    private Monster monster;
    private float pathCostThreshold = 50000;
    private GridPoint2 targetPoint;
    private GridPoint2 pathTarget;
    private Array<AstarNode> path;

    public SkeletonFindPlayerBehavior(Monster monster){
        this.monster = monster;
        targetPoint = new GridPoint2();
        pathTarget = new GridPoint2();
        path = new Array<AstarNode>();
        getNewPath();
    }

    @Override
    public Behavior act() {
        if (!targetPoint.equals(pathTarget) || monster.getPosition().equals(pathTarget)){
            if (MathUtils.randomBoolean()){
                return new SkeletonWanderBehavior(monster);
            }
            getNewPath();
        }
        if (!moveMonsterAlongPath(monster, path)){
            getNewPath();
        }
        return this;
    }

    private void getNewPath(){
        int tries = 0;
        GridPoint2 potentialTarget = getRandomTileInAnyRoom();
        Array<AstarNode> potentialPath = generateNewPathBetween(monster.getPosition(), potentialTarget);

        while (potentialPath.size == 0 || potentialPath.get(0).fScore > pathCostThreshold){
            potentialTarget = getRandomTileInAnyRoom();
            potentialPath = generateNewPathBetween(monster.getPosition(), potentialTarget);
            tries++;
            if (tries > GameHandler.dungeon.getRoomCount()*2){
                return;
            }
        }

        pathTarget.set(potentialTarget);

        path = potentialPath;
    }
}
