package com.mygdx.game.PathFinding;

import java.awt.*;

public class CrowFliesHeuristic implements AstarHeuristic {
    @Override
    public float costEstimate(AstarNode node, AstarNode endNode) {
        return (float)Point.distance(node.x, node.y, endNode.x, endNode.y);
    }
}
