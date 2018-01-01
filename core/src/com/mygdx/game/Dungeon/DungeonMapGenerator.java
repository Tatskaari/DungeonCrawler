package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonTiles.*;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.GridBasedHeuristic;
import com.mygdx.game.Utils.MiscUtils;


class DungeonMapGenerator {
    private enum Direction {
        VERTICAL, HORIZONTAL
    }

    private static final int ROOM_MAX_SIZE = 15;
    private static final int ROOM_MIN_SIZE = 7;

    void generateDungeonTiles(Dungeon dungeon, int requestedRoomCount) {
        placeRooms(dungeon, requestedRoomCount);
        placeCorridors(dungeon);

        int startRoom = MathUtils.random(dungeon.getRoomCount() - 1);
        dungeon.startRoom = dungeon.getDungeonRoom(startRoom);
        placeStartAndEndStairs(dungeon);

        dungeon.updateLineOfSightResistanceMap();
    }

    private void placeStartAndEndStairs(Dungeon dungeon) {
        GridPoint2 stairsUpPos;
        GridPoint2 stairsDownPos;

        DungeonRoom startRoom = dungeon.startRoom;
        DungeonRoom endRoom = DungeonUtils.getRandomNotStartDungeonRoom(dungeon);

        stairsUpPos = DungeonUtils.getRandomTileInRoom(startRoom);
        stairsDownPos = DungeonUtils.getRandomTileInRoom(endRoom);

        StairsUpDungeonTile stairsUpDungeonTile = new StairsUpDungeonTile(stairsUpPos, dungeon);
        StairsDownDungeonTile stairsDownDungeonTile = new StairsDownDungeonTile(stairsDownPos, dungeon);

        dungeon.setTile(stairsUpDungeonTile);
        dungeon.setTile(stairsDownDungeonTile);

        dungeon.stairsDownDungeonTile = stairsDownDungeonTile;
        dungeon.stairsUpDungeonTile = stairsUpDungeonTile;
    }

    private void placeRooms(Dungeon dungeon, int roomCount){
        for(int roomsAdded = 0; roomsAdded < roomCount; roomsAdded++){
            int width = MathUtils.random(ROOM_MIN_SIZE, ROOM_MAX_SIZE);
            int height = MathUtils.random(ROOM_MIN_SIZE, ROOM_MAX_SIZE);

            int x = MathUtils.random(dungeon.getMapWidth() - width);
            int y = MathUtils.random(dungeon.getMapHeight() - height);

            addRoom(dungeon, x, y, width, height);
        }
    }

    private void placeCorridors(Dungeon dungeon){
        for(int i = 0; i < dungeon.getRoomCount(); i++){
            int targetRoomNumber = MiscUtils.randomIntExcluding(0, dungeon.getRoomCount()-1, i);
            connectRooms(dungeon, dungeon.getDungeonRoom(i), dungeon.getDungeonRoom(targetRoomNumber));
        }
    }

    private void addRoom(Dungeon dungeon, int x, int y, int width, int height){
        if (roomFits(dungeon, x, y, width, height)) {
            dungeon.addDungeonRoom(new DungeonRoom(x, y, width, height, dungeon.getRoomCount()));

            for(int i = x+1; i < x + width-1; i++){
                for(int j = y+1; j < y + height-1; j++) {
                    dungeon.setTile(new FloorDungeonTile(new GridPoint2(i, j), dungeon));
                }
            }

            for (int j = x; j < width+x; j++) {
                dungeon.setTile(new WallDungeonTile(new GridPoint2(j, y), dungeon));
                dungeon.setTile(new WallDungeonTile(new GridPoint2(j, height + y - 1), dungeon));
            }

            for (int i = y; i < height+y; i++) {
                dungeon.setTile(new WallDungeonTile(new GridPoint2(x, i), dungeon));
                dungeon.setTile(new WallDungeonTile(new GridPoint2(x + width - 1, i), dungeon));
            }
        }
    }

    private boolean roomFits(Dungeon dungeon, int x, int y, int width, int height){
        for(int i = x; i < x + width; i++){
            for(int j = y; j < y + height; j++){
                if(!dungeon.isTileEmpty(new GridPoint2(i, j))){
                    return false;
                }
            }
        }
        return true;
    }

    private void connectRooms(Dungeon dungeon, DungeonRoom startRoom, DungeonRoom endRoom){
        GridPoint2 startPoint;
        GridPoint2 endPoint;

        startPoint = DungeonUtils.getRandomTileInRoom(startRoom);
        endPoint = DungeonUtils.getRandomTileInRoom(endRoom);

        Astar astar = new Astar(new GridBasedHeuristic());
        Array<Array<AstarNode>> dungeonAsGraph = getDungeonAsAstarNodeGraph(dungeon);
        Array<AstarNode> path = astar.getPath(dungeonAsGraph, dungeonAsGraph.get(startPoint.x).get(startPoint.y), dungeonAsGraph.get(endPoint.x).get(endPoint.y));

        placeCorridorAlongPath(dungeon, path);
    }

    private void placeCorridorAlongPath(Dungeon dungeon, Array<AstarNode> path){
        // The first and last points will be inside the room so don't need to be processed
        for(int i = 1; i < path.size-1; i++){
            GridPoint2 nextPosition = path.get(i + 1).getPosition();
            GridPoint2 position = path.get(i).getPosition();
            Direction direction = calculateDirection(position, nextPosition);
            addCorridorTile(dungeon, position, direction);

            Direction lastDirection = calculateDirection(path.get(i-1).getPosition(), position);
            // if we have changed direction then terminate the corridor to add the extra wall tiles for the corner
            if (direction != lastDirection){
                terminateCorridor(dungeon, position);
            }
        }
    }

    private Direction calculateDirection(GridPoint2 from, GridPoint2 to){
        if(from.x == to.x){
            return Direction.VERTICAL;
        }else if(from.y == to.y){
            return Direction.HORIZONTAL;
        } else {
            throw new RuntimeException("Unable to calculate direction: " + to + " is not on a cardinal direction from " + from);
        }
    }

    private Array<Array<AstarNode>> getDungeonAsAstarNodeGraph(Dungeon dungeon) {
        Array<Array<AstarNode>> graph = new Array<>();
        for(int i = 0; i < dungeon.getMapWidth(); i++){
            graph.add(new Array<>());
        }
        for (int i = 0; i < dungeon.getMapWidth(); i++){
            for(int  j = 0; j < dungeon.getMapHeight(); j++){
                AstarNode node = new AstarNode();
                node.passingCost = dungeon.getDungeonTile(new GridPoint2(i, j)).getCorridorPlacingCost();
                node.x = i;
                node.y = j;
                graph.get(i).add(node);
            }
        }
        return graph;
    }

    private void addCorridorTile(Dungeon dungeon, GridPoint2 pos, Direction direction){
        DungeonTile tile = dungeon.getDungeonTile(pos);
        // Set wall tiles this corridor passes through to doors
        if(tile instanceof WallDungeonTile){
            if (direction == Direction.VERTICAL ){
                // Place a perpendicular door across the corridor
                dungeon.setTile(new DoorDungeonTile(pos, dungeon, true));
            } else {
                dungeon.setTile(new DoorDungeonTile(pos, dungeon, false));
            }
        }
        // Otherwise set the tile to a floor tiles with walls either side
        else if(tile instanceof EmptyDungeonTile || tile instanceof CorridorWallDungeonTile) {
            dungeon.setTile(new CorridorFloorDungeonTile(pos, dungeon));
            if(direction == Direction.VERTICAL){
                addCorridorWall(dungeon, new GridPoint2(pos.x+1, pos.y));
                addCorridorWall(dungeon, new GridPoint2(pos.x-1, pos.y));
            } else {
                addCorridorWall(dungeon, new GridPoint2(pos.x, pos.y+1));
                addCorridorWall(dungeon, new GridPoint2(pos.x, pos.y-1));
            }
        }
    }

    private void addCorridorWall(Dungeon dungeon, GridPoint2 pos){
        if (dungeon.getDungeonTile(pos) instanceof EmptyDungeonTile){
            dungeon.setTile(new CorridorWallDungeonTile(pos, dungeon));
        }
    }

    private void terminateCorridor(Dungeon dungeon, GridPoint2 pos){
        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1;  j++){
                addCorridorWall(dungeon, new GridPoint2(pos.x + i, pos.y + j));
            }
        }
    }
}
