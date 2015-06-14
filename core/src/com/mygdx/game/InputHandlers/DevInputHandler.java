package com.mygdx.game.InputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.Dungeon.DungeonGenerator;
import com.mygdx.game.Dungeon.DungeonGeneratorFactory;
import com.mygdx.game.GameHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;
import com.mygdx.game.Screens.DevScreen;
import com.mygdx.game.Screens.MapScreen;

public class DevInputHandler extends MapInputHandler {

    public DevInputHandler(DevScreen lastScreen) {
        super(lastScreen);
    }

    // TODO add the dev tools
    // 1. regenerate, 2. spawn monster
    @Override
    public boolean keyDown(int keyCode){
        if (keyCode == Input.Keys.M){
            return false;
        } if (keyCode == Input.Keys.CONTROL_LEFT) {
            MyGdxGame.myGdxGame.setScreen(getLastScreen());
        }
        return super.keyDown(keyCode);
    }
}
