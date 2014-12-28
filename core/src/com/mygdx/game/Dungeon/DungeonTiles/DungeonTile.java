package com.mygdx.game.Dungeon.DungeonTiles;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameHandler;
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


    public float getVisibilityLevel() {
        if(isVisible()){
            tileHasBeenVisible = true;
            return 1;
        } else {
            return getHasBeenVisibleVisibility();
        }
    }

    public boolean isVisible(){
        GridPoint2 playerGridPoint = GameHandler.player.getPosition();

        float distance = (float)Point.distance(pos.x, pos.y, playerGridPoint.x, playerGridPoint.y);
        if(distance > 10){
            return false;
        }else{
            return isLOSBlocked();
        }
    }

    private float getHasBeenVisibleVisibility(){
        if(tileHasBeenVisible){
            return 0.5f;
        }
        else{
            return 0;
        }
    }

    private boolean isLOSBlocked(){
        Array<GridPoint2> points;
        points = bresenham.line(pos, GameHandler.player.getPosition());
        points.removeIndex(0); //remove the current tile from the tiles to check
        if(points.size > 0){
            points.removeIndex(points.size-1); // remove the players tile from the tiles to check
        }

        for(GridPoint2 point : points){
            DungeonTile tile = GameHandler.dungeon.getDungeonTile(point);
            if (tile.isVisionObstructing()){
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(){
        return false;
    }


    public abstract float getPassingCost();
}
