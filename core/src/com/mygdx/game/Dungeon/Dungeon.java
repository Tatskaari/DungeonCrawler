package com.mygdx.game.Dungeon;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceLoader;

import java.awt.*;

public class Dungeon {
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;

    private DungeonTile[][] map;
    private Array<DungeonRoom> dungeonRooms;

    private int mapWidth, mapHeight;
    private int tileSize;

    public Dungeon(int mapWidth, int mapHeight, int tileSize){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileSize = tileSize;

        map = new DungeonTile[mapWidth+2][mapHeight+2];
        for(int i = 0; i < mapWidth+2; i++){
            for (int j = 0; j < mapHeight+2; j++){
                map[i][j] = new DungeonTile(new GridPoint2(i-1, j-1));
            }
        }

        dungeonRooms = new Array<DungeonRoom>();
    }

    public void render(){
        for (int i = 0; i < mapWidth; i++){
            for (int j = 0; j < mapHeight; j++){
                DungeonTile tile = getDungeonTile(i, j);
                float colourVal = tile.checkVisibility();
                MyGdxGame.batch.setColor(colourVal, colourVal, colourVal, colourVal);
                if(tile.tileType == DungeonTile.FLOOR || tile.tileType == DungeonTile.CORRIDOR_FLOOR){
                    MyGdxGame.batch.draw(ResourceLoader.floor, i * tileSize, j * tileSize);
                }
                if (tile.tileType == DungeonTile.WALL || tile.tileType == DungeonTile.CORRIDOR_WALL){
                    MyGdxGame.batch.draw(ResourceLoader.wall, i * tileSize, j * tileSize);
                }
                if(tile.tileType == DungeonTile.DOOR_HORIZONTAL){
                    MyGdxGame.batch.draw(ResourceLoader.doorHorizontal, i * tileSize, j * tileSize);
                }
                if(tile.tileType == DungeonTile.DOOR_VERTICAL){
                    MyGdxGame.batch.draw(ResourceLoader.doorVertical, i * tileSize, j * tileSize);
                }
                MyGdxGame.batch.setColor(1,1,1,1);
            }
        }
    }

    public void devRender(){
        for (int i = 0; i < mapWidth; i++){
            for (int j = 0; j < mapHeight; j++){
                DungeonTile tile = getDungeonTile(i, j);
                if(tile.tileType == DungeonTile.FLOOR || tile.tileType == DungeonTile.CORRIDOR_FLOOR){
                    MyGdxGame.batch.draw(ResourceLoader.floor, i * tileSize, j * tileSize);
                }
                if (tile.tileType == DungeonTile.WALL || tile.tileType == DungeonTile.CORRIDOR_WALL){
                    MyGdxGame.batch.draw(ResourceLoader.wall, i * tileSize, j * tileSize);
                }
                if(tile.tileType == DungeonTile.DOOR_HORIZONTAL){
                    MyGdxGame.batch.draw(ResourceLoader.doorHorizontal, i * tileSize, j * tileSize);
                }
                if(tile.tileType == DungeonTile.DOOR_VERTICAL){
                    MyGdxGame.batch.draw(ResourceLoader.doorVertical, i * tileSize, j * tileSize);
                }
            }
        }
    }


    public void setTileType(int x, int y, int type, boolean passable){
        setTileType(new Point(x, y), type, passable);
    }

    public void setTileType(Point pos, int type, boolean passable){
        map[pos.x+1][pos.y+1].tileType = type;
        map[pos.x+1][pos.y+1].passable = passable;
    }

    public int getTileType(Point pos){
        return map[pos.x+1][pos.y+1].tileType;
    }

    public int getRoomCount(){
        return dungeonRooms.size;
    }

    public boolean isTileEmpty(int x, int y){
        if(map[x+1][y+1].tileType == DungeonTile.EMPTY){
            return true;
        }
        return false;
    }

    public int getMapWidth(){
        return mapWidth;
    }

    public int getMapHeight(){
        return mapHeight;
    }

    public boolean isPointInMap(Point pos){
        if(pos.x >= mapWidth || pos.y >= mapHeight){
            return false;
        }
        if(pos.x < 0 || pos.y < 0){
            return false;
        }
        return true;
    }


    public void addDungeonRoom(DungeonRoom dungeonRoom) {
        dungeonRooms.add(dungeonRoom);
    }

    public DungeonRoom getDungeonRoom(int roomId) {
        return dungeonRooms.get(roomId);
    }

    public boolean isTilePassable(GridPoint2 pos){
        return map[pos.x+1][pos.y+1].passable;
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
}
