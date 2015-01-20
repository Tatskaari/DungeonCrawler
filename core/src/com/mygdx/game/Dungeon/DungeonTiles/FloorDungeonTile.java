package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.DungeonTile;
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
    public int getTileType() {
        return DungeonTile.FLOOR;
    }

    @Override
    public TextureRegion getTileTexture() {
        return ResourceLoader.floor;
    }

}
