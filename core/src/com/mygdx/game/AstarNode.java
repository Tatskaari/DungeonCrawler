package com.mygdx.game;

import java.awt.*;

public class AstarNode {
    public int x;
    public int y;
    public float gScore;
    public float fScore;
    public float passingCost;
    public AstarNode prevNodeInPath;

    public boolean equals(AstarNode node){
        if(node.x == x && node.y == y){
            return true;
        }
        return false;
    }

    public Point getPosition(){
        return new Point(x,y);
    }
}
