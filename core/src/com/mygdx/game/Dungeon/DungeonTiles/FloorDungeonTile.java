package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class FloorDungeonTile extends DungeonTile {
    public FloorDungeonTile(GridPoint2 pos) {
        super(pos);
    }

    @Override
    public float getCorridorPlacingCost() {
        return 200;
    }

    @Override
    public boolean isVisionObstructing() {
        return false;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public int getTileType() {
        return DungeonTile.FLOOR;
    }

    @Override
    public Texture getTileTexture() {
        return ResourceLoader.floor;
    }

    @Override
    public float getPassingCost() {
        return 5;
    }
}
