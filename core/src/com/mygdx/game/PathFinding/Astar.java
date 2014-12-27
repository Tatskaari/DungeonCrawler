package com.mygdx.game.PathFinding;

import com.badlogic.gdx.utils.Array;

public class Astar {
    private Array<Array<AstarNode>> graph;

    private AstarNode endPoint;

    private Array<AstarNode> openSet;
    private Array<AstarNode> closedSet;


    public Array<AstarNode> getPath(Array<Array<AstarNode>> graph, AstarNode startPoint, AstarNode endPoint){
        this.graph = graph;
        this.endPoint = endPoint;

        openSet = new Array<AstarNode>();
        closedSet = new Array<AstarNode>();

        openSet.add(startPoint);

        return getPath();
    }

    private Array<AstarNode> getPath(){
        AstarNode workingNode;
        while (openSet.size > 0){
            workingNode = getLowestFscore();
            if (workingNode.equals(endPoint)){
                return reconstructPath(workingNode);
            }

            openSet.removeValue(workingNode, true);
            closedSet.add(workingNode);

            for(AstarNode neighbor : getNeighborNodes(workingNode)){
                if(closedSet.contains(neighbor, true)){
                    continue;
                }

                float tentativeGScore = workingNode.gScore + neighbor.passingCost;

                if(!openSet.contains(neighbor, true) || neighbor.gScore > tentativeGScore){
                    neighbor.prevNodeInPath = workingNode;
                    neighbor.gScore = tentativeGScore;
                    neighbor.fScore = neighbor.gScore + costEstimate(neighbor);
                    if (!openSet.contains(neighbor, true)){
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null;

    }

    private int costEstimate(AstarNode node){
        return Math.abs(node.x - endPoint.x) + Math.abs(node.y - endPoint.y);
    }

    private AstarNode getLowestFscore(){
        AstarNode lowest = openSet.first();
        for (int i = 1; i < openSet.size; i++){
            if(openSet.get(i).fScore < lowest.fScore){
                lowest = openSet.get(i);
            }
        }
        return lowest;
    }

    private Array<AstarNode> reconstructPath(AstarNode fromNode){
        Array<AstarNode> path = new Array<AstarNode>();
        AstarNode currentNode = fromNode;

        while(currentNode != null){
            path.add(currentNode);
            currentNode = currentNode.prevNodeInPath;
        }

        return path;
    }

    private Array<AstarNode> getNeighborNodes(AstarNode node){
        Array<AstarNode> neighbors = new Array<AstarNode>();

        int x = node.x;
        int y = node.y;

        addToArrayIfInBounds(neighbors, x+1, y);
        addToArrayIfInBounds(neighbors, x-1, y);
        addToArrayIfInBounds(neighbors, x, y+1);
        addToArrayIfInBounds(neighbors, x, y-1);

        return neighbors;
    }

    private void addToArrayIfInBounds(Array<AstarNode> neighbors, int x, int y){
        if(!(x < 0 || x >= graph.size) && !(y < 0 || y >= graph.get(x).size)){
            neighbors.add(graph.get(x).get(y));
        }
    }
}
