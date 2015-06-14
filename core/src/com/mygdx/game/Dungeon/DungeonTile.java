package com.mygdx.game.Dungeon;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.LineOfSight;
import com.mygdx.game.Characters.CharacterEntity;
import com.mygdx.game.Player.PlayerCharacterEntity;

import java.awt.*;

public abstract class DungeonTile {
    private final int VIEW_DIST = 12;
    private final float FOW_VISIBILITY = 0.4f;
    
    private boolean tileIsDescovered = false;
    private final GridPoint2 pos;
    private final Array<InventoryItem> itemList;

    protected DungeonTile(GridPoint2 pos) {
        this.pos = pos;

        itemList = new Array<InventoryItem>();
    }

    public abstract float getCorridorPlacingCost();

    public abstract boolean isVisionObstructing();

    public abstract TextureRegion getTileTexture();

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
            tileIsDescovered = true;
            GridPoint2 playerGridPoint = PlayerCharacterEntity.getInstance().getPosition();
            float distance = (float)Point.distance(playerGridPoint.x, playerGridPoint.y, pos.x, pos.y);
            return 1 - distance/(VIEW_DIST*2);
        } else {
            return getHasBeenVisibleVisibility();
        }
    }

    public boolean isVisible(){
        GridPoint2 playerGridPoint = PlayerCharacterEntity.getInstance().getPosition();

        float distance = (float)Point.distance(playerGridPoint.x, playerGridPoint.y, pos.x, pos.y);
        if(distance > VIEW_DIST){
            return false;
        }else if(distance < 1.5f) {
            return true;
        } else {
            return isLOSBlocked();
        }
    }

    private float getHasBeenVisibleVisibility(){
        if(tileIsDescovered){
            return FOW_VISIBILITY;
        }
        else{
            return 0;
        }
    }

    private boolean isLOSBlocked(){
        return LineOfSight.checkLineOfSight(pos, PlayerCharacterEntity.getInstance().getPosition());
    }

    public boolean isPassable(){
        if(pos.equals(PlayerCharacterEntity.getInstance().getPosition())){
            return false;
        } else {
            for(CharacterEntity characterEntity : Dungeon.getActiveDungeon().monsters) {
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
        for(int i = 0; i < Dungeon.getActiveDungeon().monsters.size; i++){
            CharacterEntity characterEntity = Dungeon.getActiveDungeon().monsters.get(i);
            if (characterEntity.getPosition().equals(pos)){
                return true;
            }
        }

        return false;
    }

    public CharacterEntity getMonster() {
        for(int i = 0; i < Dungeon.getActiveDungeon().monsters.size; i++){
            CharacterEntity characterEntity = Dungeon.getActiveDungeon().monsters.get(i);
            if (characterEntity.getPosition().equals(pos) && !characterEntity.isDead()){
                return characterEntity;
            }
        }

        return null;
    }

    public void addItem(InventoryItem item){
        itemList.add(item);
    }

    public InventoryItem pickUpItem(){
        return itemList.pop();
    }

    public void onStep(){
        return;
    }

    public boolean hasItem() {
        return itemList.size > 0;
    }

    public int itemCount() {
        return itemList.size;
    }

    public InventoryItem getItem(int i){
        return itemList.get(i);
    }

    public boolean isDescovered() {
        return tileIsDescovered;
    }
}
