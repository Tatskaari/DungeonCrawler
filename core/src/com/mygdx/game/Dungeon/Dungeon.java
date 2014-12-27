package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonTiles.DungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.EmptyDungeonTile;
import com.mygdx.game.Renderers.DevDungeonRenderer;
import com.mygdx.game.Renderers.DungeonRenderer;
import com.mygdx.game.Renderers.Renderer;

public class Dungeon {
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;

    private DungeonTile[][] map;
    private Array<DungeonRoom> dungeonRooms;

    private int mapWidth, mapHeight;
    private int tileSize;

    public Renderer renderer;
    public Renderer devRenderer;

    public Dungeon(int mapWidth, int mapHeight, int tileSize){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileSize = tileSize;

        devRenderer = new DevDungeonRenderer(this);
        renderer = new DungeonRenderer(this);

        map = new DungeonTile[mapWidth+2][mapHeight+2];
        for(int i = 0; i < mapWidth+2; i++){
            for (int j = 0; j < mapHeight+2; j++){
                map[i][j] = new EmptyDungeonTile(new GridPoint2(i-1, j-1));
            }
        }

        dungeonRooms = new Array<DungeonRoom>();
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
}
