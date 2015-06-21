package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonTile;

public class EmptyDungeonTile extends DungeonTile {
    public EmptyDungeonTile(GridPoint2 pos, Dungeon dungeon) {
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
        return null;
    }

    @Override
    public boolean isEmpty(){
        return true;
    }

    @Override
    public boolean isVisible(){
        return false;
    }

    @Override
    public float getVisibilityLevel(){
        return 0;
    }
}
