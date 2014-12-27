package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class DevScreen implements Screen {
    OrthographicCamera camera;
    Screen lastScreen;
    DevInputHandler inputHandler;
    float zoomSpeed = 0.1f;
    float minZoom = 0.2f;
    float maxZoom = 10;

    public DevScreen(Screen lastScreen){
        camera = new OrthographicCamera(MyGdxGame.camera.viewportWidth, MyGdxGame.camera.viewportHeight);
        camera.position.set(MyGdxGame.camera.position);
        camera.zoom = MyGdxGame.camera.zoom;
        camera.update();
        this.lastScreen = lastScreen;
        inputHandler = new DevInputHandler(this);
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
        MyGdxGame.batch.setProjectionMatrix(camera.combined);
        MyGdxGame.batch.begin();
        MyGdxGame.dungeon.devRender();
        MyGdxGame.player.render();
        MyGdxGame.batch.end();

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
    }
}
