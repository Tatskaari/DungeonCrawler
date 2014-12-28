package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class DoorDungeonTile extends DungeonTile {
    private int tileType;
    private Texture texture;
    public DoorDungeonTile(GridPoint2 pos, boolean isHorizontal) {
        super(pos);
        if(isHorizontal){
            tileType = DungeonTile.DOOR_HORIZONTAL;
            texture = ResourceLoader.doorHorizontal;
        }else{
            tileType = DungeonTile.DOOR_VERTICAL;
            texture = ResourceLoader.doorVertical;
        }
    }

    @Override
    public float getCorridorPlacingCost() {
        return 9000;
    }

    @Override
    public boolean isVisionObstructing() {
        return true;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public int getTileType() {
        return tileType;
    }

    @Override
    public Texture getTileTexture() {
        return texture;
    }

    @Override
    public float getPassingCost() {
        return 5;
    }
}
