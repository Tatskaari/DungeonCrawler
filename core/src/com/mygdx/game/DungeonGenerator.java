package com.mygdx.game;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

/**
 * Created by jony1710 on 22/12/2014.
 */
public class DungeonGenerator {
    public int roomMaxSize = 15;
    public int roomMinSize = 7;


    private Dungeon dungeon;
    private int requestedRoomCount;
    private int requestedMapWidth;
    private int requestedMapHeight;

    public Dungeon generateDungeon() {
        return generateDungeon(requestedMapWidth, requestedMapHeight, requestedRoomCount);
    }

    public Dungeon generateDungeon(int mapWidth, int mapHeight, int roomCount){
        requestedRoomCount = roomCount;
        requestedMapHeight = mapHeight;
        requestedMapWidth = mapWidth;
        dungeon = new Dungeon(mapWidth, mapHeight, ResourceLoader.floor.getWidth());

        placeRooms(roomCount);
        placeCorridors();
        return dungeon;
    }

    public void placeRooms(int roomCount){
        for(int roomsAdded = 0; roomsAdded < roomCount; roomsAdded++){
            int width = MathUtils.random(roomMinSize, roomMaxSize);
            int height = MathUtils.random(roomMinSize, roomMaxSize);

            int x = MathUtils.random(dungeon.getMapWidth() - width);
            int y = MathUtils.random(dungeon.getMapHeight() - height);

            addRoom(x, y, width, height);

        }
    }

    public void placeCorridors(){
        for(int i = 0; i < dungeon.getRoomCount(); i++){
            int endRoom = randomExcluding(0, dungeon.getRoomCount()-1, i);
            connectRooms(dungeon.getDungeonRoom(i), dungeon.getDungeonRoom(endRoom));
        }
    }

    private int randomExcluding(int start, int end, int n){
        int result = n;
        while(result == n){
            result = MathUtils.random(start, end);
        }
        return result;
    }

    public boolean addRoom(int x, int y, int width, int height){
        boolean roomCanBeAdded = roomFits(x, y, width, height);
        if (roomCanBeAdded) {
            dungeon.addDungeonRoom(new DungeonRoom(x, y, width, height));

            for(int i = x+1; i < x + width-1; i++){
                for(int j = y+1; j < y + height-1; j++) {
                    dungeon.setTileType(i, j, DungeonTile.FLOOR, true);
                }
            }

            for (int j = x; j < width+x; j++) {
                dungeon.setTileType(j, y, DungeonTile.WALL, false);
                dungeon.setTileType(j, height + y - 1, DungeonTile.WALL, false);
            }

            for (int i = y; i < height+y; i++) {
                dungeon.setTileType(x, i, DungeonTile.WALL, false);
                dungeon.setTileType(x + width - 1, i, DungeonTile.WALL, false);
            }
        }

        return roomCanBeAdded;
    }

    private boolean roomFits(int x, int y, int width, int height){
        for(int i = x; i < x + width; i++){
            for(int j = y; j < y + height; j++){
                if(!dungeon.isTileEmpty(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    public void connectRooms(DungeonRoom startRoom, DungeonRoom endRoom){

        Point startPoint;
        Point endPoint;

        startPoint = getRandomTileInRoom(startRoom);
        endPoint = getRandomTileInRoom(endRoom);

        Astar astar = new Astar();
        Array<Array<AstarNode>> dungeonAsGraph = getDungeonAsAstarNodeGraph();
        Array<AstarNode> path = astar.getPath(dungeonAsGraph, dungeonAsGraph.get(startPoint.x).get(startPoint.y), dungeonAsGraph.get(endPoint.x).get(endPoint.y));

        placeCorridorAlongPath(path);
    }

    private void placeCorridorAlongPath(Array<AstarNode> path){
        int lastDirection = -1;
        for(int i = 0; i < path.size; i++){
            int direction;
            Point position = path.get(i).getPosition();
            if((i+1) < path.size){
                Point nextPosition = path.get(i+1).getPosition();
                direction = calculateDirection(position, nextPosition);
            }else{
                direction = lastDirection;
            }
            addCorridorTile(position, direction);
            if (lastDirection != direction){
                terminateCorridor(position);
            }
        }
    }

    private int calculateDirection(Point from, Point to){
        if(from.x == to.x){
            if (from.y < to.y){
                return Dungeon.NORTH;
            }else if (from.y > to.y){
                return Dungeon.SOUTH;
            }
        }else if(from.y == to.y){
            if (from.x < to.x){
                return Dungeon.EAST;
            }else if (from.x > to.x){
                return Dungeon.WEST;
            }
        }

        return -1;
    }

    private Array<Array<AstarNode>> getDungeonAsAstarNodeGraph() {
        Array<Array<AstarNode>> graph = new Array<Array<AstarNode>>();
        for(int i = 0; i < dungeon.getMapWidth(); i++){
            graph.add(new Array<AstarNode>());
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

    private Point getRandomTileInRoom(DungeonRoom room){
        Point tilePosition = new Point();

        tilePosition.y = MathUtils.random(room.getY()+1, room.getY() + room.getHeight()-2);
        tilePosition.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);

        return tilePosition;
    }


    private boolean addCorridorTile(Point pos, int direction){
        Point nextPos;

        if (!dungeon.isPointInMap(pos)){
            return false;
        }

        if (direction == Dungeon.EAST){
            nextPos = new Point(pos.x+1, pos.y);
        }
        else if (direction == Dungeon.WEST){
            nextPos = new Point(pos.x-1, pos.y);
        }
        else if (direction == Dungeon.NORTH){
            nextPos = new Point(pos.x, pos.y+1);
        }
        else { //SOUTH
            nextPos = new Point(pos.x, pos.y-1);
        }

        // Set wall tiles to doors unless you are parallel to the wall
        if(dungeon.getTileType(pos) == DungeonTile.WALL){
            if (dungeon.getTileType(nextPos) == DungeonTile.WALL){
                terminateCorridor(pos);
                return false;
            }else{
                if (direction == Dungeon.NORTH || direction == Dungeon.SOUTH){
                    dungeon.setTileType(pos, DungeonTile.DOOR_HORIZONTAL, true);
                }else if (direction == Dungeon.EAST || direction == Dungeon.WEST){
                    dungeon.setTileType(pos, DungeonTile.DOOR_VERTICAL, true);
                }
                return true;
            }
        }

        // Ignore tiles that are already floor tiles
        if (dungeon.getTileType(pos) == DungeonTile.FLOOR){
            return true;
        }

        if(dungeon.getTileType(pos) == DungeonTile.EMPTY || dungeon.getTileType(pos) == DungeonTile.CORRIDOR_WALL) {
            dungeon.setTileType(pos, DungeonTile.CORRIDOR_FLOOR, true);
            if(direction == Dungeon.NORTH || direction == Dungeon.SOUTH){
                addCorridorWall(new Point(pos.x+1, pos.y));
                addCorridorWall(new Point(pos.x-1, pos.y));
            } else {
                addCorridorWall(new Point(pos.x, pos.y+1));
                addCorridorWall(new Point(pos.x, pos.y-1));
            }
            return true;
        }

        // Default to false as we have encountered an unrecognised tile.
        terminateCorridor(pos);
        return false;
    }

    private void addCorridorWall(Point pos){
        if (dungeon.getTileType(pos) == DungeonTile.EMPTY && dungeon.isPointInMap(pos)){
            dungeon.setTileType(pos, DungeonTile.CORRIDOR_WALL, false);
        }
    }

    private void terminateCorridor(Point pos){
        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1;  j++){
                addCorridorWall(new Point(pos.x + i, pos.y + j));
            }
        }
    }

}
