package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;

public class EmptyDungeonTile extends DungeonTile {
    public EmptyDungeonTile(GridPoint2 pos) {
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
        return 0;
    }

    @Override
    public Texture getTileTexture() {
        return null;
    }

    @Override
    public boolean isEmpty(){
        return true;
    }

    @Override
    public float checkVisibility(){
        return 0;
    }
}
