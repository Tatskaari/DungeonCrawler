package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.CrowFliesHeuristic;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class DungeonUtils {
    public static DungeonRoom getRandomNotStartDungeonRoom(Dungeon dungeon){
        int roomIndex = MathUtils.random(dungeon.getRoomCount() - 1);
        while (roomIndex == dungeon.startRoom.getRoomNumber()){
            roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        }
        return dungeon.getDungeonRoom(roomIndex);
    }

    public static GridPoint2 getRandomTileInRoom(DungeonRoom room){
        GridPoint2 tilePosition = new GridPoint2();

        tilePosition.y = MathUtils.random(room.getY()+1, room.getY() + room.getHeight()-2);
        tilePosition.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);

        return tilePosition;
    }

    public static GridPoint2 getRandomSpawnLocation(Dungeon dungeon){
        DungeonRoom room = getRandomNotStartDungeonRoom(dungeon);
        return getRandomTileInRoom(room);
    }

    public static GridPoint2 getRandomNonVisibleTilePosInAnyRoom(Dungeon dungeon){
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        GridPoint2 tilePos = getRandomTileInRoom(dungeon.getDungeonRoom(roomIndex));
        while(dungeon.getDungeonTile(tilePos).isVisible()){
            roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
            tilePos = getRandomTileInRoom(dungeon.getDungeonRoom(roomIndex));
        }
        return tilePos;
    }


    public static boolean canSeePlayerFrom(GridPoint2 pos){
        return LineOfSight.checkLineOfSight(pos, PlayerCharacterEntity.getInstance().getPosition());
    }


    public static GridPoint2 getRandomTileInAnyRoom(Dungeon dungeon) {
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        return getRandomTileInRoom(dungeon.getDungeonRoom(roomIndex));
    }

    public static Array<AstarNode> generateNewPathBetween(GridPoint2 startPoint, GridPoint2 targetPoint, Dungeon dungeon){
        GameHandler.PATH_GEN_COUNT_THIS_STEP++;

        Array<AstarNode> path;

        Astar astar = new Astar(new CrowFliesHeuristic());
        Array<Array<AstarNode>> astarGraph = dungeon.getAstarGraph();

        AstarNode startNode = astarGraph.get(startPoint.x).get(startPoint.y);
        AstarNode targetNode = astarGraph.get(targetPoint.x).get(targetPoint.y);

        path = astar.getPath(astarGraph, startNode, targetNode);
        path.pop(); //Get rid of the node we're currently on

        return path;
    }

    public static boolean checkDungeonCompletable(Dungeon dungeon){
        DungeonTile startDungeonTile = dungeon.getStairsUpDungeonTile();
        DungeonTile endDungeonTile = dungeon.getStairsDownDungeonTile();

        return generateNewPathBetween(startDungeonTile.getPos(), endDungeonTile.getPos(), dungeon).get(0).fScore < 50000;
    }
}
