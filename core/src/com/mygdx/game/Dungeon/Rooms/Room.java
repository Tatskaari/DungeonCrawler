package com.mygdx.game.Dungeon.Rooms;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTiles.FloorDungeonTile;

public abstract class Room {
    final int x;
    final int y;
    final int width;
    final int height;
    final int roomNumber;
    final Dungeon dungeon;

    Room(int x, int y, int width, int height, Dungeon dungeon){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roomNumber = dungeon.getRoomCount();
        this.dungeon = dungeon;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRoomNumber() {
        return roomNumber;
    }


    public abstract void generate();

    public GridPoint2 getRandomFloorTile() {
        GridPoint2 pos = new GridPoint2();

        pos.y = MathUtils.random(getY()+1, getY() + getHeight()-2);
        pos.x = MathUtils.random(getX()+1, getX() + getWidth()-2);

        if (dungeon.getDungeonTile(pos) instanceof FloorDungeonTile){
            return pos;
        } else {
            return getRandomFloorTile();
        }
    }
}
