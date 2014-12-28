package com.mygdx.game.PathFinding;

import com.badlogic.gdx.utils.Array;

public class Astar {
    public Array<AstarNode> getPath(Array<Array<AstarNode>> graph, AstarNode startNode, AstarNode endNode){
        Array<AstarNode> openSet = new Array<AstarNode>();
        Array<AstarNode> closedSet = new Array<AstarNode>();

        openSet.add(startNode);

        AstarNode workingNode;
        while (openSet.size > 0){
            workingNode = getLowestFscore(openSet);
            if (workingNode.equals(endNode)){
                return reconstructPath(workingNode);
            }

            openSet.removeValue(workingNode, true);
            closedSet.add(workingNode);

            for(AstarNode neighbor : getNeighborNodes(workingNode, graph)){
                if(closedSet.contains(neighbor, true)){
                    continue;
                }

                float tentativeGScore = workingNode.gScore + neighbor.passingCost;

                if(!openSet.contains(neighbor, true) || neighbor.gScore > tentativeGScore){
                    neighbor.prevNodeInPath = workingNode;
                    neighbor.gScore = tentativeGScore;
                    neighbor.fScore = neighbor.gScore + costEstimate(neighbor, endNode);
                    if (!openSet.contains(neighbor, true)){
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    private int costEstimate(AstarNode node, AstarNode endNode){
        return Math.abs(node.x - endNode.x) + Math.abs(node.y - endNode.y);
    }

    private AstarNode getLowestFscore(Array<AstarNode> openSet){
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

    private Array<AstarNode> getNeighborNodes(AstarNode node, Array<Array<AstarNode>> graph){
        Array<AstarNode> neighbors = new Array<AstarNode>();

        int x = node.x;
        int y = node.y;

        addToArrayIfInBounds(neighbors, x+1, y, graph);
        addToArrayIfInBounds(neighbors, x-1, y, graph);
        addToArrayIfInBounds(neighbors, x, y+1, graph);
        addToArrayIfInBounds(neighbors, x, y-1, graph);

        return neighbors;
    }

    private void addToArrayIfInBounds(Array<AstarNode> neighbors, int x, int y, Array<Array<AstarNode>> graph){
        if(!(x < 0 || x >= graph.size) && !(y < 0 || y >= graph.get(x).size)){
            neighbors.add(graph.get(x).get(y));
        }
    }
}
