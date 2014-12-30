package com.mygdx.game.PathFinding;

public class GridBasedHeuristic implements AstarHeuristic {
    @Override
    public float costEstimate(AstarNode node, AstarNode endNode) {
        return Math.abs(node.x - endNode.x) + Math.abs(node.y - endNode.y);
    }
}
