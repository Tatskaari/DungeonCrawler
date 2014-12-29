package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.PathFinding.AstarNode;

/**
 * Created by jony1710 on 28/12/2014.
 */
public abstract class SkeletonBehavior extends Behavior{
    private Monster monster;

    public SkeletonBehavior (Monster monster){
        this.monster = monster;
    }

    @Override
    public Behavior act() {
        return this;
    }

    /*
    @Override
    public Behavior act() {
        boolean targetChanged = false;
        if(canSeePlayerFrom(monster.getPosition())){
            if(!targetPosition.equals(GameHandler.player.getPosition())){
                targetPosition = GameHandler.player.getPosition();
                targetChanged = true;
            }

        }
        else if (targetPosition.equals(monster.getPosition())){
            targetPosition = getRandomTileInAnyRoom();
            targetChanged = true;
        }

        if (targetChanged) {
            generateNewPathBetween(monster.getPosition(), targetPosition);
        }

        if (isPlayerAdjacent(monster.getPosition())){
            GameHandler.player.beAttacked(1);
            System.out.println(GameHandler.player.getHealth());
        } else if (path.size > 0) {
            AstarNode node = path.peek();
            if(GameHandler.dungeon.isTilePassable(node.getPosition())){
                monster.moveTo(node.getPosition());
                path.pop();
            }
        }
        return this;
    }
    */

}
