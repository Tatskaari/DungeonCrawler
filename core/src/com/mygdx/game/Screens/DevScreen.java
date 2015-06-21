package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.InputHandlers.DevInputHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;

public class DevScreen extends MapScreen {

    public DevScreen(Screen lastScreen) {
        super(lastScreen);
    }

    @Override
    public InputProcessor getInputProcessor() {
        return new DevInputHandler(this);
    }

    public void render(float delta){
        Camera camera = getCamera();
        SpriteBatch batch = getBatch();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Dungeon.getActiveDungeon().renderer.devRender(delta, batch);
        PlayerCharacterEntity.getInstance().renderer.devRender(delta, batch);
        batch.end();

    }
}
