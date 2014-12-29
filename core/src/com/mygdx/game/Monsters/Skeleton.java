package com.mygdx.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Behaviors.Behavior;
import com.mygdx.game.Behaviors.SkeletonBehavior;
import com.mygdx.game.Behaviors.SkeletonWanderBehavior;
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
    private int health = 10;
    private Behavior behavior;

    public Renderer renderer;

    public Skeleton(GridPoint2 pos){
        renderer = new MonsterRenderer(this);
        this.pos = pos;
        behavior = new SkeletonWanderBehavior(this);
    }

    @Override
    public void beAttacked() {
        health--;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
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

    @Override
    public void setPosition(GridPoint2 position) {
        this.pos = position;
    }

    @Override
    public void attackPlayer() {
        GameHandler.player.beAttacked(1);
    }

    @Override
    public void act() {
        behavior = behavior.act();
    }
}
