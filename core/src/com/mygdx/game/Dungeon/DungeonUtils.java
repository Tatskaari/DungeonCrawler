package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.Rooms.Room;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.CrowFliesHeuristic;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class DungeonUtils {
    public static Room getRandomNotStartDungeonRoom(Dungeon dungeon){
        int roomIndex = MathUtils.random(dungeon.getRoomCount() - 1);
        while (roomIndex == dungeon.startRoom.getRoomNumber()){
            roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        }
        return dungeon.getDungeonRoom(roomIndex);
    }


    public static GridPoint2 getRandomSpawnLocation(Dungeon dungeon){
        Room room = getRandomNotStartDungeonRoom(dungeon);
        return room.getRandomFloorTile();
    }

    public static GridPoint2 getRandomNonVisibleTilePosInAnyRoom(Dungeon dungeon){
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        GridPoint2 tilePos = dungeon.getDungeonRoom(roomIndex).getRandomFloorTile();
        while(dungeon.getDungeonTile(tilePos).isVisible() || dungeon.getDungeonTile(tilePos).isVisionObstructing()){
            roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
            tilePos = dungeon.getDungeonRoom(roomIndex).getRandomFloorTile();
        }
        return tilePos;
    }


    public static boolean canSeePlayerFrom(GridPoint2 pos, Dungeon dungeon){
        return LineOfSight.checkLineOfSight(pos, PlayerCharacterEntity.getInstance().getPosition(), dungeon);
    }


    public static GridPoint2 getRandomTileInAnyRoom(Dungeon dungeon) {
        int roomIndex = MathUtils.random(dungeon.getRoomCount()-1);
        return dungeon.getDungeonRoom(roomIndex).getRandomFloorTile();
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
