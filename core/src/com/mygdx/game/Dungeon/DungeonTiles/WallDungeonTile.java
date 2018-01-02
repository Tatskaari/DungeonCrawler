package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.ResourceLoader;

public class WallDungeonTile extends DungeonTile {
    private final boolean isDoorNode;

    public WallDungeonTile(GridPoint2 pos, Dungeon dungeon, boolean isDoorNode) {
        super(pos, dungeon);
        this.isDoorNode = isDoorNode;
    }

    @Override
    public float getCorridorPlacingCost() {
        return isDoorNode ? 1 : 10000;
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
        return ResourceLoader.getResTextureRegion("wall");
    }

}
