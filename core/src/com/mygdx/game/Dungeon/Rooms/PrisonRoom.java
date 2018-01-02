package com.mygdx.game.Dungeon.Rooms;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTiles.DoorDungeonTile;
import com.mygdx.game.Dungeon.DungeonTiles.WallDungeonTile;

public class PrisonRoom extends PlainRoom {

    public PrisonRoom(int x, int y, Dungeon dungeon) {
        super(x, y, 13, 11, dungeon);
    }

    @Override
    public void generate() {
        super.generate();
        int yOffset = (getHeight()/2) + 1;

        for (int i = 0; i + 4 <= getWidth(); i += 4){
            placeBoundingWalls(dungeon, getX() + i, getY(), 5, 5);
            placeBoundingWalls(dungeon, getX() + i, getY()+yOffset, 5, 5);

            int doorX = getX() + i + 2;
            dungeon.setTile(new DoorDungeonTile(new GridPoint2(doorX, getY()+yOffset-2), dungeon, true));
            dungeon.setTile(new DoorDungeonTile(new GridPoint2(doorX, getY()+yOffset), dungeon, true));
        }

        dungeon.setTile(new WallDungeonTile(new GridPoint2(getX(), getY()+getHeight()/2), dungeon, true));
        dungeon.setTile(new WallDungeonTile(new GridPoint2(getX()+getWidth()-1, getY()+getHeight()/2), dungeon, true));
    }
}
