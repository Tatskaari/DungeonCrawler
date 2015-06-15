package com.mygdx.game.Behaviors;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Characters.NonPlayerCharacterEntity;
import com.mygdx.game.Dungeon.DungeonUtils;
import com.mygdx.game.PathFinding.AstarNode;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class GenericAttackPlayerBehavior extends Behavior {
    private final NonPlayerCharacterEntity character;
    private final GridPoint2 playersLastKnownPos;
    private Array<AstarNode> path;
    private final GridPoint2 pathTarget;

    public GenericAttackPlayerBehavior(NonPlayerCharacterEntity character){
        this.character = character;
        path = new Array<AstarNode>();
        playersLastKnownPos = PlayerCharacterEntity.getInstance().getPosition();
        pathTarget = new GridPoint2(-1,-1);
    }

    @Override
    public Behavior act() {
        PlayerCharacterEntity player = PlayerCharacterEntity.getInstance();

        if (DungeonUtils.canSeePlayerFrom(character.getPosition())){
            playersLastKnownPos.set(player.getPosition());
        }else if (!isPlayerPositionKnown()){
            return new GenericWanderBehavior(character);
        }

        if (isPlayerAdjacent(character.getPosition())){
            character.attack(player);
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
            path = DungeonUtils.generateNewPathBetween(character.getPosition(), playersLastKnownPos, character.getDungeon());
            pathTarget.set(playersLastKnownPos);
        }
        if (path.size > 0){
            moveMonsterAlongPath(character, path);
        }
    }
}
