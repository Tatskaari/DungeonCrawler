package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.PathFinding.AstarNode;

public class SkeletonAttackPlayerBehavior extends Behavior {
    private Monster monster;
    private final GridPoint2 playersLastKnownPos;
    private Array<AstarNode> path;
    private final GridPoint2 pathTarget;

    public SkeletonAttackPlayerBehavior(Monster monster){
        this.monster = monster;
        path = new Array<AstarNode>();
        playersLastKnownPos = GameHandler.player.getPosition();
        pathTarget = new GridPoint2(-1,-1);
    }

    @Override
    public Behavior act() {
        if (canSeePlayerFrom(monster.getPosition())){
            playersLastKnownPos.set(GameHandler.player.getPosition());
        }else if (!isPlayerPositionKnown()){
            return new SkeletonWanderBehavior(monster);
        }

        if (isPlayerAdjacent(monster.getPosition())){
            monster.attack(GameHandler.player);
        } else {
            moveTowardsPlayer();
        }
        return this;
    }

    private boolean isPlayerPositionKnown() {
        return !(playersLastKnownPos == null || playersLastKnownPos.equals(monster.getPosition()));
    }

    private void moveTowardsPlayer(){
        if (!playersLastKnownPos.equals(pathTarget)){
            path = generateNewPathBetween(monster.getPosition(), playersLastKnownPos);
            pathTarget.set(playersLastKnownPos);
        }
        if (path.size > 0){
            moveMonsterAlongPath(monster, path);
        }
    }
}
