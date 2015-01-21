package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.DungeonTile;
import com.mygdx.game.GameHandler;
import com.mygdx.game.Inventory.ItemTypes.InventoryItem;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.Screens.DevScreen;
import com.mygdx.game.UserInterface.UserInterface;
import com.mygdx.game.Utils.ColouredText;

public class PlayerInputHandler extends InputAdapter {
    private final PlayerCharacterEntity player;

    public PlayerInputHandler(PlayerCharacterEntity player){
        this.player = player;
    }

    @Override
    public boolean keyDown(int keyCode){
        if(keyCode == Input.Keys.CONTROL_LEFT){
            MyGdxGame.myGdxGame.setScreen(new DevScreen(MyGdxGame.myGdxGame.getScreen()));
            return true;
        }

        return checkDpadDown(keyCode);
    }

    boolean checkDpadDown(int keyCode){
        GridPoint2 position = player.getPosition();
        if(keyCode == Input.Keys.W || keyCode == Input.Keys.UP){
            position.y++;
        }
        else if(keyCode == Input.Keys.S || keyCode == Input.Keys.DOWN){
            position.y--;
        }
        else if(keyCode == Input.Keys.D || keyCode == Input.Keys.RIGHT){
            position.x++;
        }
        else if(keyCode == Input.Keys.A || keyCode == Input.Keys.LEFT){
            position.x--;
        } else if (keyCode == Input.Keys.SPACE){
            pickUpItem();
        }
        if (position.equals(player.getPosition())){
            return false;
        } else {
            player.moveTo(position);
            return true;
        }
    }

    private void pickUpItem() {
        PlayerCharacterEntity player = GameHandler.player;
        DungeonTile tile = GameHandler.dungeon.getDungeonTile(player.getPosition());

        if(tile.hasItem()){
            InventoryItem item = tile.pickUpItem();
            if(player.inventory.addItem(item)){
                UserInterface.growlArea.println(new ColouredText("Picked up a " + item.getItemName()));
            } else {
                tile.addItem(item);
                UserInterface.growlArea.println(new ColouredText("You cannot pick that item up. Your inventory is full."));
            }
        }
    }
}
