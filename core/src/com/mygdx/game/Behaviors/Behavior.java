package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.CrowFliesHeuristic;

import java.awt.*;

public abstract class Behavior {

    public abstract Behavior act();

    boolean isPlayerAdjacent(GridPoint2 pos) {
        GridPoint2 playerPosition = GameHandler.player.getPosition();
        double dist = Point.distance(pos.x, pos.y, playerPosition.x, playerPosition.y);
        return dist < 1.5;
    }

    Array<AstarNode> generateNewPathBetween(GridPoint2 startPoint, GridPoint2 targetPoint){
        Array<AstarNode> path;

        Astar astar = new Astar(new CrowFliesHeuristic());
        Array<Array<AstarNode>> astarGraph = GameHandler.dungeon.getAstarGraph();

        AstarNode startNode = astarGraph.get(startPoint.x).get(startPoint.y);
        AstarNode targetNode = astarGraph.get(targetPoint.x).get(targetPoint.y);

        path = astar.getPath(astarGraph, startNode, targetNode);
        path.pop(); //Get rid of the node we're currently on

        return path;
    }

    boolean moveMonsterAlongPath(CharacterEntity characterEntity, Array<AstarNode> path){
        if (path.size == 0){
            return false;
        }
        AstarNode node = path.peek();
        if(characterEntity.moveTo(node.getPosition())){
            path.pop();
            return true;
        }
        return false;
    }
}
