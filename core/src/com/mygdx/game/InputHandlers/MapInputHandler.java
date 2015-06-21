package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.MapScreen;

public class MapInputHandler extends InputAdapter{
    private final MapScreen screen;

    private int lastFrameX, lastFrameY;

    public MapInputHandler(MapScreen screen){
        this.screen = screen;
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

        screen.moveCamera(deltaX, deltaY);

        return true;
    }

    @Override
    public boolean scrolled(int amount){
        screen.zoomCamera(amount);
        return true;
    }

    @Override
    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.M){
            MyGdxGame.myGdxGame.setScreen(screen.getLastScreen());
            return true;
        }
        return false;
    }

    Screen getLastScreen(){
        return screen.getLastScreen();
    }
}
