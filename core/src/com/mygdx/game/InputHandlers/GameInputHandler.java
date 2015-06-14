package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.GameHandler;

public class GameInputHandler extends InputAdapter {
    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
            return true;
        }
        else if (keyCode == Input.Keys.T){
            GameHandler.stepTurn();
            return true;
        }

        return false;
    }
}
