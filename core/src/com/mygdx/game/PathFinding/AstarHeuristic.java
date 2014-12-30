package com.mygdx.game.PathFinding;

public interface AstarHeuristic {
    public float costEstimate(AstarNode node, AstarNode endNode);
}
