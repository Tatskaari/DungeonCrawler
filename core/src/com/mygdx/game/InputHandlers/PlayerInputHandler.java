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
        GridPoint2 position = player.getPosition();
        if(keyCode == Input.Keys.W){
            player.moveToPos(new GridPoint2(position.x, position.y+1));
        }
        if(keyCode == Input.Keys.S){
            player.moveToPos(new GridPoint2(position.x, position.y - 1));
        }
        if(keyCode == Input.Keys.D){
            player.moveToPos(new GridPoint2(position.x + 1, position.y));
        }
        if(keyCode == Input.Keys.A){
            player.moveToPos(new GridPoint2(position.x - 1, position.y));
        }
        if(keyCode == Input.Keys.CONTROL_LEFT){
            MyGdxGame.myGdxGame.setScreen(new DevScreen(MyGdxGame.myGdxGame.getScreen()));
        }

        return true;
    }
}
