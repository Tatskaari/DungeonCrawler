package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.PathFinding.AstarNode;

public class SkeletonAttackPlayerBehavior extends Behavior {
    private NonPlayerCharacterEntity character;
    private final GridPoint2 playersLastKnownPos;
    private Array<AstarNode> path;
    private final GridPoint2 pathTarget;

    public SkeletonAttackPlayerBehavior(NonPlayerCharacterEntity character){
        this.character = character;
        path = new Array<AstarNode>();
        playersLastKnownPos = GameHandler.player.getPosition();
        pathTarget = new GridPoint2(-1,-1);
    }

    @Override
    public Behavior act() {
        if (canSeePlayerFrom(character.getPosition())){
            playersLastKnownPos.set(GameHandler.player.getPosition());
        }else if (!isPlayerPositionKnown()){
            return new SkeletonWanderBehavior(character);
        }

        if (isPlayerAdjacent(character.getPosition())){
            character.attack(GameHandler.player);
        } else {
            moveTowardsPlayer();
        }
        return this;
    }

    private boolean isPlayerPositionKnown() {
        return !(playersLastKnownPos == null || playersLastKnownPos.equals(character.getPosition()));
    }

    private void moveTowardsPlayer(){
        if (!playersLastKnownPos.equals(pathTarget)){
            path = generateNewPathBetween(character.getPosition(), playersLastKnownPos);
            pathTarget.set(playersLastKnownPos);
        }
        if (path.size > 0){
            moveMonsterAlongPath(character, path);
        }
    }
}
