package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonTiles.*;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.PathFinding.GridBasedHeuristic;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.SpawnPools.MonsterSpawnPool;

public class DungeonGenerator {
    private final int roomMaxSize = 15;
    private final int roomMinSize = 7;
    private MonsterSpawnPool monsterSpawnPool;
    private int requestedRoomCount;
    private int requestedMapWidth;
    private int requestedMapHeight;

    public DungeonGenerator(int mapWidth, int mapHeight, int roomCount){
        requestedRoomCount = roomCount;
        requestedMapHeight = mapHeight;
        requestedMapWidth = mapWidth;
    }

    private Dungeon generateDungeonTiles(Dungeon dungeon) {
        placeRooms(dungeon, requestedRoomCount);
        placeCorridors(dungeon);

        int startRoom = MathUtils.random(dungeon.getRoomCount()-1);
        dungeon.startRoom = dungeon.getDungeonRoom(startRoom);
        placeStartAndEndStairs(dungeon);

        dungeon.updateLineOfSightResistanceMap();

        return dungeon;
    }

    public Dungeon regenerateDungeon(){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight);
        Dungeon oldDungeon = Dungeon.getActiveDungeon();
        dungeon.floorAbove = oldDungeon.floorAbove;
        dungeon.floorBelow = oldDungeon.floorBelow;
        dungeon.level = oldDungeon.level;
        return generateDungeonTiles(dungeon);
    }

    public Dungeon generateDungeonBelow(Dungeon parentDungeon){
        requestedMapHeight = parentDungeon.getMapHeight();
        requestedMapWidth = parentDungeon.getMapWidth();

        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight, parentDungeon);

        generateDungeonTiles(dungeon);

        spawnMonsters(dungeon, dungeon.getRoomCount());
        dungeon.monsters.add(PlayerCharacterEntity.getInstance());

        return dungeon;
    }

    public Dungeon generateDungeon(){
        Dungeon dungeon = new Dungeon(requestedMapWidth, requestedMapHeight);
        return generateDungeonTiles(dungeon);
    }

    private void placeStartAndEndStairs(Dungeon dungeon) {
        GridPoint2 stairsUpPos;
        GridPoint2 stairsDownPos;

        DungeonRoom startRoom = dungeon.startRoom;
        DungeonRoom endRoom = DungeonUtils.getRandomNotStartDungeonRoom(dungeon);

        stairsUpPos = DungeonUtils.getRandomTileInRoom(startRoom);
        stairsDownPos = DungeonUtils.getRandomTileInRoom(endRoom);

        StairsUpDungeonTile stairsUpDungeonTile = new StairsUpDungeonTile(stairsUpPos);
        StairsDownDungeonTile stairsDownDungeonTile = new StairsDownDungeonTile(stairsDownPos);

        dungeon.setTile(stairsUpDungeonTile);
        dungeon.setTile(stairsDownDungeonTile);

        dungeon.stairsDownDungeonTile = stairsDownDungeonTile;
        dungeon.stairsUpDungeonTile = stairsUpDungeonTile;
    }

    public void spawnMonsters(Dungeon dungeon, int monsterCount) {
        monsterSpawnPool = new MonsterSpawnPool(dungeon);
        for (int i = 0; i < monsterCount; i++){
            dungeon.monsters.add(monsterSpawnPool.getNewInstance());
        }
    }

    void placeRooms(Dungeon dungeon, int roomCount){
        for(int roomsAdded = 0; roomsAdded < roomCount; roomsAdded++){
            int width = MathUtils.random(roomMinSize, roomMaxSize);
            int height = MathUtils.random(roomMinSize, roomMaxSize);

            int x = MathUtils.random(dungeon.getMapWidth() - width);
            int y = MathUtils.random(dungeon.getMapHeight() - height);

            addRoom(dungeon, x, y, width, height);

        }
    }

    void placeCorridors(Dungeon dungeon){
        for(int i = 0; i < dungeon.getRoomCount(); i++){
            int endRoom = randomExcluding(0, dungeon.getRoomCount()-1, i);
            connectRooms(dungeon, dungeon.getDungeonRoom(i), dungeon.getDungeonRoom(endRoom));
        }
    }

    private int randomExcluding(int start, int end, int n){
        int result = n;
        while(result == n){
            result = MathUtils.random(start, end);
        }
        return result;
    }

    boolean addRoom(Dungeon dungeon, int x, int y, int width, int height){
        boolean roomCanBeAdded = roomFits(dungeon, x, y, width, height);
        if (roomCanBeAdded) {
            dungeon.addDungeonRoom(new DungeonRoom(x, y, width, height, dungeon.getRoomCount()));

            for(int i = x+1; i < x + width-1; i++){
                for(int j = y+1; j < y + height-1; j++) {
                    dungeon.setTile(new FloorDungeonTile(new GridPoint2(i, j)));
                }
            }

            for (int j = x; j < width+x; j++) {
                dungeon.setTile(new WallDungeonTile(new GridPoint2(j, y)));
                dungeon.setTile(new WallDungeonTile(new GridPoint2(j, height + y - 1)));
            }

            for (int i = y; i < height+y; i++) {
                dungeon.setTile(new WallDungeonTile(new GridPoint2(x, i)));
                dungeon.setTile(new WallDungeonTile(new GridPoint2(x + width - 1, i)));
            }
        }

        return roomCanBeAdded;
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

    void connectRooms(Dungeon dungeon, DungeonRoom startRoom, DungeonRoom endRoom){

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
        int lastDirection = -1;
        for(int i = 0; i < path.size; i++){
            int direction;
            GridPoint2 position = path.get(i).getPosition();
            if((i+1) < path.size){
                GridPoint2 nextPosition = path.get(i+1).getPosition();
                direction = calculateDirection(position, nextPosition);
            }else{
                direction = lastDirection;
            }
            addCorridorTile(dungeon, position, direction);
            if (lastDirection != direction){
                terminateCorridor(dungeon, position);
            }
        }
    }

    private int calculateDirection(GridPoint2 from, GridPoint2 to){
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

    private Array<Array<AstarNode>> getDungeonAsAstarNodeGraph(Dungeon dungeon) {
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

    private boolean addCorridorTile(Dungeon dungeon, GridPoint2 pos, int direction){
        GridPoint2 nextPos;

        if (direction == Dungeon.EAST){
            nextPos = new GridPoint2(pos.x+1, pos.y);
        }
        else if (direction == Dungeon.WEST){
            nextPos = new GridPoint2(pos.x-1, pos.y);
        }
        else if (direction == Dungeon.NORTH){
            nextPos = new GridPoint2(pos.x, pos.y+1);
        }
        else { //SOUTH
            nextPos = new GridPoint2(pos.x, pos.y-1);
        }

        // Set wall tiles to doors unless you are parallel to the wall
        if(dungeon.getTileType(pos) == DungeonTile.WALL){
            if (dungeon.getTileType(nextPos) == DungeonTile.WALL){
                terminateCorridor(dungeon, pos);
                return false;
            }else{
                if (direction == Dungeon.NORTH || direction == Dungeon.SOUTH){
                    dungeon.setTile(new DoorDungeonTile(pos, true));
                }else if (direction == Dungeon.EAST || direction == Dungeon.WEST){
                    dungeon.setTile(new DoorDungeonTile(pos, false));
                }
                return true;
            }
        }

        // Ignore tiles that are already floor tiles
        if (dungeon.getTileType(pos) == DungeonTile.FLOOR){
            return true;
        }

        if(dungeon.getTileType(pos) == DungeonTile.EMPTY || dungeon.getTileType(pos) == DungeonTile.CORRIDOR_WALL) {
            dungeon.setTile(new CorridorFloorDungeonTile(pos));
            if(direction == Dungeon.NORTH || direction == Dungeon.SOUTH){
                addCorridorWall(dungeon, new GridPoint2(pos.x+1, pos.y));
                addCorridorWall(dungeon, new GridPoint2(pos.x-1, pos.y));
            } else {
                addCorridorWall(dungeon, new GridPoint2(pos.x, pos.y+1));
                addCorridorWall(dungeon, new GridPoint2(pos.x, pos.y-1));
            }
            return true;
        }

        terminateCorridor(dungeon, pos);

        // Default to false as we have encountered an unrecognised tile.
        return false;
    }

    private void addCorridorWall(Dungeon dungeon, GridPoint2 pos){
        if (dungeon.getTileType(pos) == DungeonTile.EMPTY){
            dungeon.setTile(new CorridorWallDungeonTile(pos));
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
