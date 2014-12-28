package com.mygdx.game;

import com.badlogic.gdx.math.GridPoint2;
import squid.squidgrid.fov.EliasLOS;

/**
 * Created by jony1710 on 28/12/2014.
 */
public class LineOfSight {
    public static boolean checkLineOfSight(GridPoint2 startPoint, GridPoint2 endPoint){
        EliasLOS los = new EliasLOS();
        float[][] resMap = GameHandler.dungeon.getLineOfSightResMap();
        return los.isReachable(resMap, startPoint.x+1, startPoint.y+1, endPoint.x+1, endPoint.y+1);
    }
}
