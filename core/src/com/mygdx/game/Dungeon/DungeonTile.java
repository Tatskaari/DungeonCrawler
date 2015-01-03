package com.mygdx.game.Dungeon;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.Characters.CharacterEntity;

import java.awt.*;

public abstract class DungeonTile {
    public static final int EMPTY = 0;
    public static final int FLOOR = 10;
    public static final int WALL = 20;
    public static final int DOOR_HORIZONTAL = 30;
    public static final int DOOR_VERTICAL = 40;
    public static final int CORRIDOR_FLOOR = 50;
    public static final int CORRIDOR_WALL = 60;


    private boolean tileHasBeenVisible = false;

    private GridPoint2 pos;

    public DungeonTile(GridPoint2 pos) {
        this.pos = pos;
    }

    public abstract float getCorridorPlacingCost();

    public abstract boolean isVisionObstructing();

    public abstract int getTileType();

    public abstract Texture getTileTexture();

    public GridPoint2 getPos(){
        return pos;
    }

    public boolean isEmpty(){
        return false;
    }

    public float getPassingCost(){
        if(!isPassable()){
            return 100000;
        } else if (hasMonster()) {
            return 10000;
        } else {
            return 5;
        }
    }

    public float getVisibilityLevel() {
        if(isVisible()){
            tileHasBeenVisible = true;
            return 1;
        } else {
            return getHasBeenVisibleVisibility();
        }
    }

    public boolean isVisible(){
        GridPoint2 playerGridPoint = GameHandler.player.getPosition();

        float distance = (float)Point.distance(playerGridPoint.x, playerGridPoint.y, pos.x, pos.y);
        if(distance > 12){
            return false;
        }else if(distance < 1) {
            return true;
        } else {
            return isLOSBlocked();
        }
    }

    private float getHasBeenVisibleVisibility(){
        if(tileHasBeenVisible){
            return 0.5f;
        }
        else{
            return 0;
        }
    }

    private boolean isLOSBlocked(){
        return LineOfSight.checkLineOfSight(pos, GameHandler.player.getPosition());
    }

    public boolean isPassable(){
        if(pos.equals(GameHandler.player.getPosition())){
            return false;
        } else {
            for(CharacterEntity characterEntity : GameHandler.dungeon.monsters) {
                if(characterEntity.getPosition().equals(pos)){
                    if(!characterEntity.isDead()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean hasMonster() {
        for(int i = 0; i < GameHandler.dungeon.monsters.size; i++){
            CharacterEntity characterEntity = GameHandler.dungeon.monsters.get(i);
            if (characterEntity.getPosition().equals(pos)){
                return true;
            }
        }

        return false;
    }

    public CharacterEntity getMonster() {
        for(int i = 0; i < GameHandler.dungeon.monsters.size; i++){
            CharacterEntity characterEntity = GameHandler.dungeon.monsters.get(i);
            if (characterEntity.getPosition().equals(pos) && !characterEntity.isDead()){
                return characterEntity;
            }
        }

        return null;
    }
}
