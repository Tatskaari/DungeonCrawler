package com.mygdx.game;


import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

/**
 * Created by jony1710 on 23/12/2014.
 */
public class DungeonTile {
    public static final int EMPTY = 0;
    public static final int FLOOR = 10;
    public static final int WALL = 20;
    public static final int DOOR_HORIZONTAL = 30;
    public static final int DOOR_VERTICAL = 40;
    public static final int CORRIDOR_FLOOR = 50;
    public static final int CORRIDOR_WALL = 60;

    public static Bresenham2 bresenham = new Bresenham2();

    public int tileType;

    public boolean passable = true;

    public boolean tileHasBeenVisible = false;

    private GridPoint2 pos;

    public DungeonTile(GridPoint2 pos) {
        this.pos = pos;
        tileType = EMPTY;
    }

    public float getCorridorPlacingCost() {
        switch (tileType){
            case EMPTY: return 50;
            case CORRIDOR_FLOOR: return 1;
            case CORRIDOR_WALL: return 50;
            case DOOR_HORIZONTAL: return 9000;
            case DOOR_VERTICAL: return 9000;
            case FLOOR: return 200;
            case WALL: return 10000;
            default: return 9999999;
        }
    }

    public boolean isVisionObstructing(){
        switch (tileType) {
            case CORRIDOR_WALL: return true;
            case WALL: return true;
            case DOOR_VERTICAL: return true;
            case DOOR_HORIZONTAL: return true;
            default: return false;
        }
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

    private float getHasBeenVisibleVisibility(){
        if(tileHasBeenVisible){
            return 0.5f;
        }
        else{
            return 0;
        }
    }

}
