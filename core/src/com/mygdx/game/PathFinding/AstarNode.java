package com.mygdx.game.PathFinding;

import com.badlogic.gdx.math.GridPoint2;

public class AstarNode {
    public int x;
    public int y;
    public float gScore;
    public float fScore;
    public float passingCost;
    public AstarNode prevNodeInPath;

    public boolean equals(AstarNode node){
        return node.x == x && node.y == y;
    }

    public GridPoint2 getPosition(){
        return new GridPoint2(x,y);
    }
}
