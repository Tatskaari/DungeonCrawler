package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.Player.PlayerCharacterEntity;

import java.awt.*;

public abstract class Behavior {

    public abstract Behavior act();

    boolean isPlayerAdjacent(GridPoint2 pos) {
        GridPoint2 playerPosition = PlayerCharacterEntity.getInstance().getPosition();
        double dist = Point.distance(pos.x, pos.y, playerPosition.x, playerPosition.y);
        return dist < 1.5;
    }

    boolean moveMonsterAlongPath(CharacterEntity characterEntity, Array<AstarNode> path){
        if (path.size == 0){
            return false;
        }
        AstarNode node = path.peek();
        if(characterEntity.moveTo(node.getPosition())){
            path.pop();
            return true;
        }
        return false;
    }
}
