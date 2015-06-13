package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.GameHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Screens.DevScreen;

public class DevInputHandler extends InputAdapter {
    private final DevScreen devScreen;

    private int lastFrameX, lastFrameY;

    public DevInputHandler(DevScreen devScreen){
        this.devScreen = devScreen;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        if (!Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            return false;
        }
        lastFrameX = x;
        lastFrameY = y;
        return true;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer){
        if (!Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            return false;
        }
        int deltaX = x - lastFrameX;
        int deltaY = y - lastFrameY;
        lastFrameX = x;
        lastFrameY = y;

        devScreen.moveCamera(deltaX, deltaY);

        return true;
    }

    @Override
    public boolean scrolled(int amount){
        devScreen.zoomCamera(amount);
        return true;
    }

    @Override
    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.CONTROL_LEFT){
            MyGdxGame.myGdxGame.setScreen(devScreen.getLastScreen());
            return true;
        }
        if (keyCode == Input.Keys.G){
            GameHandler.dungeon = GameHandler.dungeonGenerator.regenerateDungeon();
            PlayerCharacterEntity.getInstance().placeCharacterIn(GameHandler.dungeon);
            GameHandler.dungeonGenerator.spawnMonsters(GameHandler.dungeon.getRoomCount());
            return true;
        }
        if (keyCode == Input.Keys.R){
            ResourceLoader.loadResources();
            return true;
        }
        if (keyCode == Input.Keys.N){
            GameHandler.stepTurn();
        }
        return false;
    }
}
