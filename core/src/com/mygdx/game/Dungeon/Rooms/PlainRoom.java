package com.mygdx.game.Dungeon.Rooms;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTiles.FloorDungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.WallDungeonTile;

public class PlainRoom extends Room {
    public PlainRoom(int x, int y, int width, int height, Dungeon dungeon) {
        super(x, y, width, height, dungeon);
    }

    @Override
    public void generate() {
        placeBoundingWalls(dungeon, getX(), getY(), getWidth(), getHeight());
    }

    void placeBoundingWalls(Dungeon dungeon, int x, int y, int width, int height){
        for(int i = x+1; i < x + width-1; i++){
            for(int j = y+1; j < y + height-1; j++) {
                dungeon.setTile(new FloorDungeonTile(new GridPoint2(i, j), dungeon));
            }
        }

        for (int j = x; j < width+x; j++) {
            dungeon.setTile(new WallDungeonTile(new GridPoint2(j, y), dungeon, false));
            dungeon.setTile(new WallDungeonTile(new GridPoint2(j, height + y - 1), dungeon, false));
        }

        for (int i = y; i < height+y; i++) {
            dungeon.setTile(new WallDungeonTile(new GridPoint2(x, i), dungeon, false));
            dungeon.setTile(new WallDungeonTile(new GridPoint2(x + width - 1, i), dungeon, false));
        }
    }
}
