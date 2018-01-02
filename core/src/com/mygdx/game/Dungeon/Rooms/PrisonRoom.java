package com.mygdx.game.Dungeon.Rooms;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTiles.ChestDungeonTile;
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
            placeCell(dungeon, getX() + i, getY(), true);
            placeCell(dungeon, getX() + i, getY()+yOffset, false);
        }

        dungeon.setTile(new WallDungeonTile(new GridPoint2(getX(), getY()+getHeight()/2), dungeon, true));
        dungeon.setTile(new WallDungeonTile(new GridPoint2(getX()+getWidth()-1, getY()+getHeight()/2), dungeon, true));
    }

    private void placeCell(Dungeon dungeon, int x, int y, boolean southFacing){
        placeBoundingWalls(dungeon, x, y, 5, 5);
        int doorY = southFacing ? y + 4 : y;
        dungeon.setTile(new DoorDungeonTile(new GridPoint2(x+2, doorY), dungeon, true));

        if(MathUtils.randomBoolean(0.2f)){
            dungeon.setTile(new ChestDungeonTile(new GridPoint2(x+2, y+2), dungeon));
        }
    }
}
