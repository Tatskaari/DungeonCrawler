package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.Dungeon.Dungeon;
import com.mygdx.game.InputHandlers.GameInputHandler;
import com.mygdx.game.InputHandlers.MapInputHandler;
import com.mygdx.game.Player.PlayerCharacterEntity;
import com.mygdx.game.ResourceLoader;

public class MapScreen extends ScreenAdapter {

    private final OrthographicCamera camera;
    private final SpriteBatch batch;

    public Screen getLastScreen() {
        return lastScreen;
    }

    private final Screen lastScreen;
    private final InputMultiplexer inputMultiplexer;

    public MapScreen(Screen lastScreen){
        GameInputHandler gameInputHandler = new GameInputHandler();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        this.lastScreen = lastScreen;
        inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(gameInputHandler);
        batch = new SpriteBatch();

        inputMultiplexer.addProcessor(getInputProcessor());
    }

    public void moveCamera(int x, int y){
        camera.position.x -= x*camera.zoom;
        camera.position.y += y*camera.zoom;
    }

    public void zoomCamera(int amount){
        float zoomSpeed = 0.1f;
        float newZoomLevel = camera.zoom + camera.zoom*amount* zoomSpeed;
        float minZoom = 0.2f;
        float maxZoom = 10;
        if(newZoomLevel < minZoom){
            camera.zoom = minZoom;
        }else if(newZoomLevel > maxZoom){
            camera.zoom = maxZoom;
        }else {
            camera.zoom = newZoomLevel;
        }
    }

    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Dungeon.getActiveDungeon().renderer.mapRender(delta, batch);
        PlayerCharacterEntity.getInstance().renderer.render(delta, batch);
        batch.end();

    }

    @Override
    public void show() {
        int tileSize = ResourceLoader.getTileSize();
        Gdx.input.setInputProcessor(inputMultiplexer);
        GridPoint2 playerPos = PlayerCharacterEntity.getInstance().getPosition();
        camera.position.x = playerPos.x * tileSize;
        camera.position.y = playerPos.y * tileSize;

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    OrthographicCamera getCamera(){
        return camera;
    }

    SpriteBatch getBatch() {
        return batch;
    }

    InputProcessor getInputProcessor() {
        return new MapInputHandler(this);
    }
}
