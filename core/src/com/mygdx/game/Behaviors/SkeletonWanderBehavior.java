package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Monsters.Monster;
import com.mygdx.game.PathFinding.AstarNode;

import java.awt.*;

/**
 * Created by jony1710 on 29/12/2014.
 */
public class SkeletonWanderBehavior extends Behavior {
    private Monster monster;
    private GridPoint2 targetPoint;
    private GridPoint2 pathTarget;
    private Array<AstarNode> path;
    private int actCountdown = 0;
    private GridPoint2 randomTileInClosestRoom;
    private DungeonRoom dungeonRoom;

    public SkeletonWanderBehavior(Monster monster) {
        this.monster = monster;
        dungeonRoom = getClosestRoom();
        path = getNewPath();
    }

    @Override
    public Behavior act() {
        if(canSeePlayerFrom(monster.getPosition())){
            return new SkeletonAttackPlayerBehavior(monster);
        }
        if(actCountdown == 0){
            wander();
        } else {
            actCountdown--;
        }
        return this;
    }

    private void wander() {
        if(path.size == 0){
            path = getNewPath();
            actCountdown = 4;
        }
        else if (targetPoint.equals(pathTarget)){
            moveMonsterAlongPath(monster, path);
        } else {
            path = getNewPath();
        }
    }

    private Array<AstarNode> getNewPath(){
        targetPoint = getRandomTileInRoom(dungeonRoom);
        pathTarget = targetPoint;
        return generateNewPathBetween(monster.getPosition(), targetPoint);
    }

    public DungeonRoom getClosestRoom() {
        DungeonRoom closestRoom = GameHandler.dungeon.getDungeonRoom(0);
        double closestDist = Point.distance(monster.getPosition().x, monster.getPosition().y, closestRoom.getX(), closestRoom.getY());
        for(int i = 1; i < GameHandler.dungeon.getRoomCount(); i++){
            DungeonRoom currentRoom = GameHandler.dungeon.getDungeonRoom(i);
            double dist = Point.distance(monster.getPosition().x, monster.getPosition().y, currentRoom.getX(), currentRoom.getY());
            if (dist < closestDist){
                closestDist = dist;
                closestRoom = currentRoom;
            }
        }

        return closestRoom;
    }
}
