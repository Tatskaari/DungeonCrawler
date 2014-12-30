package com.mygdx.game.Dungeon.DungeonTiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.DungeonTile;

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
    public boolean isVisible(){
        return false;
    }

    @Override
    public float getVisibilityLevel(){
        return 0;
    }
}
