package com.mygdx.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;

public class Skeleton implements Monster{
    private GridPoint2 pos;
    private GridPoint2 targetPosition;
    private Array<AstarNode> path;
    private int health = 10;

    public Renderer renderer;

    public Skeleton(GridPoint2 pos){
        renderer = new MonsterRenderer(this);
        this.pos = pos;
        targetPosition = getRandomTileInAnyRoom();
        path = new Array<AstarNode>();
        generateNewPathTo(targetPosition);
    }

    @Override
    public void act(){
        boolean targetChanged = false;
        if(canSeePlayer()){
            if(!targetPosition.equals(GameHandler.player.getPosition())){
                targetPosition = GameHandler.player.getPosition();
                targetChanged = true;
            }

        }
        else if (targetPosition.equals(pos)){
            targetPosition = getRandomTileInAnyRoom();
            targetChanged = true;
        }

        if (targetChanged) {
            generateNewPathTo(targetPosition);
        }

        if (path.size > 0) {
            AstarNode node = path.peek();
            if(GameHandler.dungeon.isTilePassable(node.getPosition())){
                pos = node.getPosition();
                path.pop();
            }
        } else {
            System.out.println("HP: " + health);
        }
    }

    @Override
    public void beAttacked() {
        health--;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    private void generateNewPathTo(GridPoint2 targetPoint){
        Astar astar = new Astar();
        Array<Array<AstarNode>> astarGraph = GameHandler.dungeon.getAstarGraph();

        AstarNode startNode = astarGraph.get(pos.x).get(pos.y);
        AstarNode targetNode = astarGraph.get(targetPoint.x).get(targetPoint.y);

        path = astar.getPath(astarGraph, startNode, targetNode);
        path.pop(); //Get rid of the node we're currently on
    }

    private GridPoint2 getRandomTileInRoom(DungeonRoom room){
        GridPoint2 tilePosition = new GridPoint2();

        tilePosition.y = MathUtils.random(room.getY() + 1, room.getY() + room.getHeight() - 2);
        tilePosition.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);

        return tilePosition;
    }

    private boolean canSeePlayer(){
        return LineOfSight.checkLineOfSight(pos, GameHandler.player.getPosition());
    }

    @Override
    public Texture getTexture() {
        return ResourceLoader.skeleton;
    }

    @Override
    public GridPoint2 getPosition() {
        return pos;
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }

    public GridPoint2 getRandomTileInAnyRoom() {
        int roomIndex = MathUtils.random(GameHandler.dungeon.getRoomCount()-1);
        return getRandomTileInRoom(GameHandler.dungeon.getDungeonRoom(roomIndex));
    }
}
