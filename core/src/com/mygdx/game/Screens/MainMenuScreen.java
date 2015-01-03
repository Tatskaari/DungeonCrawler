package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceLoader;

public class MainMenuScreen extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public MainMenuScreen(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        camera.zoom = 1;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        ResourceLoader.defaultFont.draw(batch, "Welcome to My Dungeon!!!", camera.viewportWidth/4, camera.viewportHeight - 100);
        ResourceLoader.defaultFont.draw(batch, "Click anywhere to begin!", Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight() - 150);
        batch.end();

        if (Gdx.input.isTouched()) {
            MyGdxGame.myGdxGame.setScreen(new GameScreen());
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width/2,height/2,0);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
