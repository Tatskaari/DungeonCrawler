package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.ResourceLoader;

public class CorridorFloorDungeonTile extends DungeonTile {
    public CorridorFloorDungeonTile(GridPoint2 pos) {
        super(pos);
    }

    @Override
    public float getCorridorPlacingCost() {
        return 1;
    }

    @Override
    public boolean isVisionObstructing() {
        return false;
    }

    @Override
    public int getTileType() {
        return DungeonTile.CORRIDOR_FLOOR;
    }

    @Override
    public Texture getTileTexture() {
        return ResourceLoader.floor;
    }

}
