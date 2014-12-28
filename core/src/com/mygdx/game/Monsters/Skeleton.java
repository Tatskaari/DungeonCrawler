package com.mygdx.game.Monsters;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.GameHandler;
import com.mygdx.game.PathFinding.Astar;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.Renderers.MonsterRenderer;
import com.mygdx.game.Renderers.Renderer;
import com.mygdx.game.ResourceLoader;

public class Skeleton implements Monster{
    private GridPoint2 pos;
    private Array<AstarNode> path;

    public Renderer renderer;

    public Skeleton(GridPoint2 pos){
        renderer = new MonsterRenderer(this);
        this.pos = pos;
        path = new Array<AstarNode>();
        generateNewPath();
    }

    @Override
    public void act(){
        if(path.size > 0){
            AstarNode node = path.pop();
            pos = node.getPosition();
        }
    }

    private void generateNewPath(){
        Astar astar = new Astar();
        Array<Array<AstarNode>> astarGraph = GameHandler.dungeon.getAstarGraph();
        GridPoint2 playerPos = GameHandler.player.getPosition();

        AstarNode startNode = astarGraph.get(pos.x).get(pos.y);
        AstarNode targetNode = astarGraph.get(playerPos.x).get(playerPos.y);

        path = astar.getPath(astarGraph, startNode, targetNode);
    }

    private GridPoint2 getRandomTileInRoom(DungeonRoom room){
        GridPoint2 tilePosition = new GridPoint2();

        tilePosition.y = MathUtils.random(room.getY() + 1, room.getY() + room.getHeight() - 2);
        tilePosition.x = MathUtils.random(room.getX()+1, room.getX()+room.getWidth()-2);

        return tilePosition;
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
}
