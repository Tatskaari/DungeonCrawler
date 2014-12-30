package com.mygdx.game.PathFinding;

/**
 * Created by jony1710 on 30/12/2014.
 */
public interface AstarHeuristic {
    public float costEstimate(AstarNode node, AstarNode endNode);
}
