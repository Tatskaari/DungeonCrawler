package com.mygdx.game.PathFinding;

import java.awt.*;

/**
 * Created by jony1710 on 30/12/2014.
 */
public class CrowFliesHeuristic implements AstarHeuristic {
    @Override
    public float costEstimate(AstarNode node, AstarNode endNode) {
        return (float)Point.distance(node.x, node.y, endNode.x, endNode.y);
    }
}
