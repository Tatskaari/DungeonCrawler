package com.mygdx.game.Dungeon.DungeonTiles;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

import java.awt.*;

public abstract class DungeonTile {
    public static final int EMPTY = 0;
    public static final int FLOOR = 10;
    public static final int WALL = 20;
    public static final int DOOR_HORIZONTAL = 30;
    public static final int DOOR_VERTICAL = 40;
    public static final int CORRIDOR_FLOOR = 50;
    public static final int CORRIDOR_WALL = 60;

    private static Bresenham2 bresenham = new Bresenham2();

    private boolean tileHasBeenVisible = false;

    private GridPoint2 pos;

    public DungeonTile(GridPoint2 pos) {
        this.pos = pos;
    }

    public abstract float getCorridorPlacingCost();

    public abstract boolean isVisionObstructing();

    public abstract boolean isPassable();

    public abstract int getTileType();

    public abstract Texture getTileTexture();

    public GridPoint2 getPos(){
        return pos;
    }

    public float checkVisibility() {
        GridPoint2 playerGridPoint = MyGdxGame.player.getPosition();
        Array<GridPoint2> points;

        float distance = (float)Point.distance(pos.x, pos.y, playerGridPoint.x, playerGridPoint.y);

        if(distance > 10){
            return getHasBeenVisibleVisibility();
        }

        points = bresenham.line(pos, playerGridPoint);
        points.removeIndex(0);
        if(points.size > 0){
            points.removeIndex(points.size-1);
        }

        for(GridPoint2 point : points){
            DungeonTile tile = MyGdxGame.dungeon.getDungeonTile(point);
            if (tile.isVisionObstructing()){
                return getHasBeenVisibleVisibility();
            }
        }
        if (distance < 10 ){
            tileHasBeenVisible = true;
            return  1;
        }else
        return getHasBeenVisibleVisibility();
    }

    public boolean isEmpty(){
        return false;
    }

    private float getHasBeenVisibleVisibility(){
        if(tileHasBeenVisible){
            return 0.5f;
        }
        else{
            return 0;
        }
    }

}