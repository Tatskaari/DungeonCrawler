package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.game.GameHandler;
import com.mygdx.game.InputHandlers.DevInputHandler;

public class DevScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public Screen getLastScreen() {
        return lastScreen;
    }

    private Screen lastScreen;
    private DevInputHandler inputHandler;
    private float zoomSpeed = 0.1f;
    private float minZoom = 0.2f;
    private float maxZoom = 10;

    public DevScreen(Screen lastScreen){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        this.lastScreen = lastScreen;
        inputHandler = new DevInputHandler(this);
        batch = new SpriteBatch();
    }

    public void moveCamera(int x, int y){
        camera.position.x -= x*camera.zoom;
        camera.position.y += y*camera.zoom;
    }

    public void zoomCamera(int amount){
        float newZoomLevel = camera.zoom + camera.zoom*amount*zoomSpeed;
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
        GameHandler.dungeon.renderer.devRender(delta, batch);
        GameHandler.player.renderer.devRender(delta, batch);
        batch.end();

    }

    @Override
    public void show() {
        int tileSize = GameHandler.dungeon.getTileSize();
        Gdx.input.setInputProcessor(inputHandler);
        GridPoint2 playerPos = GameHandler.player.getPosition();
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
}
