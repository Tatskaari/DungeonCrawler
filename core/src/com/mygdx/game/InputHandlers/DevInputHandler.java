package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.DevScreen;

public class DevInputHandler extends InputAdapter {
    DevScreen devScreen;

    int lastScreenX, lastScreenY;

    public DevInputHandler(DevScreen devScreen){
        this.devScreen = devScreen;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        lastScreenX = x;
        lastScreenY = y;
        return true;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer){
        int deltaX = x - lastScreenX;
        int deltaY = y - lastScreenY;
        lastScreenX = x;
        lastScreenY = y;

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
        }
        if (keyCode == Input.Keys.G){
            MyGdxGame.dungeon = MyGdxGame.dungeonGenerator.generateDungeon();
            MyGdxGame.player.placeCharacterIn(MyGdxGame.dungeon);
        }
        return true;
    }
}
