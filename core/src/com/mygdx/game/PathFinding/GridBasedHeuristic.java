package com.mygdx.game.PathFinding;

/**
 * Created by jony1710 on 30/12/2014.
 */
public class GridBasedHeuristic implements AstarHeuristic {
    @Override
    public float costEstimate(AstarNode node, AstarNode endNode) {
        return Math.abs(node.x - endNode.x) + Math.abs(node.y - endNode.y);
    }
}
