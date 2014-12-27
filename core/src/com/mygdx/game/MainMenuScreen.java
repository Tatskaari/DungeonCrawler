package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen{
    OrthographicCamera camera;

    public MainMenuScreen(){
        camera = MyGdxGame.camera;
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        camera.zoom = 1;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MyGdxGame.batch.setProjectionMatrix(camera.combined);

        MyGdxGame.batch.begin();
        ResourceLoader.defaultFont.draw(MyGdxGame.batch, "Welcome to My Dungeon!!!", Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight() - 100);
        ResourceLoader.defaultFont.draw(MyGdxGame.batch, "Click anywhere to begin!", Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight() - 150);
        MyGdxGame.batch.end();

        if (Gdx.input.isTouched()) {
            MyGdxGame.myGdxGame.setScreen(new GameScreen());
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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
