package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.ResourceLoader;

public class CorridorWallDungeonTile extends DungeonTile {
    public CorridorWallDungeonTile(GridPoint2 pos, Dungeon dungeon) {
        super(pos, dungeon);
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
    public TextureRegion getTileTexture() {
        return ResourceLoader.wall;
    }

}
