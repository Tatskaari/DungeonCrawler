package com.mygdx.game;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.Dungeon;


public class LineOfSight {
    public static boolean checkLineOfSight(GridPoint2 startPoint, GridPoint2 endPoint, Dungeon dungeon){
        // Moves the start point towards the end point to avoid points being obstructed by themselves
        startPoint = getNextPointTowardsEndPoint(startPoint, endPoint);
        Array<GridPoint2> cellsOnBresenhamLine = runLine(endPoint, startPoint);

        for (int i = 0; i < cellsOnBresenhamLine.size; i++) {
            GridPoint2 point = cellsOnBresenhamLine.get(i);
            if (dungeon.getDungeonTile(point).isVisionObstructing() && !point.equals(endPoint)){
                return false;
            }
        }

        return true;
    }

    private static GridPoint2 getNextPointTowardsEndPoint(GridPoint2 startPoint, GridPoint2 endPoint){
        int x = startPoint.x;
        int y = startPoint.y;

        if (startPoint.x < endPoint.x){
            x++;
        } else if(startPoint.x > endPoint.x){
            x--;
        }
        if (startPoint.y < endPoint.y){
            y++;
        } else if(startPoint.y > endPoint.y){
            y--;
        }

        return new GridPoint2(x, y);
    }

    private static Array<GridPoint2> runLine(GridPoint2 start, GridPoint2 end) {
        return runLine(start.x, start.y, end.x, end.y);
    }

    private static Array<GridPoint2> runLine (int startX, int startY, int endX, int endY) {
        Array<GridPoint2> line = new Array<>();

        // The amount the line has to cover in the x and y axes
        int deltaX = endX - startX;
        int deltaY = endY - startY;

        // the delta error would be infinite on a vertical line and therefore cannot be calculated in the same way
        if (deltaX == 0){
            return runVertLine(startX, startY, endY);
        }

        // The amount the points have deviated from the line
        float error = 0;
        // The amount the points deviate from the line for each movement along the x axis
        float deltaError = Math.abs ((float)deltaY / (float)deltaX);
        int y = startY;

        // plot pixels along the x axis occasionally moving in the y axis once we have deviated too far from the real line
        for (int x = startX; x != endX; x+=getSignNumber(deltaX)){
            line.add(new GridPoint2(x, y));
            error += deltaError;

            // While we have deviated more than 1/2 a pixel from the real line, we need to correct for it
            // by moving in the y axis
            while (error >= 0.5f) {
                y += getSignNumber(endY - startY);
                error--;
                line.add(new GridPoint2(x, y));
            }
        }

        // Due to not knowing which way the loop is reaching it's condition from (x-- or x++)
        // the last point has to be added here
        line.add(new GridPoint2(endX, endY));
        return line;
    }

    private static Array<GridPoint2> runVertLine(int x, int startY, int endY) {
        Array<GridPoint2> line = new Array<>();

        // The direction the line is being drawn in (1 for up the Y axis and -1 for down the Y axis)
        int deltaYSign = getSignNumber(endY-startY);

        for (int y = startY; y != endY; y+=deltaYSign){
            line.add(new GridPoint2(x, y));
        }

        line.add(new GridPoint2(x, endY));
        return line;
    }

    private static int getSignNumber(float a){
        if (a >0){
            return 1;
        }
        else if (a < 0){
            return -1;
        }
        return 0;
    }
}
