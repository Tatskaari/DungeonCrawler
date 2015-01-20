package com.mygdx.game.PathFinding;

interface AstarHeuristic {
    public float costEstimate(AstarNode node, AstarNode endNode);
}
