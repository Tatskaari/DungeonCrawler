package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacter;
import com.mygdx.game.Screens.DevScreen;

public class PlayerInputHandler extends InputAdapter {
    private PlayerCharacter player;

    public PlayerInputHandler(PlayerCharacter player){
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

    public boolean checkDpadDown(int keyCode){
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
        }
        if (position.equals(player.getPosition())){
            return false;
        } else {
            player.moveTo(position);
            return true;
        }
    }
}
