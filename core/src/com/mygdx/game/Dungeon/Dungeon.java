package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.*;
import com.mygdx.game.Dungeon.DungeonTiles.EmptyDungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.StairsDownDungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.StairsUpDungeonTile;
import com.mygdx.game.GameHandler;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.Renderers.DungeonRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.Screens.GameScreen;

public class Dungeon {
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;

    public Renderer renderer;
    public Array<CharacterEntity> monsters;

    private DungeonTile[][] map;
    private Array<DungeonRoom> dungeonRooms;
    private int mapWidth, mapHeight;
    private int tileSize;
    private float[][] LineOfSightResMap;
    protected int level;

    protected StairsDownDungeonTile stairsDownDungeonTile;
    protected StairsUpDungeonTile stairsUpDungeonTile;
    protected Dungeon floorAbove;
    protected Dungeon floorBelow;
    protected DungeonRoom startRoom;
    protected DungeonRoom endRoom;

    public Dungeon(int mapWidth, int mapHeight, int tileSize){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileSize = tileSize;

        renderer = new DungeonRenderer(this);

        map = new DungeonTile[mapWidth+2][mapHeight+2];
        for(int i = 0; i < mapWidth+2; i++){
            for (int j = 0; j < mapHeight+2; j++){
                map[i][j] = new EmptyDungeonTile(new GridPoint2(i-1, j-1));
            }
        }

        dungeonRooms = new Array<DungeonRoom>();
        monsters = new Array<CharacterEntity>();

        level = 1;
    }

    public Dungeon(int mapWidth, int mapHeight, int tileSize, Dungeon floorAbove) {
        this(mapWidth, mapHeight, tileSize);
        level = floorAbove.getLevel() + 1;
        this.floorAbove = floorAbove;
    }



    public int getTileType(GridPoint2 pos){
        return map[pos.x+1][pos.y+1].getTileType();
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

    public int getTileSize() {
        return tileSize;
    }

    public DungeonTile getDungeonTile(GridPoint2 coord){
        return map[coord.x+1][coord.y+1];
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

    public void updateLineOfSightResistanceMap(){
        LineOfSightResMap = new float[mapWidth+2][mapHeight+2];

        for (int i = 0; i < mapWidth+2; i++){
            for (int j = 0; j < mapHeight+2; j++){
                if(map[i][j].isVisionObstructing()){
                    LineOfSightResMap[i][j] = 1;
                } else {
                    LineOfSightResMap[i][j] = 0;
                }
            }
        }
    }

    public float[][] getLineOfSightResMap(){
        return LineOfSightResMap;
    }

    public DungeonRoom getStartRoom() {
        return startRoom;
    }

    public DungeonRoom getEndRoom() {
        return endRoom;
    }

    public Dungeon getFloorAbove(){
        return floorAbove;
    }

    public Dungeon getFloorBelow(){
        if (floorBelow == null){
            return floorBelow = GameHandler.dungeonGenerator.generateDungeonBelow(this);
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
