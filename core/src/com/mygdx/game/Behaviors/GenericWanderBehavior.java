package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.DungeonRoom;
import com.mygdx.game.GameHandler;
import com.mygdx.game.PathFinding.AstarNode;

import java.awt.*;

public class GenericWanderBehavior extends Behavior {
    private NonPlayerCharacterEntity character;
    private GridPoint2 targetPoint;
    private GridPoint2 pathTarget;
    private Array<AstarNode> path;
    private int actCountdown = 0;
    private DungeonRoom dungeonRoom;

    public GenericWanderBehavior(NonPlayerCharacterEntity character) {
        this.character = character;
        dungeonRoom = getClosestRoom();
        path = getNewPath();
    }

    @Override
    public Behavior act() {
        if (MathUtils.randomBoolean(0.1f)){
            return new GenericFindPlayerBehavior(character);
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
            moveMonsterAlongPath(character, path);
        } else {
            path = getNewPath();
        }
    }

    private Array<AstarNode> getNewPath(){
        targetPoint = getRandomTileInRoom(dungeonRoom);
        pathTarget = targetPoint;
        return generateNewPathBetween(character.getPosition(), targetPoint);
    }

    public DungeonRoom getClosestRoom() {
        DungeonRoom closestRoom = GameHandler.dungeon.getDungeonRoom(0);
        double closestDist = Point.distance(character.getPosition().x, character.getPosition().y, closestRoom.getX(), closestRoom.getY());
        for(int i = 1; i < GameHandler.dungeon.getRoomCount(); i++){
            DungeonRoom currentRoom = GameHandler.dungeon.getDungeonRoom(i);
            double dist = Point.distance(character.getPosition().x, character.getPosition().y, currentRoom.getX(), currentRoom.getY());
            if (dist < closestDist){
                closestDist = dist;
                closestRoom = currentRoom;
            }
        }

        return closestRoom;
    }
}
