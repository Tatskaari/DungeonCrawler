package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.InputHandlers.DevInputHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Renderers.DungeonRenderer;

public class DevScreen implements Screen {
    private OrthographicCamera camera;
    SpriteBatch batch;

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
        GridPoint2 playerPos = MyGdxGame.player.getPosition();
        camera.position.set(playerPos.x, playerPos.y, 0);
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
        MyGdxGame.dungeon.devRenderer.render(delta, batch);
        MyGdxGame.player.renderer.render(delta, batch);
        batch.end();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
