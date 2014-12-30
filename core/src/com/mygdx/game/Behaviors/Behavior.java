package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.CrowFliesHeuristic;

import java.awt.*;

/**
 * Created by jony1710 on 28/12/2014.
 */
public abstract class Behavior {

    public abstract Behavior act();

    public boolean isPlayerAdjacent(GridPoint2 pos) {
        GridPoint2 playerPosition = GameHandler.player.getPosition();
        double dist = Point.distance(pos.x, pos.y, playerPosition.x, playerPosition.y);
        return dist < 1.5;
    }

    public Array<AstarNode> generateNewPathBetween(GridPoint2 startPoint, GridPoint2 targetPoint){
        Array<AstarNode> path;

        Astar astar = new Astar(new CrowFliesHeuristic());
        Array<Array<AstarNode>> astarGraph = GameHandler.dungeon.getAstarGraph();

        AstarNode startNode = astarGraph.get(startPoint.x).get(startPoint.y);
        AstarNode targetNode = astarGraph.get(targetPoint.x).get(targetPoint.y);

        path = astar.getPath(astarGraph, startNode, targetNode);
        path.pop(); //Get rid of the node we're currently on

        return path;
    }

    public GridPoint2 getRandomTileInRoom(DungeonRoom room){
        GridPoint2 tilePosition = new GridPoint2();

        tilePosition.y = MathUtils.random(room.getY() + 1, room.getY() + room.getHeight() - 2);
        tilePosition.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);

        return tilePosition;
    }

    public static boolean canSeePlayerFrom(GridPoint2 pos){
        return LineOfSight.checkLineOfSight(pos, GameHandler.player.getPosition());
    }

    public GridPoint2 getRandomTileInAnyRoom() {
        int roomIndex = MathUtils.random(GameHandler.dungeon.getRoomCount()-1);
        return getRandomTileInRoom(GameHandler.dungeon.getDungeonRoom(roomIndex));
    }

    public boolean moveMonsterAlongPath(Monster monster, Array<AstarNode> path){
        AstarNode node = path.peek();
        if(monster.moveTo(node.getPosition())){
            path.pop();
            return true;
        }
        return false;
    }
}
