package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class CorridorWallDungeonTile extends DungeonTile {
    public CorridorWallDungeonTile(GridPoint2 pos) {
        super(pos);
    }

    @Override
    public float getCorridorPlacingCost() {
        return 50;
    }

    @Override
    public boolean isVisionObstructing() {
        return true;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public int getTileType() {
        return DungeonTile.CORRIDOR_WALL;
    }

    @Override
    public Texture getTileTexture() {
        return ResourceLoader.wall;
    }

    @Override
    public float getPassingCost() {
        return 100000;
    }
}
