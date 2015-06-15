package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.*;
import com.mygdx.game.Dungeon.DungeonTiles.EmptyDungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.StairsDownDungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.StairsUpDungeonTile;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.Renderers.DungeonRenderer;

public class Dungeon {
    private static Dungeon activeDungeon;

    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;

    //TODO create a renderable interface and have a list of renderables to iterate through
    public final DungeonRenderer renderer;
    public final Array<CharacterEntity> monsters;

    private final DungeonTile[][] map;
    private final Array<DungeonRoom> dungeonRooms;
    private final int mapWidth;
    private final int mapHeight;
    private float[][] lineOfSightResMap;
    int level;

    StairsDownDungeonTile stairsDownDungeonTile;
    StairsUpDungeonTile stairsUpDungeonTile;
    Dungeon floorAbove;
    Dungeon floorBelow;
    DungeonRoom startRoom;

    public static void setActiveDungeon(Dungeon dungeon){
        activeDungeon = dungeon;
    }

    public static Dungeon getActiveDungeon(){
        return activeDungeon;
    }

    public Dungeon(int mapWidth, int mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        renderer = new DungeonRenderer(this);

        map = new DungeonTile[mapWidth+2][mapHeight+2];
        for(int i = 0; i < mapWidth+2; i++){
            for (int j = 0; j < mapHeight+2; j++){
                map[i][j] = new EmptyDungeonTile(new GridPoint2(i-1, j-1), this);
            }
        }

        dungeonRooms = new Array<DungeonRoom>();
        monsters = new Array<CharacterEntity>();

        level = 1;
    }

    public Dungeon(int mapWidth, int mapHeight, Dungeon floorAbove) {
        this(mapWidth, mapHeight);
        level = floorAbove.getLevel() + 1;
        this.floorAbove = floorAbove;
    }

    public int getRoomCount(){
        return dungeonRooms.size;
    }

    public int getMapWidth(){
        return mapWidth;
    }

    public int getMapHeight(){
        return mapHeight;
    }


    public void addDungeonRoom(DungeonRoom dungeonRoom) {
        dungeonRooms.add(dungeonRoom);
    }

    public DungeonRoom getDungeonRoom(int roomId) {
        return dungeonRooms.get(roomId);
    }

    public boolean isTilePassable(GridPoint2 pos){
        return map[pos.x+1][pos.y+1].isPassable();
    }

    public DungeonTile getDungeonTile(GridPoint2 pos){
        return map[pos.x+1][pos.y+1];
    }

    public DungeonTile getDungeonTile(int x, int y){
        return map[x+1][y+1];
    }

    public void setTile(DungeonTile dungeonTile) {
        GridPoint2 pos = dungeonTile.getPos();
        map[pos.x+1][pos.y+1] = dungeonTile;
    }

    public boolean isTileEmpty(GridPoint2 pos) {
        return map[pos.x][pos.y].isEmpty();
    }

    public Array<Array<AstarNode>> getAstarGraph(){
        Array<Array<AstarNode>> astarGraph = new Array<Array<AstarNode>>();
        for(int i = 0; i < getMapWidth(); i++){
            astarGraph.add(new Array<AstarNode>());
        }
        for (int i = 0; i < getMapWidth(); i++){
            for(int  j = 0; j < getMapHeight(); j++){
                AstarNode node = new AstarNode();
                node.passingCost = getDungeonTile(new GridPoint2(i, j)).getPassingCost();
                node.x = i;
                node.y = j;
                astarGraph.get(i).add(node);
            }
        }
        return astarGraph;
    }

    // Updates a grid of 1s or 0s that represent if a tile is visible. Used by the LOS algo.
    public void updateLineOfSightResistanceMap(){
        lineOfSightResMap = new float[mapWidth+2][mapHeight+2];

        for (int i = 0; i < mapWidth+2; i++){
            for (int j = 0; j < mapHeight+2; j++){
                if(map[i][j].isVisionObstructing()){
                    lineOfSightResMap[i][j] = 1;
                } else {
                    lineOfSightResMap[i][j] = 0;
                }
            }
        }
    }

    public float[][] getLineOfSightResMap(){
        return lineOfSightResMap;
    }

    public DungeonRoom getStartRoom() {
        return startRoom;
    }

    public Dungeon getFloorAbove(){
        return floorAbove;
    }

    public Dungeon getFloorBelow(){
        if (floorBelow == null){
            return floorBelow = DungeonGeneratorFactory.getDungeonGenerator(getLevel()+1).generateDungeonBelow(this);
        } else {
            return floorBelow;
        }
    }

    public int getLevel() {
        return level;
    }

    public StairsDownDungeonTile getStairsDownDungeonTile() {
        return stairsDownDungeonTile;
    }

    public StairsUpDungeonTile getStairsUpDungeonTile() {
        return stairsUpDungeonTile;
    }
}
